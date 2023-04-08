package com.taskListApp.toDoList.web.user;

import com.taskListApp.toDoList.AuthorizedUser;
import com.taskListApp.toDoList.model.User;
import com.taskListApp.toDoList.to.UserTo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.taskListApp.toDoList.service.UserService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

import static com.taskListApp.toDoList.util.UserUtil.*;
import static com.taskListApp.toDoList.util.ValidationUtil.assureIdConsistent;
import static com.taskListApp.toDoList.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value="/rest/profile", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileController {

    private final UserService userService;
    protected final Logger log = LoggerFactory.getLogger(getClass());


    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> register(@Valid @RequestBody UserTo userTo) {
        log.info("Create profile");
        checkNew(userTo);
        User created = userService.create(prepareToSave(createNewFromTo(userTo)));
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/rest/profile")
                .build()
                .toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @GetMapping()
    public User get(AuthorizedUser authUser) {
        log.info("Get user by id {}.", authUser.getId());
        return userService.get(authUser.getId());
    }
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(AuthorizedUser authUser) {
        log.info("Delete profile id {} by user.", authUser.getId());
        userService.delete(authUser.getId());
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody UserTo userTo, AuthorizedUser authUser) {
        log.info("Update user to {} by user id {}.", userTo, authUser.getId());
        assureIdConsistent(userTo, authUser.getId());
        User user = authUser.getUser();
        userService.create(updateFromTo(user, userTo));
    }
}
