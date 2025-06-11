package com.example.todo.todo.Service;

import java.util.List;
import com.example.todo.todo.security.CustomUserDetailsService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import com.example.todo.todo.entity.Todo;
import com.example.todo.todo.repository.TodoRepository;
import com.example.todo.todo.security.CustomUserDetails;

@Service
public class TodoService {

    private final CustomUserDetailsService customUserDetailsService;
    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository, CustomUserDetailsService customUserDetailsService) {
        this.todoRepository = todoRepository;
        this.customUserDetailsService = customUserDetailsService;
    }

    public List<Todo> selectAllTodoByIdService(long userId, boolean isCompleted, String column, boolean isSort, String sort) {
        // 並び替えが有効だったら
        if (isSort) {
            return todoRepository.selectAllTodoByIdAndCompletedFlagAndColumnSort(userId, isCompleted, column, sort);
        } else {
            return todoRepository.selectAllTodoByIdAndCompletedFlag(userId, isCompleted);
        }
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
