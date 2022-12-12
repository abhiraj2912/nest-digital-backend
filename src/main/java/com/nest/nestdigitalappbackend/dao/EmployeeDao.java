package com.nest.nestdigitalappbackend.dao;

import com.nest.nestdigitalappbackend.model.Employee;
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




}
