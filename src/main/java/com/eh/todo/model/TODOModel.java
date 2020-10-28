package com.eh.todo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * @author   Md. Emran Hossain <emranhos1@gmail.com>
 * @version  1.0.00 EH
 * @since    1.0.00 EH
 */
@Entity
@Table(name = "todo_table")
public class TODOModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_table_id")
    private Long todoTableId;
    @Column(name = "assignment")
    private String assignment;
    @Column(name = "assignment_details")
    private String assignmentDetails;
    @Column(name = "create_date")
    private String createDate;
    @Column(name = "completed_date")
    private String completedDate;
    @Column(name = "has_done")
    private int hasDone;

    public Long getTodoTableId() {
        return todoTableId;
    }

    public void setTodoTableId(Long todoTableId) {
        this.todoTableId = todoTableId;
    }

    public String getAssignment() {
        return assignment;
    }

    public void setAssignment(String assignment) {
        this.assignment = assignment;
    }

    public String getAssignmentDetails() {
        return assignmentDetails;
    }

    public void setAssignmentDetails(String assignmentDetails) {
        this.assignmentDetails = assignmentDetails;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCompletedDate() {
        return completedDate;
    }

    public void setCompletedDate(String completedDate) {
        this.completedDate = completedDate;
    }

    public int getHasDone() {
        return hasDone;
    }

    public void setHasDone(int hasDone) {
        this.hasDone = hasDone;
    }
}
