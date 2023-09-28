package org.rsm.threads.service;

import lombok.extern.slf4j.Slf4j;
import org.rsm.threads.conf.Conf;
import org.rsm.threads.exception.CapacityExceededException;
import org.rsm.threads.model.FileReport;
import org.rsm.threads.model.PackageReport;
import org.rsm.threads.service.download.DownloadService;
import org.rsm.threads.service.fileinfo.DownloadInfo;
import org.rsm.threads.service.fileinfo.FileInfoService;
import org.rsm.threads.service.upload.UploadService;

import java.io.InputStream;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class DataTransferService {

    public static PackageReport transferData(long packageId) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(Conf.threadPoolSize);

        long packageTransferStartTime = System.currentTimeMillis();
        PackageReport packageReport = new PackageReport();
        Set<String> duplicateNamesFilter = Collections.synchronizedSet(new HashSet<>());
        AtomicInteger occupiedCapacity = new AtomicInteger();

        FileInfoService.getDownloadInfos(packageId)
                .stream()
                .filter(downloadInfo -> fileFilter(downloadInfo, duplicateNamesFilter, packageReport))
                .forEach(downloadInfo -> executorService.submit(() ->
                        submitTransfer(downloadInfo, occupiedCapacity, packageReport))
                );

        executorService.shutdown();
        executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);

        long packageTransferEndTime = System.currentTimeMillis();
        packageReport.setTotalTime(packageTransferEndTime - packageTransferStartTime);

        return packageReport;
    }

    private static boolean fileFilter(DownloadInfo downloadInfo, Set<String> duplicateNamesFilter, PackageReport packageReport) {
        log.debug("Verifying download info: {}", downloadInfo);
        boolean isDuplicate = !duplicateNamesFilter.add(downloadInfo.getOriginalFileName());
        boolean hasForbiddenExtension = Conf.fileExtensionsFilter.stream()
                .parallel()
                .anyMatch(extension -> downloadInfo.getOriginalFileName().endsWith(extension));

        if (isDuplicate || hasForbiddenExtension) {
            log.info("File is either duplicate or has forbidden extension: {}", downloadInfo.getOriginalFileName());
            packageReport.getFileReports()
                    .add(new FileReport(downloadInfo.getOriginalFileName(), 0, false));
        }

        return !isDuplicate && !hasForbiddenExtension;
    }

    private static void submitTransfer(DownloadInfo downloadInfo, AtomicInteger occupiedCapacity, PackageReport packageReport) {
        log.debug("Submitting file {} for transfer", downloadInfo.getOriginalFileName());
        long startTime = System.currentTimeMillis();
        boolean success = false;

        try {
            if (occupiedCapacity.addAndGet(downloadInfo.getSize()) > Conf.maxCapacity) {
                log.error("File {} exceeds current transfer capacity with {}", downloadInfo.getOriginalFileName(), occupiedCapacity);
                throw new CapacityExceededException("Exceeded transfer capacity: " + occupiedCapacity);
            }

            InputStream dataStream = DownloadService.doDownload(downloadInfo.getDownloadURL());
            UploadService.doUpload(downloadInfo.getFileKey(), dataStream, downloadInfo.getSize());
            success = true;
        } catch (Exception e) {
            log.error("Transfer failed: {}", e.getMessage());
        } finally {
            occupiedCapacity.addAndGet(-downloadInfo.getSize());
            long endTime = System.currentTimeMillis();

            packageReport.getFileReports()
                    .add(new FileReport(downloadInfo.getOriginalFileName(), endTime - startTime, success));
            log.info("Completed file transfer of {} in {} milliseconds. Success: {}",
                    downloadInfo.getOriginalFileName(), endTime - startTime, success);
        }
    }

}