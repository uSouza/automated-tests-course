package org.example.todo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

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
    @DisplayName("should be able to save a todo item")
    void testSave(@Mock TodoRepository todoRepository) {
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
    @DisplayName("should NOT be able to find a null todo item")
    void testFindNullTodoItemId(@Mock TodoRepository todoRepository) {
        final var todoService = createTodoService(todoRepository);
        final var todoItem = new TodoItem(null, "Java", "Learn Java.");

        assertThrows(IllegalArgumentException.class, () -> todoService.findById(todoItem.getId()));
    }

    @Test
    @DisplayName("should NOT be able to find a non-existing todo item")
    void testFindByIdNonExistingTodoItem(@Mock TodoRepository todoRepository) {
        final var todoService = createTodoService(todoRepository);
        final var todoItem = new TodoItem(1000L, "Java", "Learn Java.");

        assertThrows(TodoService.TodoItemNotFoundException.class, () -> todoService.findById(todoItem.getId()));
    }

    @Test
    @DisplayName("should be able to find a todo item by id")
    void testFindById(@Mock TodoRepository todoRepository) {
        final var todoService = createTodoService(todoRepository);
        final var expectedTodoItem = new TodoItem(1L, "Java", "Learn Java.");

        when(todoRepository.findById(expectedTodoItem.getId())).thenReturn(Optional.of(expectedTodoItem));

        final var todoItemOutput = todoService.findById(expectedTodoItem.getId());

        verify(todoRepository, times(1)).findById(expectedTodoItem.getId());
        verifyNoMoreInteractions(todoRepository);
        assertTodoItem(expectedTodoItem, todoItemOutput);
    }

    @Test
    @DisplayName("should be able to find a todo item by id")
    void testFindAll(@Mock TodoRepository todoRepository) {
        final var todoService = createTodoService(todoRepository);
        final var todoItemsInput = List.of(
            new TodoItem(1L, "Java", "Learn Java."),
            new TodoItem(2L, "Kotlin", "Learn Kotlin.")
        );

        when(todoRepository.findAll()).thenReturn(todoItemsInput);

        final var todoItemsOutput = todoService.findAll();

        verify(todoRepository, times(1)).findAll();
        verifyNoMoreInteractions(todoRepository);
        assertIterableEquals(todoItemsInput, todoItemsOutput);
    }

    @Test
    @DisplayName("should be able to complete a todo item")
    void testComplete(@Mock TodoRepository todoRepository) {
        final var todoService = createTodoService(todoRepository);
        final var todoItem = new TodoItem(1L, "Java", "Learn Java.");

        when(todoRepository.findById(todoItem.getId())).thenReturn(Optional.of(todoItem));

        todoService.complete(todoItem.getId());

        verify(todoRepository, times(1)).save(todoItem);
        verifyNoMoreInteractions(todoRepository);
        assertTrue(todoItem.isDone());
    }

    @Test
    @DisplayName("should NOT be able to complete a null todo item")
    void testCompleteNullTodoItemId(@Mock TodoRepository todoRepository) {
        final var todoService = createTodoService(todoRepository);
        final var todoItem = new TodoItem(null, "Java", "Learn Java.");

        assertThrows(IllegalArgumentException.class, () -> todoService.complete(todoItem.getId()));
    }

    @Test
    @DisplayName("should NOT be able to complete a non-existing todo item")
    void testCompleteNonExistingTodoItem(@Mock TodoRepository todoRepository) {
        final var todoService = createTodoService(todoRepository);
        final var todoItem = new TodoItem(1000L, "Java", "Learn Java.");

        assertThrows(TodoService.TodoItemNotFoundException.class, () -> todoService.complete(todoItem.getId()));
    }

    @Test
    @DisplayName("should be able to delete a todo item")
    void testDelete(@Mock TodoRepository todoRepository) {
        final var todoService = createTodoService(todoRepository);
        final var todoItem = new TodoItem(1L, "Java", "Learn Java.");

        when(todoRepository.delete(todoItem.getId())).thenReturn(true);

        final var todoItemWasDeleted = todoService.delete(todoItem.getId());

        verify(todoRepository, times(1)).delete(todoItem.getId());
        verifyNoMoreInteractions(todoRepository);
        assertTrue(todoItemWasDeleted);
    }

    @Test
    @DisplayName("should NOT be able to delete a null todo item")
    void testDeleteNullTodoItemId(@Mock TodoRepository todoRepository) {
        final var todoService = createTodoService(todoRepository);
        final var todoItem = new TodoItem(null, "Java", "Learn Java.");

        assertThrows(IllegalArgumentException.class, () -> todoService.delete(todoItem.getId()));
    }
}
