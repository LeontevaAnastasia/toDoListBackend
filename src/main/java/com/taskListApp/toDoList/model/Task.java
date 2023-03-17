package com.taskListApp.toDoList.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "tasks")
public class Task extends AbstractBaseEntity {

    @Column(name = "header")
    @NotBlank
    @Size(min=1, max = 30)
    private String header;

    @Column(name = "description")
    private String description;
    @Column(name = "completed")
    private boolean completed = false;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "date_time_complete")
    @NotNull
    LocalDateTime dateTimeComplete;

    public Task(Integer id, String header, String description, boolean completed, User user, LocalDateTime dateTimeComplete) {
        super(id);
        this.header = header;
        this.description = description;
        this.completed = completed;
        this.user = user;
        this.dateTimeComplete = dateTimeComplete;
    }


}
