package com.nest.nestdigitalappbackend.dao;

import com.nest.nestdigitalappbackend.model.VisitorLog;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VisitorDao extends CrudRepository<VisitorLog, Integer> {

    @Query(value = "SELECT `id`, `date`, `entry_time`, `exit_time`, `name`, `purpose`, `whom_to_meet` FROM `visitorlog` WHERE `date` =:date",nativeQuery = true)
    List<VisitorLog> visitorByDate(@Param("date") String date);
}
