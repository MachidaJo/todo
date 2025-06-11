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

        // 未完了のみのtodoをリストにする
        List<Todo> notCompletedTodos = todoService.selectAllTodoByIdAndCompletedFlag(userDetails.getUserId(), false);
        // 完了のみのtodoをリストにする
        List<Todo> completedTodos = todoService.selectAllTodoByIdAndCompletedFlag(userDetails.getUserId(), true);
        // formに使うTodoObjectを渡す。
        Todo todo = new Todo();
        // Todoの優先度の初期値を4にする。
        todo.setPriority(4);
        
        // 優先度でソートする
        if (sort != null && sort.equals("priority")) {
            notCompletedTodos = notCompletedTodos.stream()
                                                .sorted((x, y) -> x.getPriority() - y.getPriority())
                                                .toList();

            completedTodos = completedTodos.stream()
                                            .sorted((x, y) -> x.getPriority() - y.getPriority())
                                            .toList();
        }

        model.addAttribute("notCompletedTodos", notCompletedTodos);
        model.addAttribute("completedTodos", completedTodos);
        model.addAttribute("todo", todo);
        model.addAttribute("sort", sort);

        return "todo-list";
    }

    @PostMapping("/new")
    public String createTodo(@AuthenticationPrincipal CustomUserDetails userDetails, Todo todo,
                             @RequestParam(value = "sort", required = false) String sort) {
        todoService.createTodo(userDetails, todo);

        // sortに文字が入っれいたらソート込みでリダイレクトする。
        if (!sort.isEmpty()) {
            return "redirect:/nagomi?sort=" + sort;
        }

        return "redirect:/nagomi";
    }
    
    @PostMapping("{todoId}/complete")
    public String completeTodo(@PathVariable long todoId, 
                               @RequestParam(value = "sort", required = false) String sort) {
        todoService.completed(todoId);

        // sortに文字が入っれいたらソート込みでリダイレクトする。
        if (!sort.isEmpty()) {
            return "redirect:/nagomi?sort=" + sort;
        }

        return "redirect:/nagomi";
    }

    @PostMapping("{todoId}/deComplete")
    public String deCompleteTodo(@PathVariable long todoId,
                                 @RequestParam(value = "sort", required = false) String sort) {
        todoService.deCompleted(todoId);
        
        // sortに文字が入っれいたらソート込みでリダイレクトする。
        if (sort != null && !sort.isEmpty()) {
            return "redirect:/nagomi?sort=" + sort;
        }

        return "redirect:/nagomi";
    }

    @PostMapping("{todoId}/delete")
    public String deleteTodo(@PathVariable long todoId,
                             @RequestParam(value = "sort", required = false) String sort) {
        todoService.deleteTodo(todoId);

        // sortに文字が入っれいたらソート込みでリダイレクトする。
        if (sort != null && !sort.isEmpty()) {
            return "redirect:/nagomi?sort=" + sort;
        }
        
        return "redirect:/nagomi";
    }
}
