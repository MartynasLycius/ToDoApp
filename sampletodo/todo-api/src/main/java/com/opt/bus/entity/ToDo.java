package com.opt.bus.entity;

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
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.opt.common.persistence.BaseEntity;
import com.opt.common.persistence.BaseEntityListener;

@Entity(name = "toDo")
@EntityListeners(BaseEntityListener.class)
@Table(schema = "crms", name = "ToDo")
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
	@Column(name = "NAME", length = 50, nullable = false)
	private String name;

	@NotNull
	@NotEmpty
	@NotBlank
	@Size(max = 100)
	@Basic(optional = false)
	@Column(name = "DESCRIPTION", length = 50, nullable = false)
	private String description;

	@NotNull
    @Basic(optional = false)
    @Column(name = "DATE", nullable = false) // unique = true
   	private LocalDate date;	

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

	@Transient
	private String edit;

	@Transient
	private String delete;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public String getEdit() {
		return edit;
	}

	public void setEdit(String edit) {
		this.edit = edit;
	}

	public String getDelete() {
		return delete;
	}

	public void setDelete(String delete) {
		this.delete = delete;
	}

}
