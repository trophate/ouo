package com.trophate.ouo.framework.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface CustomRepository<T, ID> extends JpaRepository<T, ID> {

    /**
     * 该方法是逻辑删除。
     *
     * @param id id
     */
    @Modifying
    @Transactional
    @Query(value = "UPDATE #{#entityName} SET deleted = 1, deleted_at = NOW() WHERE id = :id", nativeQuery = true)
    void deleteByIdInLogical(@Param("id") Integer id);
}
