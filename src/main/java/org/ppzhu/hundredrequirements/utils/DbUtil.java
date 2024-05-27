package org.ppzhu.hundredrequirements.utils;

import jakarta.annotation.PostConstruct;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

@Component
public class DbUtil {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    private static class Config {
        private static String url;
        private static String username;
        private static String password;
        private static String driverClassName;
    }

    @PostConstruct
    public void init() {
        Config.url = this.url;
        Config.username = this.username;
        Config.password = this.password;
        Config.driverClassName = this.driverClassName;

        try {
            Class.forName(Config.driverClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(Config.url, Config.username, Config.password);
    }

    /**
     * 关闭数据库连接
     * @param connection
     */
    public static void closeConnection(Connection connection) {
        DbUtils.closeQuietly(connection);
    }

    public static <T> List<T> executeQuery(String sql, Class<T> type, Object... params) throws SQLException {
        Connection connection = getConnection();

        try {
            QueryRunner queryRunner = new QueryRunner();
            return queryRunner.query(connection, sql, new BeanListHandler<>(type), params);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            closeConnection(connection);
        }
    }

    public static int executeUpdate(String query, Object... params) throws SQLException {
        Connection connection = getConnection();

        try {
            QueryRunner runner = new QueryRunner();
            return runner.update(connection, query, params);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            closeConnection(connection);
        }
    }
}
