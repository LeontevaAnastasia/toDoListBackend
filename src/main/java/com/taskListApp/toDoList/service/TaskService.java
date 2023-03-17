package com.taskListApp.toDoList.service;

import com.taskListApp.toDoList.model.Task;
import com.taskListApp.toDoList.repository.TaskRepository;
import com.taskListApp.toDoList.repository.UserRepository;

import java.util.List;

import static com.taskListApp.toDoList.util.ValidationUtil.checkNotFoundWithId;

public class TaskService {

    TaskRepository taskRepository;
    UserRepository userRepository;

    public Task create(Task task, int userId) {
        return saveTask(task, userId);
    }

    public void delete(int id, int userId ) {
        checkNotFoundWithId(taskRepository.delete(id, userId), id);
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
        task.setUser(userRepository.getById(userId));
        return taskRepository.save(task);
    }

}
