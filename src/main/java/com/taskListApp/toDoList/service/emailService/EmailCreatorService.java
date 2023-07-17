package com.taskListApp.toDoList.service.emailService;

import com.taskListApp.toDoList.model.User;
import com.taskListApp.toDoList.to.EmailTo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailCreatorService {

    private final EmailCreator emailCreator;

    public EmailTo getEmailMessages(User user) {
            EmailTo emailTo = new EmailTo();
            emailTo.setRecipientAddress(emailCreator.createEmailAddress(user));
            emailTo.setTitle(emailCreator.createEmailTitle(user));
            emailTo.setText(emailCreator.createEmailText(user));

        return emailTo;
    }
}
