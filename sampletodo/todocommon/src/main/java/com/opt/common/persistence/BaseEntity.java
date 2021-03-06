/*****************************************************************************************************************
 *
 *	 File			 : BaseEntity.java
 *
  *****************************************************************************************************************/

package com.opt.common.persistence;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/*
 * This class is  base class   which is used for
 *  handling Common  data in db
 */
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = -4820393655770250900L;

	@Basic(optional = false)
    @Column(name = "CREATIONDATE")
    @Temporal(TemporalType.DATE)
    private Date creationDate;

    @Basic(optional = false)
    @Column(name = "CREATEDBY")
    private String createdBy;

    @Column(name = "MODIFICATIONDATE")
    @Temporal(TemporalType.DATE)
    private Date modificationDate;

    @Column(name = "MODIFIEDBY")
    private String modifiedBy;

    public BaseEntity() {
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

}
