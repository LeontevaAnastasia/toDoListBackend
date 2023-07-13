package com.taskListApp.toDoList.web.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import com.taskListApp.toDoList.AuthorizedUser;
import com.taskListApp.toDoList.model.User;
import com.taskListApp.toDoList.service.emailService.EmailSenderService;
import com.taskListApp.toDoList.to.UserTo;
import com.taskListApp.toDoList.util.UserUtil;
import com.taskListApp.toDoList.util.exception.NotFoundException;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import com.taskListApp.toDoList.service.UserService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

import static com.taskListApp.toDoList.util.UserUtil.*;
import static com.taskListApp.toDoList.util.ValidationUtil.checkNew;

@RestController
@AllArgsConstructor
@RequestMapping(value="/rest/profile", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileController {

    private final UserService userService;
    protected final Logger log = LoggerFactory.getLogger(getClass());

    private ObjectMapper objectMapper;
    private EmailSenderService emailSenderService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> register(@Valid @RequestBody UserTo userTo) {
        log.info("Create profile");
        checkNew(userTo);
        User created = userService.create(createNewFromTo(userTo));
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/rest/profile")
                .build()
                .toUri();
        emailSenderService.sendEmail(created);
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @GetMapping()
    public User get(@AuthenticationPrincipal AuthorizedUser authUser) {
        log.info("Get user by id {}.", authUser.getId());
        return userService.get(authUser.getId());
    }
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@AuthenticationPrincipal AuthorizedUser authUser) {
        log.info("Delete profile id {} by user.", authUser.getId());
        userService.delete(authUser.getId());
    }

    @PatchMapping(consumes = "application/json-patch+json")
    public ResponseEntity<UserTo> update(@RequestBody JsonMergePatch patch,
                                         @Parameter(hidden = true)@AuthenticationPrincipal AuthorizedUser authUser) {
        try {
            log.info("Update user with id {}.", authUser.getId());
            User user = userService.get(authUser.getId());//юзера находит
            UserTo userTo = UserUtil.asTo(user);
            UserTo userPatched = applyPatchToUser(patch, userTo);
            userService.update(UserUtil.updateFromTo(user, userPatched)); // ошибка при сохранении
            return ResponseEntity.ok(userTo);
        } catch (JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    private UserTo applyPatchToUser(
            JsonMergePatch patch, UserTo targetUserTo) throws JsonPatchException, JsonProcessingException {
        JsonNode patched = patch.apply(objectMapper.convertValue(targetUserTo, JsonNode.class));
        return objectMapper.treeToValue(patched, UserTo.class);
    }
}
