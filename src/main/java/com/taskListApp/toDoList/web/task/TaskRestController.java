package com.taskListApp.toDoList.web.task;


import com.taskListApp.toDoList.model.Task;
import com.taskListApp.toDoList.model.User;
import com.taskListApp.toDoList.service.TaskService;
import com.taskListApp.toDoList.service.UserService;
import com.taskListApp.toDoList.to.TaskTo;
import com.taskListApp.toDoList.util.SecurityUtil;
import com.taskListApp.toDoList.util.TaskUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static com.taskListApp.toDoList.util.ValidationUtil.assureIdConsistent;
import static com.taskListApp.toDoList.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = "/rest/profile/tasks", produces = MediaType.APPLICATION_JSON_VALUE)
public class TaskRestController {


    private final TaskService taskService;

    private final UserService userService;

    public TaskRestController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService=userService;
    }


    @GetMapping("/{id}")
    public Task get(@PathVariable int id) {
        int userId = SecurityUtil.authUserId();

        return taskService.get(id, userId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {

        int userId = SecurityUtil.authUserId();
        taskService.delete(id, userId);
    }

    @GetMapping
    public List<Task> getAll() {
        int userId = SecurityUtil.authUserId();
        return taskService.getAll(userId);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody TaskTo taskTo, @PathVariable int id) {
        int userId = SecurityUtil.authUserId();
        assureIdConsistent(taskTo, id);
        taskService.update(TaskUtil.updateFromTo((taskService.get(id, userId)) ,taskTo) , userId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Task> createWithLocation(@RequestBody TaskTo taskTo) {

        int userId = SecurityUtil.authUserId();
        User user= userService.get(userId);
        Task task = TaskUtil.createNewFromTo(taskTo,user);
        checkNew(task);
        Task created = taskService.create(task, userId);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/rest/profile/tasks/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void completed(@PathVariable int id, @RequestParam boolean completed) {
        int userId = SecurityUtil.authUserId();
        taskService.getDone(id, userId, completed);
    }



}
