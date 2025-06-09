package com.example.todo.todo.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.todo.todo.Service.TodoService;
import com.example.todo.todo.entity.Todo;

@Controller
@RequestMapping("/nagomi")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping()
    public String nagomi(Model model) {
        // TODO ログイン中のIDを取得したい
        List<Todo> todos = todoService.selectAllTodoById(0L);
        Todo todo = new Todo();

        model.addAttribute("todos", todos);
        model.addAttribute("todo", todo);
        return "todo-list";
    }

    @PostMapping("/new")
    public String createTodo(Todo todo) {
        todoService.createTodo(todo);
        return "redirect:/nagomi";
    }
    
    @PostMapping("{todoId}/complete")
    public String completeTodo(@PathVariable long todoId) {
        todoService.completed(todoId);

        return "redirect:/nagomi";
    }

    @PostMapping("{todoId}/deComplete")
    public String deCompleteTodo(@PathVariable long todoId) {
        todoService.deCompleted(todoId);
        
        return "redirect:/nagomi";
    }

    @PostMapping("{todoId}/delete")
    public String deleteTodo(@PathVariable long todoId) {
        todoService.deleteTodo(todoId);

        return "redirect:/nagomi";
    }
}
