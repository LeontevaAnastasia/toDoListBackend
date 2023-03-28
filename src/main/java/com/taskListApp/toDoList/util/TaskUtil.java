package com.taskListApp.toDoList.util;
import com.taskListApp.toDoList.model.Task;
import com.taskListApp.toDoList.model.User;
import com.taskListApp.toDoList.to.TaskTo;
import lombok.experimental.UtilityClass;
import java.time.LocalDateTime;

@UtilityClass
public class TaskUtil {
    public static Task updateFromTo(Task task, TaskTo taskTo) {
        task.setHeader(taskTo.getHeader());
        task.setDescription(taskTo.getDescription());
        return task;
    }

    public static Task createNewFromTo(TaskTo taskTo, User user) {
        return new Task(null, taskTo.getHeader(), taskTo.getDescription(), false, user, LocalDateTime.now());
    }
}

