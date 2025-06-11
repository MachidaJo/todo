package com.example.todo.todo.Service;

import com.example.todo.todo.entity.User;
import com.example.todo.todo.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

    // ユーザー作成
    public void createUser(User user) {
        // 既にユーザーが存在するかチェック
        if (userRepository.selectUserByUsername(user.getUsername()) != null) {
            throw new IllegalArgumentException("Username already exists");
        }
        // パスワードをハッシュ化
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        userRepository.insertUser(user);
    }
}