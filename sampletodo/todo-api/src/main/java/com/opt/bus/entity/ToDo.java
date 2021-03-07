package com.opt.bus.entity;


import com.opt.common.persistence.BaseEntity;
import com.opt.common.persistence.BaseEntityListener;

import java.time.LocalDate;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;



@Entity(name = "ToDo")
@EntityListeners(BaseEntityListener.class)
@Table(schema = "crms", name = "TODO")
@Audited
@AuditOverride(forClass = BaseEntity.class)
@AuditTable(schema = "crms", value = "ToDo_AUD")
public class ToDo extends BaseEntity {
	private static final long serialVersionUID = 8121254796806684289L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ToDoPKGEN")
    @TableGenerator(name = "ToDoPKGEN", schema = "crms", table = "SEQUENCESTORE", 
    pkColumnName = "TABLENAME", pkColumnValue = "ToDo",
    valueColumnName = "INTEGERKEY", initialValue = 1)
	@Basic(optional = false)
	@Column(name = "ID" , nullable = false)
	private Long id;

	@NotNull
	@NotEmpty
	@NotBlank
	@Size(max = 50)
	@Basic(optional = false)
	@Column(name = "ITEMNAME", length = 50, nullable = false) // unique = true
	private String itemName;

	@NotNull
	@NotEmpty
	@NotBlank
	@Size(max = 100)
	@Basic(optional = false)
	@Column(name = "ITEMDESCRIPTION", length = 50, nullable = false)
	private String itemDescription;

	@NotNull
    @Basic(optional = false)
    @Column(name = "PRODUCTIONDATE", nullable = false) 
   	private LocalDate productionDate;	

	@NotNull
	@NotEmpty
	@NotBlank
	@Size(min = 1, max = 1)
	@Basic(optional = false)
	@Column(name = "STATUS", length = 1, nullable = false)
	private String statusId;

	@Version
	@NotAudited
	@Basic(optional = false)
	@Column(name = "VERSION")
	private Long version;

	
	public ToDo() {

	}
	
	public String getStatusId() {
		return statusId;
	}

	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public LocalDate getProductionDate() {
		return productionDate;
	}

	public void setProductionDate(LocalDate productionDate) {
		this.productionDate = productionDate;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}



}
