package todo.proit.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * @author dipanjal
 * @since 2/18/2021
 */
@Entity
@Table(name = "task")
@Getter
@Setter
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "status")
    private int status;
    @Column(name = "created_date")
    private Date createdDate;
    @Column(name = "is_active")
    private char isActive;
}
