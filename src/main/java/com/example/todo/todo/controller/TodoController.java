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
        Todo todo = new Todo();

        model.addAttribute("todos", todos);
        model.addAttribute("todo", todo);
        return "todo-list";
    }

    @PostMapping("/new")
    public String createTodo(Todo todo) {
        todoService.createTodo(todo);
        return "redirect:/todo";
    }
    
    @PostMapping("{todoId}/complete")
    public String completeTodo(@PathVariable long todoId) {
        todoService.completed(todoId);
    }

    @PostMapping("{todoId}/deComplete")
    public String deCompleteTodo(@PathVariable long todoId) {
        todoService.deCompleted(todoId);
    }

    @PostMapping("{todoId}/delete")
    public String deleteTodo(@PathVariable long todoId) {
        todoService.deleteTodo(todoId);

        return "redirect:/todo";
    }
}
