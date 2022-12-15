package com.nest.nestdigitalappbackend.dao;

import com.nest.nestdigitalappbackend.model.Employee;
import com.nest.nestdigitalappbackend.model.Security;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeDao extends CrudRepository<Employee, Integer> {

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM `employees` WHERE `id`=:id",nativeQuery = true)
    void deleteEmployee(@Param("id") Integer id);

    @Query(value = "SELECT `id`, `address`, `designation`, `dob`, `email`, `emp_code`, `name`, `password`, `phone`, `salary`, `username` FROM `employees` WHERE `name` LIKE %:name%", nativeQuery = true)
    List<Employee> searchEmployee(@Param("name") String name);

    @Query(value = "SELECT `id`, `address`, `designation`, `dob`, `email`, `emp_code`, `name`, `password`, `phone`, `salary`, `username` FROM `employees` WHERE `emp_code` =:empCode", nativeQuery = true)
    List<Employee> fetchId(@Param("empCode") Integer empCode);

    @Query(value = "SELECT `id`, `address`, `designation`, `dob`, `email`, `emp_code`, `name`, `password`, `phone`, `salary`, `username` FROM `employees` WHERE `username`=:username AND `password` =:password",nativeQuery = true)
    List <Employee> LoginCheck(@Param("username") String username, @Param("password") String password);

    @Query(value = "SELECT `id`, `address`, `designation`, `dob`, `email`, `emp_code`, `name`, `password`, `phone`, `salary`, `username` FROM `employees` WHERE `id`=:id", nativeQuery = true)
    List <Employee> employeeInfo(@Param("id") Integer id);

}
