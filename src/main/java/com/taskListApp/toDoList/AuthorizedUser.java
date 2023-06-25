package com.taskListApp.toDoList;

import com.taskListApp.toDoList.model.User;
import com.taskListApp.toDoList.to.UserTo;
import com.taskListApp.toDoList.util.UserUtil;

public class AuthorizedUser extends org.springframework.security.core.userdetails.User  {

    private static final long serialVersionUID = 1L;
    private UserTo userTo;

    public AuthorizedUser(User user) {
        super(user.getEmail(), user.getPassword(), user.isEnabled(), true, true, true, user.getRoles());
        setTo(UserUtil.asTo(user));
    }
    public int getId() {
        return userTo.id();
    }

    public void setTo(UserTo newUserTo) {
        newUserTo.setPassword(null);
        userTo = newUserTo;
    }

    public UserTo getUserTo() {
        return userTo;
    }

    @Override
    public String toString() {
        return userTo.toString();
    }


}
