package com.ce4apps.service;

import com.ce4apps.domain.TodoItem;
import com.ce4apps.repository.TodoItemRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class TodoItemService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    private final TodoItemRepository todoItemRepository;

    private final CacheManager cacheManager;

    public TodoItemService(CacheManager cacheManager, TodoItemRepository todoItemRepository) {
        this.todoItemRepository = todoItemRepository;
        this.cacheManager = cacheManager;
    }

    public List<TodoItem> findTodoItemByTodoListId(Long id) {
        //log.debug();
        return todoItemRepository.findAllByTodoListId(id);
    }
}


