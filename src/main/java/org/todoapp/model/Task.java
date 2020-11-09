package org.todoapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    protected int id;

    @Column(name="task_name")
    protected String taskName;

    @Column(name="description")
    protected String description;

    @Column(name = "task_date", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date taskDate;

    public Task(String taskName, String description, Date taskDate) {
        this.taskName = taskName;
        this.description = description;
        this.taskDate = taskDate;
    }
}
