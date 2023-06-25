package com.taskListApp.toDoList.web.task;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import com.taskListApp.toDoList.model.Task;
import com.taskListApp.toDoList.model.User;
import com.taskListApp.toDoList.service.TaskService;
import com.taskListApp.toDoList.service.UserService;
import com.taskListApp.toDoList.to.TaskTo;
import com.taskListApp.toDoList.util.SecurityUtil;
import com.taskListApp.toDoList.util.TaskUtil;
import com.taskListApp.toDoList.util.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static com.taskListApp.toDoList.util.ValidationUtil.checkNew;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/rest/profile/tasks", produces = MediaType.APPLICATION_JSON_VALUE)
public class TaskRestController {


    private final TaskService taskService;

    private final UserService userService;

    protected final Logger log = LoggerFactory.getLogger(getClass());
    private ObjectMapper objectMapper = new ObjectMapper();


    @GetMapping("/{id}")
    public Task get(@PathVariable int id) {
        log.info("Get task by id {}.", id);
        int userId = SecurityUtil.authUserId();

        return taskService.get(id, userId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("Delete task by id {}.", id);

        int userId = SecurityUtil.authUserId();
        taskService.delete(id, userId);
    }

    @GetMapping
    public List<Task> getAll() {
        log.info("Get all tasks");
        int userId = SecurityUtil.authUserId();
        return taskService.getAll(userId);
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Task> create(@ModelAttribute TaskTo taskTo) {
        log.info("Create task.");

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

    @PatchMapping(path = "/{id}", consumes = "application/json-patch+json")
    public ResponseEntity<TaskTo> update(@PathVariable int id, @RequestBody JsonMergePatch patch) {

        try {
            int userId = SecurityUtil.authUserId();
            log.info("Update task with id {} and userId {}.", id, userId);
            Task task = taskService.get(id,userId);
            TaskTo taskTo = TaskUtil.asTo(task);
            TaskTo taskPatched = applyPatchToTask(patch, taskTo);
            taskService.update(TaskUtil.updateFromTo(task,taskPatched), userId);
            return ResponseEntity.ok(taskTo);
        } catch (JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    private TaskTo applyPatchToTask(
            JsonMergePatch patch, TaskTo targetTaskTo) throws JsonPatchException, JsonProcessingException {
        JsonNode patched = patch.apply(objectMapper.convertValue(targetTaskTo, JsonNode.class));
        return objectMapper.treeToValue(patched, TaskTo.class);
    }

}
