package org.ppzhu.hundredrequirements.dao;

import org.ppzhu.hundredrequirements.pojo.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UserDao {

    // 添加用户，返回受影响的行数
    int addUser(User user) throws SQLException;

    // 删除用户，返回受影响的行数
    int deleteUser(String uid) throws SQLException;

    // 更新用户信息，返回受影响的行数
    int updateUser(User user) throws SQLException;

    // 查询用户信息，返回Optional包装的User对象
    Optional<User> queryUser(String uid) throws SQLException;

    // 查询所有用户，返回用户列表
    List<User> queryAllUser() throws SQLException;

    boolean deleteUserByUid(String uid) throws  SQLException;

    // 查询用户名是否存在，返回true或false
    boolean queryUserNameExist(String username) throws SQLException;

    Optional<User> queryUserByUserName(String username) throws  SQLException;
}
