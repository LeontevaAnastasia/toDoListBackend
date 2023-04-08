package com.taskListApp.toDoList.web.user;


import com.taskListApp.toDoList.model.User;
import com.taskListApp.toDoList.service.UserService;
import com.taskListApp.toDoList.to.UserTo;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.taskListApp.toDoList.util.ValidationUtil.assureIdConsistent;

@RestController
@RequestMapping(value = "/rest/admin/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminRestController {

    private final UserService userService;

    public AdminRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping(value = "/{id}")
    public User get(@PathVariable int id) {
        return userService.get(id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody UserTo userTo, @PathVariable int id) {
        assureIdConsistent(userTo, id);
        userService.update(userTo);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id){
        userService.delete(id);
    }

    @GetMapping("/by-email")
    public User findByEmailIgnoringCase(@RequestParam String email){
        return userService.findByEmailIgnoringCase(email).orElse(null);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void isEnable(@PathVariable int id, @RequestParam boolean enabled) {
        userService.isEnable(id, enabled);
    }


    @PatchMapping("/{id}/set-role")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void setRole(@PathVariable int id) {
        userService.setAdminRole(id);
    }

    @PatchMapping("/{id}/remove-role")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeRole(@PathVariable int id) {
        userService.removeAdminRole(id);
    }
}
