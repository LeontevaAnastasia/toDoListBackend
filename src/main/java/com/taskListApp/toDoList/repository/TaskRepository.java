package com.taskListApp.toDoList.repository;

import com.taskListApp.toDoList.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    @Query("delete from Task t where t.id=:id")
    int delete(@Param("id") int id);
}
