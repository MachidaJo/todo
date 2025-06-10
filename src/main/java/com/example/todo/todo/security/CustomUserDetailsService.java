package com.example.todo.todo.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.todo.todo.entity.User;
import com.example.todo.todo.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // ログイン認証に利用するユーザを取得するメソッドをオーバーライド
    // UserDetailsインターフェースを実装したクラスを戻り値として返す
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.selectUserByUsername(username);

        // 対応するユーザ名が見つからない場合、例外となる
        if(user == null) {
            throw new UsernameNotFoundException("user not found");
        }

        // 
        return new CustomUserDetails(user);
    }
}
