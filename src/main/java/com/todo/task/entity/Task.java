package com.todo.task.entity;

import com.todo.user.entity.User;
import com.todo.utils.AbstractEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

@Entity
@Getter @Setter @ToString
public class Task extends AbstractEntity{

    @Id
    @GeneratedValue(generator = "task_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "task_id_seq", sequenceName = "task_id_seq")
    private Long id;

    @NotNull(message = "Please input time")
    @Column(name = "time")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "hh:mm a")
    private LocalTime time;

    @NotNull(message = "Please input date")
    @Column(name = "date")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;

    @NotNull
    @NotBlank(message = "Please enter username")
    @Column(name = "task_name")
    private String taskName;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User taskOwner;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "task", fetch = FetchType.LAZY)
    private List<Comment> comments = new LinkedList<>();

    @Override
    public boolean isPersisted() {
        return id != null;
    }
}
