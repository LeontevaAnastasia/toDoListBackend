package com.taskListApp.toDoList.service;

import com.taskListApp.toDoList.model.User;
import com.taskListApp.toDoList.repository.UserRepository;
import com.taskListApp.toDoList.to.UserTo;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return userRepository.getById(id);
    }

   public User findByEmailIgnoringCase(String email){
        return userRepository.findByEmailIgnoreCase(email);
    }

    public void delete(int id) {
        userRepository.delete(id);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public void update(UserTo userTo) {

        User user = get(userTo.getId());
        updateFromTo(user, userTo);
    }

    public static User updateFromTo(User user, UserTo userTo) {
        user.setName(userTo.getName());
        user.setEmail(userTo.getEmail().toLowerCase());
        user.setPassword(userTo.getPassword());
        return user;
    }


}
