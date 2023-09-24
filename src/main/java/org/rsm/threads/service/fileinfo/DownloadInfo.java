package org.rsm.threads.service.fileinfo;

public interface DownloadInfo {
    int getSize(); //bytes

    String getOriginalFileName();

    String getFileKey();

    String getDownloadURL();
}
