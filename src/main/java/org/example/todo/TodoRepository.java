package org.example.todo;

import java.util.List;

interface TodoRepository {
    TodoItem findById(long id);
    TodoItem save(TodoItem todoItem);
    List<TodoItem> findAll();
    boolean delete(long id);
}
