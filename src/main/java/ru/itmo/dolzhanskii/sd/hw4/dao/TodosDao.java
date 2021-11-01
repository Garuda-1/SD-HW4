package ru.itmo.dolzhanskii.sd.hw4.dao;

import org.springframework.stereotype.Service;
import ru.itmo.dolzhanskii.sd.hw4.model.TodosList;

import java.util.List;

@Service
public interface TodosDao {

    List<TodosList> getTodosLists();

    long addTodosList();

    void deleteTodosList(long id);

    long addTodo(long todosListId, String name);

    void markTodoDone(long todosListId, long todoId);
}
