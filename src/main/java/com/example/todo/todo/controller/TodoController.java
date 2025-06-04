package com.example.todo.todo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.todo.todo.Service.TodoService;
import com.example.todo.todo.entity.Todo;

@Controller
@RequestMapping("/todo")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping()
    public String todo(Model model) {
        List<Todo> todos = todoService.getAllTodos();
        model.addAttribute("todos", todos);
        return "todo-list";
    }

    @PostMapping("/new")
    public String createTodo(Todo todo) {
        todoService.createTodo(todo);
        return "redirect:/todo";
    }

    @PostMapping("{todoId}/delete")
    public String deleteTodo(@PathVariable long todoId) {
        todoService.deleteTodo(todoId);

        return "redirect:/todo";
    }
}
