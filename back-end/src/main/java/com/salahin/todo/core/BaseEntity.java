/**
 * Created By: Md. Nazmus Salahin
 * Created Date: 23-Jan-2021
 * Time: 1:00 AM
 * Modified By:
 * Modified date:
 * (C) CopyRight Salahin ltd.
 */

package com.salahin.todo.core;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Data
@MappedSuperclass
@DynamicInsert
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {
	
    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @CreatedDate
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @LastModifiedDate
    @Column(name = "updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;
}
