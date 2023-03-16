package com.taskListApp.toDoList.to;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserTo extends AbstractBaseTo {

    @Serial
    private static final long serialVersionUID = 1L;

    private String name;


    private String email;


    private String password;

    public UserTo(Integer id, String name, String email, String password) {
        super(id);
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
