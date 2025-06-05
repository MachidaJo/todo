package com.example.todo.todo.Service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.todo.todo.entity.Todo;
import com.example.todo.todo.repository.TodoRepository;

@Service
public class TodoService {
    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> getAllTodos() {
        return todoRepository.getTodoId();
    }

    public void createTodo(Todo todo) {
        todo.setCreatedDate(LocalDate.now());
        todoRepository.insertTodo(todo);
    }

    public void deleteTodo(long todoId) {
        todoRepository.deleteTodoById(todoId);
    }
}
