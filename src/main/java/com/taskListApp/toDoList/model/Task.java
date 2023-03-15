package com.taskListApp.toDoList.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class Task {

    private String header;
    private String description;
    private boolean completed;
    private User user;
    LocalDateTime dateTimeComplete;




}
