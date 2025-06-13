package com.example.todo.todo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class LoginController {
    // ログイン画面をルーティング
    @GetMapping("/login")
    public String login(@ModelAttribute("message") String message, Model model) {
        model.addAttribute("message", message);
        return "login";
    }
}
