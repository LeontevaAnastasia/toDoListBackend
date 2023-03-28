package com.taskListApp.toDoList.util;

public class SecurityUtil {
    private static int id = 100000;

    public static int authUserId() {
        return id;
    }

    public static void setAuthUserId(int id) {
        SecurityUtil.id = id;
    }
}
