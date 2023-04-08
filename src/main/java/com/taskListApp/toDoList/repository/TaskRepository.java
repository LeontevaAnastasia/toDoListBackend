package com.taskListApp.toDoList.repository;

import com.taskListApp.toDoList.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface TaskRepository extends JpaRepository<Task, Integer> {

    @Transactional
    @Modifying
    @Query("delete from Task t where t.id=:id and t.user.id=:userId")
    int delete(@Param("id") int id, @Param("userId") int userId);

    @Query("select t from Task t where t.id=:id and t.user.id=:userId")
    Optional<Task> getTaskById(@Param("id") int id, @Param("userId") int userId);

    @Query("select t from Task t where t.user.id=:userId")
    Optional<List<Task>> getAll(@Param("userId") int userId);
}
