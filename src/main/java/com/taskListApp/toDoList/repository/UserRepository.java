package com.taskListApp.toDoList.repository;

import com.taskListApp.toDoList.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

      @Query("SELECT u FROM User u WHERE u.email = LOWER(:email)")
    Optional<User> findByEmailIgnoreCase(String email);

    @Query("delete from User u where u.id=:id")
    int delete(@Param("id") int id);

    @Query("select u from User u where u.id=:id")
    Optional<User> getUserById(int id);


}