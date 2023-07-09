package com.taskListApp.toDoList.service.emailService;

import com.taskListApp.toDoList.model.User;

public interface EmailCreator {
    String createEmailAddress(User user);

    String createEmailTitle(User user);

    String createEmailText(User user);
}
