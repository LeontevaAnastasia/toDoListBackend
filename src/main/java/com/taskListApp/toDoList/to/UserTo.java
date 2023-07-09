package com.taskListApp.toDoList.to;

import com.taskListApp.toDoList.HasId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serial;
import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserTo extends AbstractBaseTo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Size(min =1, max = 128)
    @NotBlank
    private String name;


    @Size(min=1, max = 128)
    @NotBlank
    private String email;

    @Size(min = 4, max = 100)
    @NotBlank
    private String password;

    public UserTo(Integer id, String name, String email, String password) {
        super(id);
        this.name = name;
        this.email = email;
        this.password = password;
    }


}
