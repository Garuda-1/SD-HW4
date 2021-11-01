package ru.itmo.dolzhanskii.sd.hw4.database;

import org.springframework.stereotype.Service;

import java.sql.ResultSet;

@Service
public interface DatabaseUtils {

    void executeUpdate(String sql);

    void executeQuery(String sql, ThrowingConsumer<ResultSet> resultSetConsumer);
}
