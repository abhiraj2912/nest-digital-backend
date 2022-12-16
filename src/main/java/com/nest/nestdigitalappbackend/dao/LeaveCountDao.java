package com.nest.nestdigitalappbackend.dao;

import com.nest.nestdigitalappbackend.model.LeaveCount;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LeaveCountDao extends CrudRepository<LeaveCount, Integer> {



    @Transactional
    @Modifying
    @Query(value = "UPDATE `leavecount` SET `casual_leave`=`casual_leave`-(SELECT  DATEDIFF(`to_date`, `from_date`)+1 FROM `leaveapplications` WHERE `id`=:id) WHERE `emp_id`=:empId",nativeQuery = true)
    void casualLeave (@Param("id") Integer id, @Param("empId") Integer empId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE `leavecount` SET `sick_leave`=`sick_leave`-(SELECT  DATEDIFF(`to_date`, `from_date`)+1 FROM `leaveapplications` WHERE `id`=:id) WHERE `emp_id`=:empId",nativeQuery = true)
    void sickLeave (@Param("id") Integer id, @Param("empId") Integer empId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE `leavecount` SET `special_leave`=`special_leave`-(SELECT  DATEDIFF(`to_date`, `from_date`)+1 FROM `leaveapplications` WHERE `id`=:id) WHERE `emp_id`=:empId",nativeQuery = true)
    void specialLeave (@Param("id") Integer id, @Param("empId") Integer empId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE `leavecount` SET `casual_leave`=`casual_leave`+(SELECT  DATEDIFF(`to_date`, `from_date`)+1 FROM `leaveapplications` WHERE `id`=:id) WHERE `emp_id`=:empId",nativeQuery = true)
    void rejCasualLeave (@Param("id") Integer id, @Param("empId") Integer empId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE `leavecount` SET `sick_leave`=`sick_leave`+(SELECT  DATEDIFF(`to_date`, `from_date`)+1 FROM `leaveapplications` WHERE `id`=:id) WHERE `emp_id`=:empId",nativeQuery = true)
    void rejSickLeave (@Param("id") Integer id, @Param("empId") Integer empId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE `leavecount` SET `special_leave`=`special_leave`+(SELECT  DATEDIFF(`to_date`, `from_date`)+1 FROM `leaveapplications` WHERE `id`=:id) WHERE `emp_id`=:empId",nativeQuery = true)
    void rejSpecialLeave (@Param("id") Integer id, @Param("empId") Integer empId);


    @Query(value = "SELECT `id`, `casual_leave`, `emp_id`, `sick_leave`, `special_leave`, `year` FROM `leavecount` WHERE `emp_id`=:empId",nativeQuery = true)
    List<LeaveCount> getCount (@Param("empId") Integer empId);

}
