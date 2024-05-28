package org.ppzhu.hundredrequirements.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.ppzhu.hundredrequirements.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@Service
@Slf4j
public class FileServiceImpl implements FileService {
    private final Path filePath;

    @Autowired
    public FileServiceImpl(@Value("${file.upload-dir}")String filePath) {
        this.filePath = Path.of(filePath);
        try {
            File file = this.filePath.toFile();
            if (!file.exists()){
                Files.createDirectories(this.filePath);
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Could not initialize folder for upload");
        }
    }
    @Override
    public boolean uploadFile(MultipartFile file) {
        UUID uuid = UUID.randomUUID();
        String fileSuffix = getFileSuffix(file.getOriginalFilename());
        Path target = filePath.resolve(uuid + fileSuffix);
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
            Files.copy(inputStream,target);
            return  true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                if (inputStream != null){
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public File downloadFile(String fileName) {
        Path filePath = this.filePath.resolve(fileName);
        File file = null;
        try {
            file = filePath.toFile();
        }catch (Exception e){

        }
        return file;
    }


    private String getFileSuffix(String fileName){
        return  fileName.substring(fileName.lastIndexOf("."));
    }
}
