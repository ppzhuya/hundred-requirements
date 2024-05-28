package org.ppzhu.hundredrequirements.exception;


public class UserExistException extends RuntimeException{
    //用户已存在异常
    public UserExistException(String message){
        super(message);
    }

    public UserExistException() {
        super("用户已存在");
    }
}
