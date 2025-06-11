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
    
    // 完了フラグに応じて取得する
    @Select("SELECT * FROM todo WHERE user_id = #{userId} AND completed = #{isCompleted}")
    List<Todo> selectAllTodoByIdAndCompletedFlag(long userId, boolean isCompleted);

    // 完了フラグに応じて取得、優先度で並び替え(ASC)
    @Select("SELECT * FROM todo WHERE user_id = #{userId} AND completed = #{isCompleted} ORDER BY ${column} ${sort}")
    List <Todo> selectAllTodoByIdAndCompletedFlagAndColumnSort(long userId, boolean isCompleted, String column, String sort);

    // 一件削除
    @Delete("DELETE FROM todo WHERE todo_id = #{todoId}")
    void deleteTodoById(long todoId);

    // 一件追加
    @Insert("INSERT INTO todo (user_id, completed, title, priority, completion_date, created_at) VALUES (#{userId}, #{completed}, #{title}, #{priority}, #{completionDate}, #{createdAt})")
    @Options(useGeneratedKeys = true, keyProperty = "todoId")
    void insertTodo(Todo todo);

    // 完了フラグの変更
    @Update("UPDATE todo SET completed = #{completed} WHERE todo_id = #{todoId}")
    void updateCompletedStatus(long todoId, boolean completed);
}