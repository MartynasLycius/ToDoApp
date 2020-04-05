package com.rali.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "tbl_todo", uniqueConstraints={
        @UniqueConstraint(columnNames = {"item_name", "start_date"})
})
@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Todo {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "id", length = 40)
    private String id;
    @Column(name = "start_date")
    private Date startDate;
    @Column(name = "item_name")
    private String itemName;
    @Column(name = "description")
    private String description;
    @Column(name = "created_at", columnDefinition="DATETIME DEFAULT CURRENT_TIMESTAMP")
    private Date createdAt;
    @Column(name = "modified_at")
    private Date modifiedAt;
    @Column(name = "is_done", columnDefinition="tinyint(1) default 0")
    private Boolean isDone;
}
