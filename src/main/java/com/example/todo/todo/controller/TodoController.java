package com.example.todo.todo.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.todo.todo.Service.TodoService;
import com.example.todo.todo.entity.Todo;
import com.example.todo.todo.security.CustomUserDetails;

@Controller
@RequestMapping("/nagomi")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping()
    public String nagomi(@AuthenticationPrincipal CustomUserDetails userDetails, Model model,
                         @RequestParam(value = "sort", required = false) String sort) {

        List<Todo> todos = todoService.selectAllTodoById(userDetails.getUserId());
        Todo todo = new Todo();
        
        // 優先度でソートする
        if (sort != null && sort.equals("priority")) {
            todos = todos.stream()
                    .sorted((x, y) -> x.getPriority() - y.getPriority())
                    .toList();
        }

        // 未完了のみのtodoをリストにする
        List<Todo> notCompletedTodos = todos.stream()
                                            .filter(t -> t.isCompleted() == false)
                                            .toList();

        // 完了のみのtodoをリストにする
        List<Todo> completedTodos = todos.stream()
                                            .filter(t -> t.isCompleted() == true)
                                            .toList();

        model.addAttribute("notCompletedTodos", notCompletedTodos);
        model.addAttribute("completedTodos", completedTodos);
        model.addAttribute("todo", todo);


        return "todo-list";
    }

    @PostMapping("/new")
    public String createTodo(@AuthenticationPrincipal CustomUserDetails userDetails, Todo todo) {
        todoService.createTodo(userDetails, todo);
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
