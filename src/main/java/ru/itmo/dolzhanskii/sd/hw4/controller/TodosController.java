package ru.itmo.dolzhanskii.sd.hw4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itmo.dolzhanskii.sd.hw4.dao.TodosDao;
import ru.itmo.dolzhanskii.sd.hw4.model.TodosList;

import java.util.List;

@RestController
public class TodosController {

    private final TodosDao todosDao;

    @Autowired
    public TodosController(TodosDao todosDao) {
        this.todosDao = todosDao;
    }

    @GetMapping("/todos-lists")
    List<TodosList> getTodosLists() {
        return todosDao.getTodosLists();
    }

    @PostMapping("/todos-lists")
    long addTodosList() {
        return todosDao.addTodosList();
    }

    @DeleteMapping("/todos-lists/{todosListId}")
    void deleteTodosList(@PathVariable long todosListId) {
        todosDao.deleteTodosList(todosListId);
    }

    @PostMapping("/todos-lists/{todosListId}/add")
    long addTodo(@PathVariable long todosListId, String name) {
        return todosDao.addTodo(todosListId, name);
    }

    @PostMapping("/todos-lists/{todosListId}/mark-done")
    void markTodoDone(@PathVariable long todosListId, long id) {
        todosDao.markTodoDone(todosListId, id);
    }
}
