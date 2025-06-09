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
    @Select("SELECT * FROM todo WHERE user_id = #{userId}")
    List<Todo> selectAllTodoById(long userId);

    // 一件削除
    @Delete("DELETE FROM todo WHERE todo_id = #{todoId}")
    void deleteTodoById(long todoId);

    // 一件追加
    @Insert("INSERT INTO todo (completed, title, created_at) VALUES (FALSE, #{title}, #{createdAt})")
    @Options(useGeneratedKeys = true, keyProperty = "todoId")
    void insertTodo(Todo todo);

    // 完了フラグの変更
    @Update("UPDATE todo SET completed = #{completed} WHERE todo_id = #{todoId}")
    void updateCompletedStatus(long todoId, boolean completed);
}