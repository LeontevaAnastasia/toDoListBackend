package com.taskListApp.toDoList.util.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid edit")
public class SaveEmptyFieldException extends RuntimeException {

    public SaveEmptyFieldException(String message){
        super(message);

    }


}
