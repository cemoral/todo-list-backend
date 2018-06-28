package com.ce4apps.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "todo_item")
public class TodoItem extends AbstractAuditingEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "deadline")
    private Instant deadline;

    @JsonIgnore
    @Column(name = "todo_list_id")
    private Long todoListId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getDeadline() {
        return deadline;
    }

    public void setDeadline(Instant deadline) {
        this.deadline = deadline;
    }

    public Long getTodoListId() {
        return todoListId;
    }

    public void setTodoListId(Long todoListId) {
        this.todoListId = todoListId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TodoItem todoItem = (TodoItem) o;
        return Objects.equals(id, todoItem.id) &&
            Objects.equals(name, todoItem.name) &&
            Objects.equals(description, todoItem.description) &&
            Objects.equals(status, todoItem.status) &&
            Objects.equals(deadline, todoItem.deadline) &&
            Objects.equals(todoListId, todoItem.todoListId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, description, status, deadline, todoListId);
    }

    @Override
    public String toString() {
        return "TodoItem{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", description='" + description + '\'' +
            ", status='" + status + '\'' +
            ", deadline=" + deadline +
            ", todoListId=" + todoListId +
            '}';
    }
}

