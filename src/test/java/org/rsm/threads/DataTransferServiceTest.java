package org.rsm.threads;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.rsm.threads.model.PackageReport;
import org.rsm.threads.service.DataTransferService;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class DataTransferServiceTest {

    @Test
    void transferData() throws InterruptedException {
        PackageReport packageReport = DataTransferService.transferData(6665485);
        log.info("Package report: \n {}", packageReport);
    }
}