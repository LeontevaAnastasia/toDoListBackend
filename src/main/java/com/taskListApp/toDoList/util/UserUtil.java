package com.taskListApp.toDoList.util;

import com.taskListApp.toDoList.model.User;
import com.taskListApp.toDoList.to.UserTo;

public class UserUtil {

    public static User updateFromTo(User user, UserTo userTo) {
        user.setName(userTo.getName());
        user.setEmail(userTo.getEmail().toLowerCase());
        user.setPassword(userTo.getPassword());
        return user;
    }
}
