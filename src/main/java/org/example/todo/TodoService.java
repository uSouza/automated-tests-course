package org.example.todo;

import java.util.List;

class TodoService {

    private final TodoRepository todoRepository;

    static class TodoItemNotFoundException extends RuntimeException {
        TodoItemNotFoundException() {
            super("Todo Item not found.");
        }
    }

    TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    TodoItem save(TodoItem todoItem) {
        if (todoItem == null) {
            throw new IllegalArgumentException("todoItem must not be null.");
        }
        return todoRepository.save(todoItem);
    }

    TodoItem findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id must not be null.");
        }
        return todoRepository.findById(id).orElseThrow(TodoItemNotFoundException::new);
    }

    List<TodoItem> findAll() {
        return todoRepository.findAll();
    }

    boolean delete(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id must not be null.");
        }
        return todoRepository.delete(id);
    }

    void complete(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id must not be null.");
        }
        var todo = todoRepository.findById(id).orElseThrow(TodoItemNotFoundException::new);
        todo.complete();
        todoRepository.save(todo);
    }

}
