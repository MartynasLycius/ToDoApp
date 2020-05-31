package com.todo.task.entity;

import com.todo.utils.AbstractEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Getter @Setter @ToString
public class Comment extends AbstractEntity {

    @Id
    @GeneratedValue(generator = "comment_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "comment_id_seq", sequenceName = "comment_id_seq")
    private Long id;

    @NotNull
    @NotEmpty
    @Column(name = "comment")
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    private Task task;

    @Override
    public boolean isPersisted() {
        return id != null;
    }
}
