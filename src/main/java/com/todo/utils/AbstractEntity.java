package com.todo.utils;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@Getter @Setter
public abstract class AbstractEntity implements Serializable, Cloneable {

    @CreationTimestamp
    private Date created;

    @UpdateTimestamp
    private Date updated;

    public abstract boolean isPersisted();
}
