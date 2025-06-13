package com.example.todo.todo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.todo.todo.entity.User;
import com.example.todo.todo.Service.UserService;

@Controller
public class RegistrationController {
    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    // GETリクエストで登録フォームを表示
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    // POSTリクエストでユーザー登録処理
    @PostMapping("/register")
    public String registerUser(User user, Model model) {
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
            userService.createUser(user);
            // 登録成功時リダイレクト
            return "login";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            // エラー時再表示
            return "register";
        }
    }
}
