package com.ce4apps.service;

import com.ce4apps.domain.TodoList;
import com.ce4apps.repository.TodoListRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class TodoListService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    private final TodoListRepository todoListRepository;

    private final CacheManager cacheManager;

    public TodoListService(CacheManager cacheManager, TodoListRepository todoListRepository) {
        this.todoListRepository = todoListRepository;
        this.cacheManager = cacheManager;
    }

    public List<TodoList> findTodoListByUserId(Long id) {
        //log.debug();
        return todoListRepository.findAllByUserId(id);
    }
}

