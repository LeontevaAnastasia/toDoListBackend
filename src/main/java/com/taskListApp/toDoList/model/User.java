package com.taskListApp.toDoList.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
public class User {

    private String name;
    private String email;
    private String password;
    private boolean enabled = true;
    private Date registered = new Date();
    private Set<Role> roles;
    private List<Task> tasks;
}
