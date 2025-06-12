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
                         @RequestParam(value = "column", required = false) String column,
                         @RequestParam(value = "order", required = false) String order) {
        
        List<Todo> notCompletedTodos;
        List<Todo> completedTodos;
        // ソートのcolumnに指定があったらソートを有効にする
        boolean isSort = column != null && !column.isEmpty() ? true : false;
        // ソートのcolumn指定が無かったら空白で処理する
        column = column != null && !column.isEmpty() ? column : "";
        // ソートのorder指定が無かったらASCで処理する
        order = order != null && !order.isEmpty() ? order : "ASC";
        
        // 未完了のTodoを取得する
        notCompletedTodos = todoService.selectAllTodoByIdService(userDetails.getUserId(), false, column, isSort, order);
        // 完了済みのTodoを取得する
        completedTodos = todoService.selectAllTodoByIdService(userDetails.getUserId(), true, column, isSort, order);

        // formに使うTodoObjectを渡す。
        Todo todo = new Todo();
        // Todoの優先度の初期値を4にする。
        todo.setPriority(4);

        model.addAttribute("notCompletedTodos", notCompletedTodos);
        model.addAttribute("completedTodos", completedTodos);
        model.addAttribute("todo", todo);
        model.addAttribute("column", column);
        model.addAttribute("order", order);

        return "todo-list";
    }

    @PostMapping("/new")
    public String createTodo(@AuthenticationPrincipal CustomUserDetails userDetails, Todo todo,
                             @RequestParam(value = "column", required = false) String column,
                             @RequestParam(value = "order", required = false) String order) {
        todoService.createTodo(userDetails, todo);

        // columnに文字が入っていたらソート込みでリダイレクトする。
        if (column != null && !column.isEmpty()) {
            return String.format("redirect:/nagomi?column=%s&order=%s", column, order);
        }

        return "redirect:/nagomi";
    }
    
    @PostMapping("{todoId}/complete")
    public String completeTodo(@PathVariable long todoId, 
                               @RequestParam(value = "column", required = false) String column,
                               @RequestParam(value = "order", required = false) String order) {
        todoService.completed(todoId);

        // columnに文字が入っていたらソート込みでリダイレクトする。
        if (column != null && !column.isEmpty()) {
            return String.format("redirect:/nagomi?column=%s&order=%s", column, order);
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
