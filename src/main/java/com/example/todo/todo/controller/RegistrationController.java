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
        try {
            userService.createUser(user);
            // 登録成功時リダイレクト
            return "redirect:/login?register";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            // エラー時再表示
            return "register";
        }
    }
}
