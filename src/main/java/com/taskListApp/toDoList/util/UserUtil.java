package com.taskListApp.toDoList.util;

import com.taskListApp.toDoList.model.Role;
import com.taskListApp.toDoList.model.User;
import com.taskListApp.toDoList.to.UserTo;

import java.time.LocalDate;
import java.util.Set;

public class UserUtil {

    public static User updateFromTo(User user, UserTo userTo) {
        user.setName(userTo.getName());
        user.setEmail(userTo.getEmail().toLowerCase());
        user.setPassword(userTo.getPassword());
        return user;
    }

    public static User createNewFromTo(UserTo userTo) {
        Set<Role> roleSet = Set.of(Role.USER);
        return new User(null, userTo.getName(), userTo.getEmail().toLowerCase(), userTo.getPassword(), LocalDate.now(), true, roleSet);
    }

    public static User prepareToSave(User user) {
        user.setEmail(user.getEmail().toLowerCase());
        return user;
    }
}
