package com.nest.nestdigitalappbackend.dao;

import com.nest.nestdigitalappbackend.model.Employee;
import com.nest.nestdigitalappbackend.model.EmployeeLog;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface EmployeeLogDao extends CrudRepository<EmployeeLog, Integer> {

    @Query(value = "SELECT e.name, e.designation, l.`date`, l.`entry_time`, l.`exit_time` FROM `employeelog` l JOIN employees e ON l.emp_id=e.id",nativeQuery = true)
    List<Map<String,String>> viewEmployeeLog();

    @Query(value = "SELECT e.name, e.designation, l.`date`, l.`entry_time`, l.`exit_time` FROM `employeelog` l JOIN employees e ON l.emp_id=e.id WHERE `date`=:date",nativeQuery = true)
    List<Map<String,String>> employeeLogByDate(@Param("date") String date);
}
