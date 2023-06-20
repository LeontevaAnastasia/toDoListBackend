package com.taskListApp.toDoList.to;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class TaskTo extends AbstractBaseTo implements Serializable {


    @NotBlank
    @Size(min=1, max = 30)
    private String header;

    private String description;

    private boolean completed = false;
}
