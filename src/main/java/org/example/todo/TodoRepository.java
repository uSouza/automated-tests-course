package org.example.todo;

import java.util.List;
import java.util.Optional;

interface TodoRepository {
    Optional<TodoItem> findById(long id);
    TodoItem save(TodoItem todoItem);
    List<TodoItem> findAll();
    boolean delete(long id);
}
