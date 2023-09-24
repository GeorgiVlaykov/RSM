package org.rsm.threads.service.fileinfo;

import lombok.extern.slf4j.Slf4j;
import org.rsm.threads.util.TestDataGenerator;

import java.util.List;

@Slf4j
public class FileInfoService {
    public static List<DownloadInfo> getDownloadInfos(long packageId) {
        log.info("Get package with ID: {}", packageId);
        return TestDataGenerator.createDownloadInfos();
    }
}
