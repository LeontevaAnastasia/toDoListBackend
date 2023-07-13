package com.taskListApp.toDoList.util;

import com.taskListApp.toDoList.model.Role;
import com.taskListApp.toDoList.model.User;
import com.taskListApp.toDoList.to.UserTo;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.util.Set;


@UtilityClass
public class UserUtil {



    public static User updateFromTo(User user, UserTo userTo) {
      user.setName(userTo.getName());
      user.setEmail(userTo.getEmail());
      user.setPassword(userTo.getPassword());
        return user;
    }

    public static User createNewFromTo(UserTo userTo) {
        Set<Role> roleSet = Set.of(Role.USER);
        return new User(null, userTo.getName(), userTo.getEmail(), userTo.getPassword(), LocalDate.now(), true, roleSet);
    }

    public static UserTo asTo(User user) {
        return new UserTo(user.getId(), user.getName(), user.getEmail(), user.getPassword());
    }
}
