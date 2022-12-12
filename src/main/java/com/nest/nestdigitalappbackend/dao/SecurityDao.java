package com.nest.nestdigitalappbackend.dao;

import com.nest.nestdigitalappbackend.model.Security;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SecurityDao extends CrudRepository<Security, Integer> {

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM `securities` WHERE `id`=:id",nativeQuery = true)
    void deleteEmployee(@Param("id") Integer id);

}
