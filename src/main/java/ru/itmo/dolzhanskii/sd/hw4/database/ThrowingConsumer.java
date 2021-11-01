package ru.itmo.dolzhanskii.sd.hw4.database;

@FunctionalInterface
public interface ThrowingConsumer<T> {
    void accept(T t) throws Exception;
}
