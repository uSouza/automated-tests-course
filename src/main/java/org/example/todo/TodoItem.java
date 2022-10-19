package org.example.todo;

import java.util.Objects;

final class TodoItem {
    private Long id;
    private String name;
    private String description;
    private String category;
    private boolean done;

    TodoItem(String name, String description, String category, boolean done) {
        Objects.requireNonNull(name, "Name must not be null.");
        Objects.requireNonNull(description, "Description must not be null.");

        this.name = name;
        this.description = description;
        this.category = category;
        this.done = done;
    }

    TodoItem(String name, String description) {
        this(name, description, "Undefined", false);
    }

    TodoItem(Long id, String name, String description) {
        this(name, description, "Undefined", false);
        this.setId(id);
    }

    Long getId() {
        return id;
    }

    void setId(Long id) {
        this.id = id;
    }

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    String getDescription() {
        return description;
    }

    void setDescription(String description) {
        this.description = description;
    }

    String getCategory() {
        return category;
    }

    void setCategory(String category) {
        this.category = category;
    }

    boolean isDone() {
        return done;
    }

    public void complete() {
        this.done = true;
    }
}
