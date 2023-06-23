package com.taskListApp.toDoList;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

public interface HasId extends Serializable {

    Integer getId();

    void setId(Integer id);

    @JsonIgnore
    default boolean isNew() {
        return getId() == null;
    }

    // doesn't work for hibernate lazy proxy
    default int id() {
        return getId();
    }


}
