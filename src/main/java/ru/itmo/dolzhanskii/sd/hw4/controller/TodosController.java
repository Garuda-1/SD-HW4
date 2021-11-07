package ru.itmo.dolzhanskii.sd.hw4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itmo.dolzhanskii.sd.hw4.dao.TodosDao;

@Controller
public class TodosController {

    private final TodosDao todosDao;

    @Autowired
    public TodosController(TodosDao todosDao) {
        this.todosDao = todosDao;
    }

    @GetMapping("/todos-lists")
    String getTodosLists(ModelMap modelMap) {
        updateModelMap(modelMap);
        return "index";
    }

    @PostMapping("/todos-lists")
    String addTodosList() {
        todosDao.addTodosList();
        return "redirect:/todos-lists";
    }

    @PostMapping("/todos-lists/{todosListId}/delete")
    String deleteTodosList(@PathVariable long todosListId) {
        todosDao.deleteTodosList(todosListId);
        return "redirect:/todos-lists";
    }

    @PostMapping("/todos-lists/{todosListId}/add")
    String addTodo(@PathVariable long todosListId, String name) {
        todosDao.addTodo(todosListId, name);
        return "redirect:/todos-lists";
    }

    @PostMapping("/todos-lists/{todosListId}/mark-done")
    String markTodoDone(@PathVariable long todosListId, Long id) {
        todosDao.markTodoDone(todosListId, id);
        return "redirect:/todos-lists";
    }

    private void updateModelMap(ModelMap map) {
        map.addAttribute("todosLists", todosDao.getTodosLists());
    }
}
