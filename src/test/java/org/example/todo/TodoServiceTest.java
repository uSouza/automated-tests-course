package org.example.todo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
final class TodoServiceTest {

    private static TodoService createTodoService(TodoRepository todoRepository) {
        return new TodoService(todoRepository);
    }

    private static void assertTodoItem(TodoItem expected, TodoItem received) {
        assertEquals(expected.getId(), received.getId());
        assertEquals(expected.getName(), received.getName());
        assertEquals(expected.getDescription(), received.getDescription());
        assertEquals(expected.getCategory(), received.getCategory());
        assertFalse(received.isDone());
    }

    @Test
    @DisplayName("should be able to create a todo item")
    void testCreate(@Mock TodoRepository todoRepository) {
        final var todoService = createTodoService(todoRepository);
        final var todoItemInput = new TodoItem("Java", "Learn Java.");
        final var expectedTodoItem = new TodoItem(1L, "Java", "Learn Java.");

        when(todoRepository.save(todoItemInput)).thenReturn(expectedTodoItem);

        final var todoItemOutput = todoService.save(todoItemInput);

        verify(todoRepository, times(1)).save(todoItemInput);
        verifyNoMoreInteractions(todoRepository);
        assertTodoItem(expectedTodoItem, todoItemOutput);
    }

    @Test
    @DisplayName("should NOT be able to create a null todo item")
    void testCreateWithNullTodoItem(@Mock TodoRepository todoRepository) {
        final var todoService = createTodoService(todoRepository);
        assertThrows(IllegalArgumentException.class, () -> todoService.save(null));
    }

    @Test
    @DisplayName("should be able to find a todo item by id")
    void testFindById(@Mock TodoRepository todoRepository) {
        final var todoService = createTodoService(todoRepository);
        final var expectedTodoItem = new TodoItem(1L, "Java", "Learn Java.");

        when(todoRepository.findById(expectedTodoItem.getId())).thenReturn(expectedTodoItem);

        final var todoItemOutput = todoService.findById(expectedTodoItem.getId());

        verify(todoRepository, times(1)).findById(expectedTodoItem.getId());
        verifyNoMoreInteractions(todoRepository);
        assertTodoItem(expectedTodoItem, todoItemOutput);
    }
}
