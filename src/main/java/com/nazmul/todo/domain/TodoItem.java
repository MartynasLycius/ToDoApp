/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nazmul.todo.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.eclipse.persistence.annotations.BatchFetch;
import org.eclipse.persistence.annotations.BatchFetchType;

/**
 *
 * @author nazmul
 */
@Entity
@Table(name = "TODO_ITEM")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TodoItem.findAll", query = "SELECT t FROM TodoItem t")
    , @NamedQuery(name = "TodoItem.findById", query = "SELECT t FROM TodoItem t WHERE t.id = :id")
    , @NamedQuery(name = "TodoItem.findByDone", query = "SELECT t FROM TodoItem t WHERE t.done = :done")
    , @NamedQuery(name = "TodoItem.findByName", query = "SELECT t FROM TodoItem t WHERE t.name = :name")
    , @NamedQuery(name = "TodoItem.findByTimecreated", query = "SELECT t FROM TodoItem t WHERE t.timecreated = :timecreated")})
public class TodoItem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    
    @Lob
    @Size(max = 2147483647)
    @Column(name = "DESCRIPTION")
    private String description;
    
    @Column(name = "DONE", columnDefinition = "tinyint(1) default 0")
    private Boolean done;
    
    @Size(max = 255)
    @Column(name = "NAME")
    private String name;
    
    @Column(name = "TIMECREATED")
//    @Temporal(TemporalType.DATE)
    @NotNull
    private LocalDate timecreated;
    
    @JoinColumn(name = "CATEGORY_ID", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.LAZY)
    @BatchFetch(BatchFetchType.JOIN)
    @NotNull
    private Category category;

    public TodoItem() {
        name = "";
        description = "";
        timecreated = LocalDate.now();
        done = false;
    }

    public TodoItem(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getDone() {
        if(done == null) done = false;
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getTimecreated() {
        return timecreated;
    }

    public void setTimecreated(LocalDate timecreated) {
        this.timecreated = timecreated;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TodoItem)) {
            return false;
        }
        TodoItem other = (TodoItem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nazmul.todo.domain.TodoItem[ id=" + id + " ]";
    }
    
}
