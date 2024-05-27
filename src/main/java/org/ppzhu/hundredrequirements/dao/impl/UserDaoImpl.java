package org.ppzhu.hundredrequirements.dao.impl;

import org.ppzhu.hundredrequirements.dao.UserDao;
import org.ppzhu.hundredrequirements.pojo.User;
import org.ppzhu.hundredrequirements.utils.DbUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class UserDaoImpl implements UserDao {

    private final DbUtil dbUtil;

    @Autowired
    public UserDaoImpl(DbUtil dbUtil) {
        this.dbUtil = dbUtil;
    }

    @Override
    public int addUser(User user) throws SQLException {
        String sql = "INSERT INTO user(uid, uname, upwd) VALUES (?, ?, ?)";
        UUID uuid = UUID.randomUUID(); // 每次调用生成新的 UUID
        return dbUtil.executeUpdate(sql, uuid.toString(), user.getUname(), user.getUpwd());
    }

    @Override
    public int deleteUser(String uid) throws SQLException {
        String sql = "DELETE FROM user WHERE uid = ?";
        return dbUtil.executeUpdate(sql, uid);
    }

    @Override
    public int updateUser(User user) throws SQLException {
        String sql = "UPDATE user SET uname = ?, upwd = ? WHERE uid = ?";
        return dbUtil.executeUpdate(sql, user.getUname(), user.getUpwd(), user.getUid());
    }

    @Override
    public Optional<User> queryUser(String uid) throws SQLException {
        String sql = "SELECT * FROM user WHERE uid = ?";
        List<User> users = dbUtil.executeQuery(sql, User.class, uid);
        return users.isEmpty() ? Optional.empty() : Optional.of(users.get(0));
    }

    @Override
    public List<User> queryAllUser() throws SQLException {
        String sql = "SELECT * FROM user";
        return dbUtil.executeQuery(sql, User.class);
    }

    @Override
    public boolean queryUserNameExist(String username) throws SQLException {
        String sql = "SELECT * FROM user WHERE uname = ?";
        List<User> users = dbUtil.executeQuery(sql, User.class, username);
        if (users.isEmpty()){
            return false;
        }
        return true;
    }

    @Override
    public Optional<User> queryUserByUserName(String username) throws SQLException {
        String sql = "SELECT * FROM user WHERE uname = ?";
        List<User> users = dbUtil.executeQuery(sql, User.class, username);
        return users.isEmpty() ? Optional.empty() : Optional.of(users.get(0));
    }

}

