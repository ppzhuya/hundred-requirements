package org.ppzhu.hundredrequirements.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Path;

/**
 * 该类负责文件的相关操作
 */
@Service
public interface FileService {
    boolean uploadFile(MultipartFile file);

    File downloadFile(String fileName);
}
