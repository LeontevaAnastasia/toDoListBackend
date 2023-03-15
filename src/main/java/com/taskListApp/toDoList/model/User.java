package com.taskListApp.toDoList.model;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class User {

    private String name;
    private String email;
    private String password;
    private boolean enabled = true;
    private Date registered = new Date();
    private Set<Role> roles;
    private List<task> tasks;
}
