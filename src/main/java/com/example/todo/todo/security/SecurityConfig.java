package com.example.todo.todo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       http // ログインしていない状態でどのページにもアクセスできなくなる
            .authorizeHttpRequests(request -> request
            // リクエストの特定の条件に一致するかどうかを判定
                .requestMatchers("/css/**", "/images/**", "/js/**", "/register/**").permitAll()
                .anyRequest().authenticated())
            .formLogin(login -> login
                // ログイン認証の送信先URL（POST送信）
                .loginProcessingUrl("/login") 
                // ログインページのURL（GET送信）
                .loginPage("/login") 
                // 認証成功時に遷移するページ
                .defaultSuccessUrl("/nagomi") 
                // 認証失敗時に遷移するページ
                .failureUrl("/error") 
                // ログインページを認証不要でアクセス可能にする設定
                .permitAll() 
            )
            .logout(logout -> logout
                // ログアウト後に遷移するページ
                .logoutSuccessUrl("/login")
                .permitAll()
            );
        return http.build();
    }

    // ＠BeanによってDIコンテナに登録され、他のクラスでDIを利用できる
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
