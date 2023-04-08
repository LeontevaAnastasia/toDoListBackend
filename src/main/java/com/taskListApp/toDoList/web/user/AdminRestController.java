package com.taskListApp.toDoList.web.user;


import com.taskListApp.toDoList.model.User;
import com.taskListApp.toDoList.service.UserService;
import com.taskListApp.toDoList.to.UserTo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.taskListApp.toDoList.util.ValidationUtil.assureIdConsistent;

@RestController
@RequestMapping(value = "/rest/admin/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminRestController {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    private final UserService userService;

    public AdminRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAll() {
        log.info("Get all users.");
        return userService.getAll();
    }

    @GetMapping(value = "/{id}")
    public User get(@PathVariable int id) {
        log.info("Get user by id {}.", id);
        return userService.get(id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody UserTo userTo, @PathVariable int id) {
        log.info("Update user by id {}.", id);
        assureIdConsistent(userTo, id);
        userService.update(userTo);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id){
        log.info("Delete user by id {}.", id);
        userService.delete(id);
    }

    @GetMapping("/by-email")
    public User findByEmailIgnoringCase(@RequestParam String email){
        log.info("Find user by email {}.", email);
        return userService.findByEmailIgnoringCase(email).orElse(null);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void isEnable(@PathVariable int id, @RequestParam boolean enabled) {
        log.info("Get user enable/disable.");
        userService.isEnable(id, enabled);
    }


    @PatchMapping("/{id}/set-role")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void setRole(@PathVariable int id) {
        log.info("Set admin role for user with id {}.", id);
        userService.setAdminRole(id);
    }

    @PatchMapping("/{id}/remove-role")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeRole(@PathVariable int id) {
        log.info("Remove admin role for user with id {}.", id);
        userService.removeAdminRole(id);
    }
}
