package org.ppzhu.hundredrequirements.service;

import org.ppzhu.hundredrequirements.pojo.User;
import java.util.Optional;

public interface UserService {
    // 注册用户
    boolean register(User user);

    // 登录用户
    Optional<User> login(String username, String password);

    //注销
    boolean logout(String token);

    boolean deleteUser(String uid);

    boolean resetPassword(String uid , String oldPassword, String newPassword);
}
