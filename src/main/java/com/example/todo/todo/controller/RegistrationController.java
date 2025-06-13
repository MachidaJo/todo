package com.example.todo.todo.controller;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.todo.todo.entity.Todo;
import com.example.todo.todo.entity.User;
import com.example.todo.todo.Service.TodoService;
import com.example.todo.todo.Service.UserService;

@Controller
public class RegistrationController {
    private final UserService userService;
    private final TodoService todoService;

    public RegistrationController(UserService userService, TodoService todoService) {
        this.userService = userService;
        this.todoService = todoService;
    }

    // GETリクエストで登録フォームを表示
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    // POSTリクエストでユーザー登録処理
    @PostMapping("/register")
    public String registerUser(User user, Model model, RedirectAttributes redirectAttributes) {
        if (user.getUsername().isBlank() || user.getPassword().isBlank()) {
            model.addAttribute("BlankError", "ユーザー名とパスワードは必須です");
            return "register";
        } else if (user.getUsername().length() < 4 || user.getUsername().length() > 100){
            model.addAttribute("HasUserNameError", "ユーザー名は4~100文字以内です");
            return "register";
        } else if (user.getPassword().length() < 8 || user.getPassword().length() > 100){
            model.addAttribute("HasPasswordError", "パスワードは8~100文字以内です");
            return "register";
        }
        
        try {
            // ユーザーを追加
            userService.createUser(user);

            // デフォルトで追加されるTodoタスク
            Todo defaultTodo = new Todo();
            defaultTodo.setTitle("ここに予定が表示されます。");
            defaultTodo.setPriority(4);
            defaultTodo.setCompletionDate(LocalDate.now());
            //Todoタスクを追加
            todoService.createTodo(user, defaultTodo);
            // リダイレクト先でメッセージを表示するための設定
            redirectAttributes.addFlashAttribute("message", "アカウントの作成に成功しました。");

            return "redirect:login";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            // エラー時再表示
            return "register";
        }
    }
}
