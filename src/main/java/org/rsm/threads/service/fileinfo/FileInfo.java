package org.rsm.threads.service.fileinfo;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FileInfo implements DownloadInfo {
    private int size;
    private String originalFileName;
    private String fileKey;
    private String downloadURL;
}
