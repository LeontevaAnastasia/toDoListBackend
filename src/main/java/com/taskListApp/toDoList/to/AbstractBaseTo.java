package com.taskListApp.toDoList.to;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.taskListApp.toDoList.HasId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractBaseTo implements HasId {

    @JsonIgnore
    protected Integer id;
}
