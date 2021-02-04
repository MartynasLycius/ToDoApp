package com.mir00r.todoapp.domains.common.models.entities.base;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "uuid", nullable = false, unique = true)
    private UUID uuid;

    @Column(nullable = false)
    private boolean deleted;

    @PrePersist
    private void onBasePersist() {
        this.createdAt = new Date();
        this.updatedAt = createdAt;
        this.createdBy = "admin"; // TODO: username will come from security context
        this.uuid = UUID.randomUUID();
    }

    @PreUpdate
    private void onBaseUpdate() {
        this.updatedAt = new Date();
        this.updatedBy = "admin"; // TODO: username will come from security context
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Date getCreatedAt() {
        return createdAt;
    }


    public Date getUpdatedAt() {
        return updatedAt;
    }


    public String getCreatedBy() {
        return createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
