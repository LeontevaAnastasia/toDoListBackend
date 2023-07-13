package com.taskListApp.toDoList.to;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmailTo {
    private String recipientAddress;
    private String title;
    private String text;

}