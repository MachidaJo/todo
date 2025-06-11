package com.example.todo.todo.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.todo.todo.entity.Todo;
import com.example.todo.todo.mapper.TodoMapper;

@Repository
public class TodoRepository {
    private final TodoMapper todoMapper;

    public TodoRepository(TodoMapper todoMapper) {
        this.todoMapper = todoMapper;
    }

    public List<Todo> selectAllTodoByIdAndCompletedFlag(long todoId, boolean isCompleted) {
        return todoMapper.selectAllTodoByIdAndCompletedFlag(todoId, isCompleted);
    }

    public List<Todo> selectAllTodoByIdAndCompletedFlagAndColumnSort(long userId, boolean isCompleted, String column, String sort) {
        return todoMapper.selectAllTodoByIdAndCompletedFlagAndColumnSort(userId, isCompleted, column, sort);
    }

    public void insertTodo(Todo todo) {
        todoMapper.insertTodo(todo);
    } 

    public void completed(long todoId,boolean completed) {
        todoMapper.updateCompletedStatus(todoId,completed);
    }

    public void deleteTodoById(long todoId) {
        todoMapper.deleteTodoById(todoId);  
    }

}
