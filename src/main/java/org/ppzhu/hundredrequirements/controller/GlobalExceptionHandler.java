package org.ppzhu.hundredrequirements.controller;

import org.ppzhu.hundredrequirements.exception.UserExistException;
import org.ppzhu.hundredrequirements.pojo.ResponseData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserExistException.class)
    public ResponseEntity<ResponseData> handleUserExistException(UserExistException ex) {
        // 在这里可以返回自定义的错误信息或者其他处理逻辑
        return ResponseEntity.ok(ResponseData.error(ex.getMessage(), 409));
    }

    // 其他异常处理逻辑可以继续添加在这里
}
