package com.taskListApp.toDoList.service;

import com.taskListApp.toDoList.model.Task;
import com.taskListApp.toDoList.repository.TaskRepository;

import java.util.List;

public class TaskService {

    TaskRepository taskRepository;

    public Task create(Task task) {
        return taskRepository.save(task);
    }

    public void delete(int id) {
        taskRepository.delete(id);
    }

    public Task get(int id) {
        return taskRepository.getById(id);
    }

    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    public void update(Task task) {
       taskRepository.save(task);
    }

}
