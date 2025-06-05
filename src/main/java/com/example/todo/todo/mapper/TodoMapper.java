package com.example.todo.todo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import com.example.todo.todo.entity.Todo;


@Mapper
public interface TodoMapper {
    
    // 全件取得
    @Select("SELECT * FROM todo")
    List<Todo> selectAllTodo();

    // 一件削除
    @Delete("DELETE FROM todo WHERE todoId = #{todoId}")
    void deleteTodoById(long todoId);

    // 一件追加
    @Insert("INSERT INTO todo (completed, title, createdDate) VALUES (FALSE, #{title}, #{createdDate})")
    @Options(useGeneratedKeys = true, keyProperty = "todoId")
    void insertTodo(Todo todo);

    @Update("UPDATE todo SET completed = #{completed} WHERE todoId = #{todoId}")
    void updateCompletedStatus(long todoId, boolean completed);
}