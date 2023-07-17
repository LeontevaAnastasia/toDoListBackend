package com.taskListApp.toDoList.util.exception;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.boot.web.error.ErrorAttributeOptions.Include.MESSAGE;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "DuplicateEmail.")
public class DuplicateEmailException extends AppException{
    public DuplicateEmailException(String message) {
        super(HttpStatus.BAD_REQUEST, message, ErrorAttributeOptions.of(MESSAGE));
    }
}
