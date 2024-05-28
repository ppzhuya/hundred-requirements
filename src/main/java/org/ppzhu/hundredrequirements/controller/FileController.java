package org.ppzhu.hundredrequirements.controller;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.ppzhu.hundredrequirements.pojo.ResponseData;
import org.ppzhu.hundredrequirements.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
@RequestMapping("/file")
public class FileController {

    private final FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/upload")
    public ResponseEntity<ResponseData> upload(@RequestParam("file") MultipartFile file) {
        boolean uploadResult = fileService.uploadFile(file);
        if (uploadResult) {
            return ResponseEntity.ok(ResponseData.success("上传成功"));
        } else {
            return ResponseEntity.ok(ResponseData.error("上传失败", 500));
        }
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity download(@PathVariable String fileName, HttpServletResponse response) {
        File file = fileService.downloadFile(fileName);
        if (file != null) {
            try {
                response.reset();
                response.setContentType("application/octet-stream");
                response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
                response.setContentLength((int)file.length());
                response.setCharacterEncoding("UTF-8");
                ServletOutputStream outputStream = response.getOutputStream();
                try(BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file))) {

                    byte[] buffer = new byte[1024];

                    int len;

                    while ((len = bufferedInputStream.read(buffer)) > 0) {

                        outputStream.write(buffer, 0, len);

                        outputStream.flush();
                    }

                    outputStream.close();
                    bufferedInputStream.close();
                    return ResponseEntity.ok(ResponseData.success("下载成功"));
                }
            } catch (IOException e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
