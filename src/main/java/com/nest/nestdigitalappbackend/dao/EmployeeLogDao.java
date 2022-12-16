package com.nest.nestdigitalappbackend.dao;

import com.nest.nestdigitalappbackend.model.Employee;
import com.nest.nestdigitalappbackend.model.EmployeeLog;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface EmployeeLogDao extends CrudRepository<EmployeeLog, Integer> {

    @Query(value = "SELECT e.emp_code, e.name, e.designation, l.`date`, l.`entry_time`, l.`exit_time` FROM `employeelog` l JOIN employees e ON l.emp_id=e.id",nativeQuery = true)
    List<Map<String,String>> viewEmployeeLog();

    @Query(value = "SELECT e.emp_code, e.name, e.designation, l.`date`, l.`entry_time`, l.`exit_time` FROM `employeelog` l JOIN employees e ON l.emp_id=e.id WHERE `date`=:date",nativeQuery = true)
    List<Map<String,String>> employeeLogByDate(@Param("date") String date);

    @Transactional
    @Modifying
    @Query(value = "UPDATE `employeelog` SET `exit_time`=:exitTime WHERE `id`=:id",nativeQuery = true)
    void addEmpExitLog(@Param("id") Integer id, @Param("exitTime") String exitTime);

    @Query(value = "SELECT e.emp_code, e.name, e.designation, l.id, l.`date`, l.`entry_time`, l.`exit_time` FROM `employeelog` l JOIN employees e ON l.emp_id=e.id WHERE e.emp_code=:empCode AND l.date=:date AND exit_time='Not Available'",nativeQuery = true)
    List<Map<String,String>> employeeLogToday(@Param("empCode") Integer empCode, @Param("date") String date);

}
