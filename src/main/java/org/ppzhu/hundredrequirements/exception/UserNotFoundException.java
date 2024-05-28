package org.ppzhu.hundredrequirements.exception;

/**
 * 用户不存在时异常
 */
public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException() {
        super("用户不存在");
    }
}
