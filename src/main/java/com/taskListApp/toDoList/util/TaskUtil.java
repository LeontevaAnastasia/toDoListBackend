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
            task.setCompleted(taskTo.isCompleted());
          if(taskTo.isCompleted()){
              LocalDateTime currentDate = LocalDateTime.now();
            task.setDateTimeComplete(currentDate);
        } else task.setDateTimeComplete(null);

        return task;
    }

    public static Task createNewFromTo(TaskTo taskTo, User user) {
        return new Task(null, taskTo.getHeader(), taskTo.getDescription(), false, user, null);
    }

    public static TaskTo asTo(Task task) {
        return new TaskTo(task.getId(),task.getHeader(), task.getDescription(), task.isCompleted());
    }
}

