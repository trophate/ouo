package com.trophate.ouo.auth.entity;

import com.trophate.ouo.framework.jpa.BaseEntity;
import jakarta.persistence.Entity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLRestriction;

import java.io.Serializable;

@Entity(name = "role")
@DynamicInsert
@DynamicUpdate
@SQLRestriction("deleted = 0")
public class Role extends BaseEntity implements Serializable {

    /**
     * 名称
     */
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
