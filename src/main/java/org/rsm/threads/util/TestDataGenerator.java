package org.rsm.threads.util;

import com.github.javafaker.Faker;
import org.rsm.threads.service.fileinfo.DownloadInfo;
import org.rsm.threads.service.fileinfo.FileInfo;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.rsm.threads.conf.Conf.MB;

public class TestDataGenerator {
    private static final Faker faker = new Faker();

    public static FileInfo createDownloadInfo() {
        return FileInfo.builder()
                .originalFileName(faker.file().fileName())
                .fileKey(faker.file().fileName())
                .size(faker.random().nextInt(5 * MB, 50 * MB))
                .downloadURL(faker.internet().url()).build();
    }

    public static List<DownloadInfo> createDownloadInfos() {
//        FileInfo duplicateInfo = TestDataGenerator.createDownloadInfo();
        return IntStream.range(1, faker.random().nextInt(100))
                .mapToObj(index -> TestDataGenerator.createDownloadInfo())
//                .mapToObj(index -> duplicateInfo)
                .collect(Collectors.toList());
    }
}
