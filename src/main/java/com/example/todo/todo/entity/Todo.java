package com.example.todo.todo.entity;

import lombok.Data;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class Todo {
    private long todoId;
    private boolean completed;
    private String title;
    private int priority;
    private LocalDate completion_date;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdAt;
}
