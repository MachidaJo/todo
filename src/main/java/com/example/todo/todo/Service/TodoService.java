package com.example.todo.todo.Service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import com.example.todo.todo.entity.Todo;
import com.example.todo.todo.repository.TodoRepository;
import com.example.todo.todo.security.CustomUserDetails;

@Service
public class TodoService {
    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> selectAllTodoById(long todoId) {
        return todoRepository.selectAllTodoById(todoId);
    }

    public void createTodo(@AuthenticationPrincipal CustomUserDetails userDetails, Todo todo) {
        // ユーザーIDと未完了フラグにして作成
        todo.setUserId(userDetails.getUserId());
        todo.setCompleted(false);
        todoRepository.insertTodo(todo);
    }

    public void completed(long todoId) {
        todoRepository.completed(todoId, true);
    }

    public void deCompleted(long todoId) {
        todoRepository.completed(todoId, false);
    }

    public void deleteTodo(long todoId) {
        todoRepository.deleteTodoById(todoId);
    }
}
