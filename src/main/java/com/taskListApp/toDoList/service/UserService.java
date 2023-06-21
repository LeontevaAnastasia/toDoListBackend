package com.taskListApp.toDoList.service;

import com.taskListApp.toDoList.model.Role;
import com.taskListApp.toDoList.model.User;
import com.taskListApp.toDoList.repository.UserRepository;
import com.taskListApp.toDoList.to.UserTo;
import com.taskListApp.toDoList.util.exception.IncorrectUpdateException;
import com.taskListApp.toDoList.util.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.taskListApp.toDoList.util.UserUtil.prepareToSave;
import static com.taskListApp.toDoList.util.UserUtil.updateFromTo;
import static com.taskListApp.toDoList.util.ValidationUtil.checkNotFound;
import static com.taskListApp.toDoList.util.ValidationUtil.checkNotFoundWithId;

@AllArgsConstructor
@Service
public class UserService {

   private final UserRepository userRepository;

    public User create(User user) {
        return userRepository.save(prepareToSave(user));
    }

    public User get(int id) {
        return checkNotFoundWithId(userRepository.getUserById(id), id);
    }

   public Optional<User> findByEmail(String email){
        return checkNotFound(userRepository.findByEmail(email),"email=" + email);
    }

    public void delete(int id) {
        checkNotFoundWithId(userRepository.delete(id), id);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public void update(UserTo userTo) {
        User user = get(userTo.getId());
        userRepository.save(updateFromTo(user, userTo));

    }

    public void isEnable(int id, boolean enabled) {
        User user = userRepository.getUserById(id).orElse(null);
        if (user == null) {
            throw new NotFoundException("User with id " + id + " doesn't exists.");
        }
        user.setEnabled(enabled);
    }

    @Transactional
    public void setAdminRole(int id) {
        User user = userRepository.getUserById(id).orElse(null);
        if (user == null) {
            throw new org.webjars.NotFoundException("User with id " + id + " doesn't exists.");
        } else user.setRoles(Set.of(Role.ADMIN));
    }

    @Transactional
    public void removeAdminRole(int id) {
        User user = userRepository.getUserById(id).orElse(null);
        if (user == null) {
            throw new NotFoundException("User with id " + id + " doesn't exists.");
        }
        if (!(user.getRoles().toString().contains("ADMIN"))) {
            throw new IncorrectUpdateException();
        }

        Set<Role> roles = user.getRoles();
        roles.remove(Role.ADMIN);
        roles.add(Role.USER);
        user.setRoles(roles);
    }



}
