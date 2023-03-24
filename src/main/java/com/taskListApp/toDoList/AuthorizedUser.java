package com.taskListApp.toDoList;

import com.taskListApp.toDoList.model.User;

public class AuthorizedUser {

    private static final long serialVersionUID = 1L;

    private User user;


    public int getId() {
        return user.id();
    }

    public void set(User newUser) {
        newUser.setPassword(null);
        user = newUser;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return user.toString();
    }


}
