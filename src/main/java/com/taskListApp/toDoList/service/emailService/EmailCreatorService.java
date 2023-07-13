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

    public List<EmailTo> getEmailMessages(User user) {
        List<EmailTo> emailMessages = new ArrayList<>();

            EmailTo emailDTO = new EmailTo();
            emailDTO.setRecipientAddress(emailCreator.createEmailAddress(user));
            emailDTO.setTitle(emailCreator.createEmailTitle(user));
            emailDTO.setText(emailCreator.createEmailText(user));
            emailMessages.add(emailDTO);

        return emailMessages;
    }
}
