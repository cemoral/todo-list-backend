package com.ce4apps.repository;

import com.ce4apps.domain.TodoItem;

import com.ce4apps.domain.TodoList;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.time.Instant;

/**
 * Spring Data JPA repository for the User entity.
 */
@Repository
public interface TodoListRepository extends JpaRepository<TodoList, Long> {

    List<TodoList> findAll();

    TodoList findOneById(Long Id);

    List<TodoList> findAllByUserId(Long userId);
}
