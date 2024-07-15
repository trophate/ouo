package com.trophate.ouo.auth.entity;

import com.trophate.ouo.framework.jpa.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity(name = "permission")
@DynamicInsert
@DynamicUpdate
@Where(clause = "deleted = 0")
public class Permission extends BaseEntity implements Serializable {

    /**
     * 名称
     */
    private String name;
    /**
     * 编码
     */
    private String code;
    /**
     * 类型
     */
    private Integer type;
    /**
     * 父级id
     */
    private Integer parentId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}
