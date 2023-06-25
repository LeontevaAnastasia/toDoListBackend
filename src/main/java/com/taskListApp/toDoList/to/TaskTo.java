package com.taskListApp.toDoList.to;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class TaskTo extends AbstractBaseTo implements Serializable {


    @Size(min=1, max = 30)
    private String header;

    private String description;


     private boolean isCompleted;

    public TaskTo(Integer id, String header, String description, boolean isCompleted) {
        super(id);
        this.header=header;
        this.description=description;
        this.isCompleted=isCompleted;
    }
}
