package com.taskListApp.toDoList.service.emailService;

import com.taskListApp.toDoList.model.User;
import org.springframework.stereotype.Service;

@Service
public class GreetingEmail implements EmailCreator{
    @Override
    public String createEmailAddress(User user) {
        return user.getEmail();
    }

    @Override
    public String createEmailTitle(User user) {
        return "Благодарим Вас за регистрацию в сервисе \"ToDoList!\"";
    }

    @Override
    public String createEmailText(User user) {
        return "Добро пожаловать в наш сервис по отслеживанию выполнения задач! Желаем приятного использования и продуктивности!\n" +
                "Ваш логин для авторизации: " + user.getEmail();
    }
}
