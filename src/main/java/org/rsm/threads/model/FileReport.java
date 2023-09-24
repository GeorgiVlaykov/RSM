package org.rsm.threads.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FileReport {
    private String fileName;
    private long timeToUpload;
    private boolean success;

    @Override
    public String toString() {
        return String.format("%s transfer %s in %s milliseconds",
                fileName,
                success ? "completed successfully" : "failed",
                timeToUpload
        );
    }
}
