package com.opt.common.persistence;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

@Entity
@RevisionEntity
@Table(schema = "AUD", name = "AUDITINFO")
public class AuditEntity implements Serializable {

    private static final long serialVersionUID = 2869121312287390869L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "AUDITIDGEN")
    @TableGenerator(name = "AUDITIDGEN", schema = "DBO", table = "SEQUENCESTORE", pkColumnName = "TABLENAME", pkColumnValue = "AUDITINFO", valueColumnName = "INTEGERKEY", initialValue = 1)
    @RevisionNumber
    @Column(name = "AUDITID")
    private Integer auditId;

    @RevisionTimestamp
    @Column(name = "AUDITTIME")
    private Long auditTime;

    public AuditEntity() {
    }

    public Integer getAuditId() {
        return auditId;
    }

    public void setAuditId(Integer auditId) {
        this.auditId = auditId;
    }

    public Long getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Long auditTime) {
        this.auditTime = auditTime;
    }

}
