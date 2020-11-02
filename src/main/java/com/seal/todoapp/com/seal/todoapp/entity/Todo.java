package com.seal.todoapp.com.seal.todoapp.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "TODO")
@Data
@NoArgsConstructor
public class Todo {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TODO_SEQ")
    @SequenceGenerator(name = "TODO_SEQ", sequenceName = "TODO_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "complete")
    private Boolean complete;

    @Column(name = "DATE")
    private LocalDate date;

    @CreationTimestamp
    @Column(name = "CREATE_TIME")
    private LocalDateTime createTime;

    @UpdateTimestamp
    @Column(name = "EDIT_TIME")
    private LocalDateTime editTime;

    @Version
    @Column(name = "VERSION")
    private Long version;
}
