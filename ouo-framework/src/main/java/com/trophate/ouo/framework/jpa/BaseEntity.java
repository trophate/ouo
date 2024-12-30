package com.trophate.ouo.framework.jpa;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@EntityListeners({CustomEntityListener.class})
@MappedSuperclass
public class BaseEntity {

    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 创建人id
     */
    private Integer createdById;
    /**
     * 创建时间
     */
    private LocalDateTime createdAt;
    /**
     * 更新人id
     */
    private Integer updatedById;
    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;
    /**
     * 删除标识
     */
    private Boolean deleted;
    /**
     * 删除时间
     */
    private LocalDateTime deletedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCreatedById() {
        return createdById;
    }

    public void setCreatedById(Integer createdById) {
        this.createdById = createdById;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getUpdatedById() {
        return updatedById;
    }

    public void setUpdatedById(Integer updatedById) {
        this.updatedById = updatedById;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }
}
