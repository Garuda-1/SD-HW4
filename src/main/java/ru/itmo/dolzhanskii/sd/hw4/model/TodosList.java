package ru.itmo.dolzhanskii.sd.hw4.model;

import java.util.ArrayList;
import java.util.List;

public class TodosList {

    private long id;
    private final List<Todo> todos = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Todo> getTodos() {
        return todos;
    }

    public void addTodo(Todo todo) {
        this.todos.add(todo);
    }
}
