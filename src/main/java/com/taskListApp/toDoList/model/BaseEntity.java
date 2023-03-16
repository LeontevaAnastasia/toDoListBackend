package com.taskListApp.toDoList.model;
import javax.persistence.*;
import lombok.*;
import org.hibernate.cfg.AccessType;
import org.springframework.data.annotation.Id;

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public abstract class BaseEntity {

    public static final int START_SEQ = 100000;
    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    protected Integer id;
}
