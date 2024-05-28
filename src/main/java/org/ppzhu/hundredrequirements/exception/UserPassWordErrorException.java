package org.ppzhu.hundredrequirements.exception;

public class UserPassWordErrorException extends RuntimeException{
    public UserPassWordErrorException() {
        super("密码错误");
    }

    public UserPassWordErrorException(String message) {
        super(message);
    }
}
