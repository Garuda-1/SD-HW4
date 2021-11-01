package ru.itmo.dolzhanskii.sd.hw4.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.dolzhanskii.sd.hw4.database.DatabaseUtils;
import ru.itmo.dolzhanskii.sd.hw4.model.Todo;
import ru.itmo.dolzhanskii.sd.hw4.model.TodosList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TodosDaoImpl implements TodosDao {

    private final DatabaseUtils databaseUtils;

    @Autowired
    public TodosDaoImpl(DatabaseUtils databaseUtils) {
        this.databaseUtils = databaseUtils;
    }

    @Override
    public List<TodosList> getTodosLists() {
        Map<Long, TodosList> todosLists = new HashMap<>();
        databaseUtils.executeQuery("SELECT * FROM todoslists", resultSet -> {
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                TodosList todosList = new TodosList();
                todosList.setId(id);
                todosLists.put(id, todosList);
            }
        });
        databaseUtils.executeQuery("SELECT * FROM todoslists NATURAL JOIN todos", resultSet -> {
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                long todosListId = resultSet.getLong("todos_list_id");
                String name = resultSet.getString("name");
                boolean done = resultSet.getBoolean("done");
                Todo todo = new Todo();
                todo.setId(id);
                todo.setName(name);
                todo.setDone(done);
                todosLists.get(todosListId).addTodo(todo);
            }
        });
        return new ArrayList<>(todosLists.values());
    }

    @Override
    public long addTodosList() {
        AtomicLong result = new AtomicLong();
        databaseUtils.executeQuery("INSERT INTO TodosLists VALUES (default) RETURNING id", resultSet -> {
            resultSet.next();
            result.set(resultSet.getLong("id"));
        });
        return result.get();
    }

    @Override
    public void deleteTodosList(long id) {
        databaseUtils.executeUpdate("DELETE FROM TodosLists WHERE id = " + id);
    }

    @Override
    public long addTodo(long todosListId, String name) {
        AtomicLong result = new AtomicLong();
        databaseUtils.executeQuery(String.format("INSERT INTO Todos (todos_list_id, name) VALUES (%d, '%s') RETURNING id", todosListId, name), resultSet -> {
            resultSet.next();
            result.set(resultSet.getLong("id"));
        });
        return result.get();
    }

    @Override
    public void markTodoDone(long todosListId, long todoId) {
        databaseUtils.executeUpdate(String.format("UPDATE Todos SET done = TRUE WHERE todos_list_id = %d AND id = %s", todosListId, todoId));
    }
}
