/*****************************************************************************************************************
 *
 *	 File			 : BaseEntityListener.java
 *
  *****************************************************************************************************************/

package com.opt.common.persistence;

import java.util.Date;

import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;


/*
 * This class is  BaseEntityListener class   which is used for
 *  handling Base entity  data in db
 */
public class BaseEntityListener {

    @PrePersist
    protected void persist(BaseEntity baseEntity) {
        baseEntity.setCreationDate(new Date());
        baseEntity.setCreatedBy("ToDo-CreateUser");

    }

    @PreUpdate
    @PreRemove
    protected void update(BaseEntity baseEntity) {
        baseEntity.setModificationDate(new Date());
        baseEntity.setModifiedBy("TODO-editUser");
        
    }

   

}
