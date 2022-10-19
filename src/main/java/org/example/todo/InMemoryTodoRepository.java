package org.example.todo;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

final class InMemoryTodoRepository implements TodoRepository {

    private final Map<Long, TodoItem> todoItems;
    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    InMemoryTodoRepository() {
        todoItems = new HashMap<>();
    }

    InMemoryTodoRepository(Map<Long, TodoItem> todoItems) {
        this.todoItems = todoItems;
    }

    private static Long getRandomId() {
        return SECURE_RANDOM.nextLong() & Long.MAX_VALUE;
    }

    @Override
    public Optional<TodoItem> findById(long id) {
        return Optional.ofNullable(todoItems.get(id));
    }

    @Override
    public TodoItem save(TodoItem todoItem) {
        if (todoItems.containsKey(todoItem.getId())) {
            todoItems.replace(todoItem.getId(), todoItem);
            return todoItem;
        }
        todoItem.setId(getRandomId());
        todoItems.put(todoItem.getId(), todoItem);

        return todoItem;
    }

    @Override
    public List<TodoItem> findAll() {
        return todoItems.values().stream().toList();
    }

    @Override
    public boolean delete(long id) {
        return todoItems.remove(id) != null;
    }
}
