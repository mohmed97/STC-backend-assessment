package com.managefiles.dto;

import org.springframework.web.multipart.MultipartFile;

public class FileDTO {
    String fileName;
    MultipartFile file;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
