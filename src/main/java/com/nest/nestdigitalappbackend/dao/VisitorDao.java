package com.nest.nestdigitalappbackend.dao;

import com.nest.nestdigitalappbackend.model.VisitorLog;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VisitorDao extends CrudRepository<VisitorLog, Integer> {

    @Query(value = "SELECT `id`, `date`, `entry_time`, `exit_time`, `name`, `purpose`, `whom_to_meet` FROM `visitorlog` WHERE `date` =:date",nativeQuery = true)
    List<VisitorLog> visitorByDate(@Param("date") String date);

    @Query(value = "SELECT `id`, `date`, `entry_time`, `exit_time`, `name`, `purpose`, `whom_to_meet` FROM `visitorlog` WHERE name=:name AND `date` =:date AND exit_time='Not Available'",nativeQuery = true)
    List<VisitorLog> visitorByName(@Param("name") String name, @Param("date") String date);

    @Transactional
    @Modifying
    @Query(value = "UPDATE `visitorlog` SET `exit_time`=:exitTime WHERE `id`=:id",nativeQuery = true)
    void addVisExitLog(@Param("id") Integer id, @Param("exitTime") String exitTime);
}
