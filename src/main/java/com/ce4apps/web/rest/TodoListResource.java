package com.ce4apps.web.rest;

import com.ce4apps.domain.TodoItem;
import com.ce4apps.domain.TodoList;
import com.ce4apps.repository.TodoItemRepository;
import com.ce4apps.repository.TodoListRepository;
import com.ce4apps.service.TodoItemService;
import com.ce4apps.service.TodoListService;
import com.ce4apps.service.UserService;
import com.codahale.metrics.annotation.Timed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class TodoListResource {

    private final Logger log = LoggerFactory.getLogger(UserResource.class);

    private final TodoListService todoListService;

    private final TodoListRepository todoListRepository;

    private final TodoItemService todoItemService;

    private final UserService userService;

    private final TodoItemRepository todoItemRepository;

    public TodoListResource(TodoListService todoListService, TodoListRepository todoListRepository,UserService userService,TodoItemService todoItemService,TodoItemRepository todoItemRepository) {

        this.todoListRepository = todoListRepository;
        this.todoListService = todoListService;
        this.userService = userService;
        this.todoItemService = todoItemService;
        this.todoItemRepository = todoItemRepository;
    }

    /*
     @PostMapping("/users")
     @Timed
     @Secured(AuthoritiesConstants.ADMIN)
     public ResponseEntity<User> createUser(@Valid @RequestBody UserDTO userDTO) throws URISyntaxException {
         log.debug("REST request to save User : {}", userDTO);

         if (userDTO.getId() != null) {
             throw new BadRequestAlertException("A new user cannot already have an ID", "userManagement", "idexists");
             // Lowercase the user login before comparing with database
         } else if (userRepository.findOneByLogin(userDTO.getLogin().toLowerCase()).isPresent()) {
             throw new LoginAlreadyUsedException();
         } else if (userRepository.findOneByEmailIgnoreCase(userDTO.getEmail()).isPresent()) {
             throw new EmailAlreadyUsedException();
         } else {
             User newUser = userService.createUser(userDTO);
             mailService.sendCreationEmail(newUser);
             return ResponseEntity.created(new URI("/api/users/" + newUser.getLogin()))
                 .headers(HeaderUtil.createAlert( "A user is created with identifier " + newUser.getLogin(), newUser.getLogin()))
                 .body(newUser);
         }
     }*/
    @PostMapping("/todoList/create")
    @Timed
    public ResponseEntity getAllTodoListByUser(@RequestBody TodoList list) {
        TodoList newList = list;
        Long userId = userService.getUserWithAuthorities().get().getId();
        newList.setUserId(userId);
        todoListRepository.save(newList);
        return ResponseEntity.ok("List created");
    }

    @GetMapping("/todoList")
    @Timed
    public ResponseEntity getAllTodoListByUser() {
        Long userId = userService.getUserWithAuthorities().get().getId();
        return ResponseEntity.ok(todoListService.findTodoListByUserId(userId));
    }

    @GetMapping("/todoList/delete/{id}")
    @Timed
    public ResponseEntity deleteList(@PathVariable Long id) {
        TodoList list = todoListRepository.findOneById(id);
        todoListRepository.delete(list);
        List<TodoItem> items = todoItemService.findTodoItemByTodoListId(id);
        todoItemRepository.deleteAll(items);

        return ResponseEntity.ok("List and items deleted");
    }
}
