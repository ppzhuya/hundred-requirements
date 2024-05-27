package org.ppzhu.hundredrequirements;

import org.junit.jupiter.api.Test;
import org.ppzhu.hundredrequirements.pojo.User;
import org.ppzhu.hundredrequirements.utils.DbUtil;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@SpringBootTest
class HundredRequirementsApplicationTests {

    @Test
    void contextLoads() {
        UUID uuid = UUID.randomUUID();
        String sql = "insert into user values(?,?,?)";
        try {
            int ppzhu = DbUtil.executeUpdate(sql, uuid.toString(), "ppzhu", "123456");
            System.out.println(ppzhu);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void queryTest(){

        String sql = "select * from user";
        try {
            List<User> users = DbUtil.executeQuery(sql, User.class);
            users.forEach(System.out::println);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
