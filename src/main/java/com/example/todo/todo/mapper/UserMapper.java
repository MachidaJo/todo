package com.example.todo.todo.mapper;

import com.example.todo.todo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("SELECT user_id, username, password, created_at FROM users WHERE username = #{username}")
    User selectUserByUsername(String username);
}
