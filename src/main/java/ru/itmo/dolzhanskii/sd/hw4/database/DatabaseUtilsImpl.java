package ru.itmo.dolzhanskii.sd.hw4.database;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

@Service
public class DatabaseUtilsImpl implements DatabaseUtils {

    @Value("${spring.datasource.url}")
    private String datasourceUrl;

    @Value("${spring.datasource.username}")
    private String datasourceUser;

    @Value("${spring.datasource.password}")
    private String datasourcePwd;

    public void executeUpdate(String sql) {
        try (Connection c = getConnection()) {
            Statement stmt = c.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void executeQuery(String sql, ThrowingConsumer<ResultSet> resultSetConsumer) {
        try (Connection c = getConnection()) {
            Statement stmt = c.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);
            resultSetConsumer.accept(resultSet);
            resultSet.close();
            stmt.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Connection getConnection() throws SQLException {
        Properties properties = new Properties();
        properties.setProperty("user", datasourceUser);
        properties.setProperty("password", datasourcePwd);
        return DriverManager.getConnection(datasourceUrl, properties);
    }
}