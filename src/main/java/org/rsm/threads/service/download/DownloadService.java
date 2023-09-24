package org.rsm.threads.service.download;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Random;

public class DownloadService {
    public static InputStream doDownload(String downloadURL) throws IOException {
        try {
            Thread.sleep(new Random().nextInt(3) * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return new BufferedInputStream(
                Path.of("./src/main/resources/file")
                        .toUri().toURL().openStream());
    }
}
