package com.taskListApp.toDoList.service;

import com.taskListApp.toDoList.model.User;
import com.taskListApp.toDoList.repository.UserRepository;
import com.taskListApp.toDoList.to.UserTo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.taskListApp.toDoList.util.UserUtil.updateFromTo;
import static com.taskListApp.toDoList.util.ValidationUtil.checkNotFound;
import static com.taskListApp.toDoList.util.ValidationUtil.checkNotFoundWithId;

@Service
public class UserService {

   UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(User user) {
        return userRepository.save(user);
    }

    public User get(int id) {
        return checkNotFoundWithId(userRepository.getUserById(id), id);
    }

   public Optional<User> findByEmailIgnoringCase(String email){
        return checkNotFound(userRepository.findByEmailIgnoreCase(email),"email=" + email);
    }

    public void delete(int id) {
        checkNotFoundWithId(userRepository.delete(id), id);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public void update(UserTo userTo) {

        User user = get(userTo.getId());
        updateFromTo(user, userTo);
    }



}
