package com.example.todo.todo.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.todo.todo.entity.User;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository UserRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.selectUserByUsername(username);

        if(user == null) {
            throw new UsernameNotFoundException("user not found");
        }

        return new CustomUserDetails(user);
    }
}
