package com.diegoborbadev.expensemanager.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Date;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseModel<ID> implements Serializable {

    public abstract ID getId();

    public abstract void setId(ID id);

    @Version
    @Column(nullable = false)
    protected Integer version;

    @Column
    @CreatedBy
    protected String createdBy;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
//    @CreatedDate
    protected Date createdAt;

    @Column
    @LastModifiedBy
    protected String updatedBy;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    protected Date updatedAt;

    @PrePersist
    @PreUpdate
    void onCreate() {
        if (createdAt == null) {
            setCreatedAt(new Date());
        }
    }
}