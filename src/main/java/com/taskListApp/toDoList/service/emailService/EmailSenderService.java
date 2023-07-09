package com.taskListApp.toDoList.service.emailService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.taskListApp.toDoList.model.User;
import com.taskListApp.toDoList.service.emailService.rabbitmq.RabbitProducer;
import com.taskListApp.toDoList.to.EmailDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailSenderService {

    private final EmailCreatorService emailCreatorService;
    private final RabbitProducer rabbitProducer;

    public void sendEmail(User user) {
       // List<User> users = new ArrayList<>(List.of(user));
        List<EmailDTO> emailMessages = emailCreatorService.getEmailMessages(user);

        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(emailMessages);
            rabbitProducer.sendMessage(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
