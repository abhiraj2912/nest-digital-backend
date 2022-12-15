package com.nest.nestdigitalappbackend.dao;

import com.nest.nestdigitalappbackend.model.LeaveApplication;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface LeaveApplicationDao extends CrudRepository<LeaveApplication, Integer> {

    @Query(value = "SELECT `id`, `applied_date`, `emp_id`, `from_date`, `leave_type`, `remarks`, `status`, `to_date` FROM `leaveapplications` WHERE `emp_id`=:empId",nativeQuery = true)
    List<LeaveApplication> viewMyLeave(@Param("empId") Integer empId);

    @Query(value = "SELECT e.emp_code, e.name, e.designation, l.`applied_date`, l.`from_date`, l.`leave_type`, l.`remarks`, l.`to_date` FROM `leaveapplications` l JOIN employees e ON l.emp_id = e.id WHERE l.status='Pending' ", nativeQuery = true)
    public List<Map<String,String>> pendingLeave();

    @Query(value = "UPDATE `leaveapplications` SET `status`=:status WHERE `id`=:id",nativeQuery = true)
    void updateStatus(Integer id, String status);
}
