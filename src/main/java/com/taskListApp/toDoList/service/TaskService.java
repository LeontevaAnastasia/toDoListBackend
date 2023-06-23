package com.taskListApp.toDoList.service;

import com.taskListApp.toDoList.model.Task;
import com.taskListApp.toDoList.repository.TaskRepository;
import com.taskListApp.toDoList.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.taskListApp.toDoList.util.ValidationUtil.checkNotFoundWithId;

@AllArgsConstructor
@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public Task create(Task task, int userId) {
        return saveTask(task, userId);
    }

    public void delete(int id, int userId ) {
        checkNotFoundWithId((taskRepository.delete(id, userId)!= 0), id);
    }

    public Task get(int id, int userId) {
        return checkNotFoundWithId(taskRepository.getTaskById(id, userId), id);
    }

    public List<Task> getAll(int userId) {
        return checkNotFoundWithId(taskRepository.getAll(userId), userId);
    }

    public void update(Task task, int userId) {
        checkNotFoundWithId(saveTask(task, userId), task.getId());
    }

    public Task saveTask(Task task, int userId){
        if (!task.isNew() && get(task.getId(), userId) == null) {
            return null;
        }
        task.setUser(checkNotFoundWithId(userRepository.getUserById(userId),userId));
        return taskRepository.save(task);
    }

    public void getDone(int taskId, int userId, boolean completed){
        Task task = get(taskId,userId);
        task.setCompleted(completed);
        task.setDateTimeComplete(LocalDateTime.now());
        update(task, userId);
    }

}
