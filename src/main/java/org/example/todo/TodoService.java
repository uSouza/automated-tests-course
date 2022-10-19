package org.example.todo;

import java.util.List;

class TodoService {

    private final TodoRepository todoRepository;

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
        return todoRepository.findById(id);
    }

    List<TodoItem> findAll() {
        return todoRepository.findAll();
    }

    boolean delete(Long id) {
        return todoRepository.delete(id);
    }

    void complete(Long id) {
        var todo = todoRepository.findById(id);
        todo.complete();
        todoRepository.save(todo);
    }

    public static void main(String[] args) {
        var todoService = new TodoService(new InMemoryTodoRepository());

        var todoItem = todoService.save(new TodoItem("Unit test remind", "Learn how to mock classes", "Programming", false));

        todoService.complete(todoItem.getId());

        System.out.println(todoItem.isDone());
    }
}
