package com.example.todo.todo.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.todo.todo.entity.User;


public class CustomUserDetails implements UserDetails{
    // ユーザ名とパスワードを保持するためのフィールド
    private final User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    // ユーザごとの持っている権限の一覧を返すメソッド（ロール）
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    // ユーザ名とパスワードとユーザIDを返すメソッド
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    public long getUserId() {
        return user.getUserId();
    }
}
