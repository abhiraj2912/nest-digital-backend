package com.nest.nestdigitalappbackend.dao;

import com.nest.nestdigitalappbackend.model.LeaveApplication;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface LeaveApplicationDao extends CrudRepository<LeaveApplication, Integer> {


    @Query(value = "SELECT `id`, `applied_date`, `emp_id`, `from_date`, `leave_type`, `remarks`, `status`, `to_date` FROM `leaveapplications` WHERE `emp_id`=:empId AND `to_date`> DATE(NOW())",nativeQuery = true)
    List<LeaveApplication> viewStatus(@Param("empId") Integer empId);
    @Query(value = "SELECT `id`, `applied_date`, `emp_id`, `from_date`, `leave_type`, `remarks`, `status`, `to_date` FROM `leaveapplications` WHERE `emp_id`=:empId",nativeQuery = true)
    List<LeaveApplication> viewMyLeave(@Param("empId") Integer empId);

    @Query(value = "SELECT l.id, e.emp_code, e.name, e.designation, l.`applied_date`, l.`from_date`, l.`leave_type`, l.`remarks`, l.`to_date` FROM `leaveapplications` l JOIN employees e ON l.emp_id = e.id WHERE l.status='Pending' ", nativeQuery = true)
    public List<Map<String,String>> pendingLeave();

    @Query(value = "SELECT l.id, e.emp_code, e.name, e.designation, l.`applied_date`, l.`from_date`, l.`leave_type`, l.`remarks`, l.`to_date`, l.status FROM `leaveapplications` l JOIN employees e ON l.emp_id = e.id ", nativeQuery = true)
    public List<Map<String,String>> viewAllLeave();

    @Modifying
    @Transactional
    @Query(value = "UPDATE `leaveapplications` SET `status`=:status WHERE `id`=:id",nativeQuery = true)
    void updateStatus(@Param("id") Integer id, @Param("status") String status);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM `leaveapplications` WHERE `id`=:id",nativeQuery = true)
    void deleteLeave(@Param("id") Integer id);

    @Query(value = "SELECT `id`, `applied_date`, `emp_id`, `from_date`, `leave_type`, `remarks`, `status`, `to_date` FROM `leaveapplications` WHERE `id`=:id",nativeQuery = true)
    List<LeaveApplication> fetchDetails(@Param("id") Integer id);

    @Query(value = "SELECT `id`, `applied_date`, `emp_id`, `from_date`, `leave_type`, `remarks`, `status`, `to_date` FROM `leaveapplications` WHERE `emp_id`=:empId AND `from_date` <= DATE(NOW()) AND `to_date` >= DATE(NOW())  AND `status`='Approved'",nativeQuery = true)
    List<LeaveApplication> securityCheck (@Param("empId") Integer empId);

}
