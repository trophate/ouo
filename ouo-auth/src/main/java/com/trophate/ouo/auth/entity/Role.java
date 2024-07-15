package com.trophate.ouo.auth.entity;

import com.trophate.ouo.framework.jpa.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity(name = "role")
@DynamicInsert
@DynamicUpdate
@Where(clause = "deleted = 0")
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
