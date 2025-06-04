package com.example.todo.todo.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class TodoRepository {
    private final TodoMapper todoMapper;

    public TodoRepository(TodoMapper todoMapper) {
        this.todoMapper = todoMapper;
    }

    public List<Todo> getTodoId(long todoId) {
        return todoMapper.selectAllTodo();
    }

    public void insertTodo(Todo todo) {
        todoMapper.insertTodo(todo);
    } 

    public void deleteTodoById(long todoId) {
        todoMapper.deleteTodoById(todoId);  
    }

}
