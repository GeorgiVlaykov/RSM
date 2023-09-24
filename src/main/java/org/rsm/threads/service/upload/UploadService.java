package org.rsm.threads.service.upload;

import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.util.Random;

@Slf4j
public class UploadService {

    public static void doUpload(String key, InputStream data, int size) {
        try {
            Thread.sleep(new Random().nextInt(3) * 500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
