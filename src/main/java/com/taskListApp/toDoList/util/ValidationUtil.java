package com.taskListApp.toDoList.util;

import com.taskListApp.toDoList.util.exception.NotFoundException;

import java.util.Optional;

public class ValidationUtil {

    public static <T> T checkNotFoundWithId(T object, int id) {
        checkNotFoundWithId(object != null, id);
        return object;
    }

    public static void checkNotFoundWithId(boolean found, int id) {
        checkNotFound(found, "id=" + id);
    }

    public static <T> T checkNotFound(T object, String msg) {
        checkNotFound(object != null, msg);
        return object;
    }

    public static void checkNotFound(boolean found, String msg) {
        if (!found) {
            throw new NotFoundException("Not found entity with " + msg);
        }
    }

    public static <T> T checkNotFoundWithId(Optional<T> optional, int id) {
        return checkNotFoundWithId(optional, "Not found entity with id=" + id);
    }

    public static <T> T checkNotFoundWithId(Optional<T> optional, String msg) {
        return optional.orElseThrow(() -> new NotFoundException(msg));
    }
}
