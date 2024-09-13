package com.trophate.ouo.framework.jpa;

import com.trophate.ouo.framework.security.CurrentUser;
import com.trophate.ouo.framework.security.SecurityUtils;
import org.springframework.stereotype.Component;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@Component
public class CustomEntityListener {

    private final int SYS_USER_ID = 0;

    @PrePersist
    public void setValueForInsert(Object obj) {
        if (obj instanceof BaseEntity) {
            BaseEntity entity = (BaseEntity) obj;
            CurrentUser user = SecurityUtils.getCurrentUser();
            LocalDateTime now = LocalDateTime.now();
            if (user != null) {
                entity.setCreatedById(user.getId());
                entity.setUpdatedById(user.getId());
            } else {
                entity.setCreatedById(SYS_USER_ID);
                entity.setUpdatedById(SYS_USER_ID);
            }
            entity.setCreatedAt(now);
            entity.setUpdatedAt(now);
        }
    }

    @PreUpdate
    public void setValueForUpdate(Object obj) {
        if (obj instanceof BaseEntity) {
            BaseEntity entity = (BaseEntity) obj;
            CurrentUser user = SecurityUtils.getCurrentUser();
            LocalDateTime now = LocalDateTime.now();
            if (user != null) {
                entity.setUpdatedById(user.getId());
            } else {
                entity.setUpdatedById(SYS_USER_ID);
            }
            entity.setUpdatedAt(now);
        }
    }
}
