package org.ppzhu.hundredrequirements.service.impl;

import org.ppzhu.hundredrequirements.dao.UserDao;
import org.ppzhu.hundredrequirements.exception.UserExistException;
import org.ppzhu.hundredrequirements.pojo.User;
import org.ppzhu.hundredrequirements.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDao userDao, BCryptPasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public boolean register(User user) throws UserExistException{
        try {
            if (userDao.queryUserNameExist(user.getUname())) {
                throw new UserExistException("用户名已存在");
            }
            user.setUpwd(passwordEncoder.encode(user.getUpwd()));
            return userDao.addUser(user) > 0;
        } catch (SQLException e) {
            // Log the exception (consider using a logger framework like SLF4J)
            throw new RuntimeException("注册时数据库出错", e);
        }
    }

    @Override
    public Optional<User> login(String username, String password) {
        try {
            Optional<User> user = userDao.queryUserByUserName(username);
            if (user.isPresent() && passwordEncoder.matches(password, user.get().getUpwd())) {
                return user;
            }
        } catch (SQLException e) {
            // Log the exception (consider using a logger framework like SLF4J)
            throw new RuntimeException("登录时数据库出错", e);
        }
        return Optional.empty();
    }
}
