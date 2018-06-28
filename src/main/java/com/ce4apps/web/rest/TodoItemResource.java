package com.ce4apps.web.rest;

import com.ce4apps.config.Constants;
import com.ce4apps.domain.TodoItem;
import com.ce4apps.domain.TodoList;
import com.ce4apps.repository.TodoItemRepository;
import com.ce4apps.repository.TodoListRepository;
import com.ce4apps.service.TodoItemService;
import com.ce4apps.service.TodoListService;
import com.codahale.metrics.annotation.Timed;
import com.ce4apps.domain.User;
import com.ce4apps.repository.UserRepository;
import com.ce4apps.security.AuthoritiesConstants;
import com.ce4apps.service.MailService;
import com.ce4apps.service.UserService;
import com.ce4apps.service.dto.UserDTO;
import com.ce4apps.web.rest.errors.BadRequestAlertException;
import com.ce4apps.web.rest.errors.EmailAlreadyUsedException;
import com.ce4apps.web.rest.errors.LoginAlreadyUsedException;
import com.ce4apps.web.rest.util.HeaderUtil;
import com.ce4apps.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;

import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

@RestController
@RequestMapping("/api")
public class TodoItemResource {

    private final Logger log = LoggerFactory.getLogger(UserResource.class);

    private final TodoItemService todoItemService;

    private final TodoItemRepository todoItemRepository;

    public TodoItemResource(TodoItemService todoItemService, TodoItemRepository todoItemRepository) {

        this.todoItemService = todoItemService;
        this.todoItemRepository = todoItemRepository;
    }

    @GetMapping("/todoItems/{id}")
    @Timed
    public ResponseEntity<List<TodoItem>> getAllTodoItemsByTodoListId(@PathVariable Long id) {
        return ResponseEntity.ok(todoItemService.findTodoItemByTodoListId(id));
    }

    @PostMapping("/todoItems/add/{id}")
    @Timed
    public ResponseEntity addTodoItem(@PathVariable Long id, @RequestBody TodoItem item) {
        TodoItem newItem = item;
        newItem.setTodoListId(id);
        todoItemRepository.save(newItem);
        return ResponseEntity.ok(todoItemService.findTodoItemByTodoListId(id));
    }

    @GetMapping("/todoItems/setCompleted/{id}")
    @Timed
    public ResponseEntity setCompleted(@PathVariable Long id) {
        TodoItem item = todoItemRepository.findOneById(id);
        item.setStatus(true);
        todoItemRepository.save(item);
        return ResponseEntity.ok("Completed");
    }

    @GetMapping("/todoItems/delete/{id}")
    @Timed
    public ResponseEntity removeItem(@PathVariable Long id) {
        TodoItem item = todoItemRepository.findOneById(id);
        todoItemRepository.delete(item);
        return ResponseEntity.ok("Deleted");
    }
}
