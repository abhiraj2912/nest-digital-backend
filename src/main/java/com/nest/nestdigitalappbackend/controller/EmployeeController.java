package com.nest.nestdigitalappbackend.controller;

import com.nest.nestdigitalappbackend.dao.EmployeeDao;
import com.nest.nestdigitalappbackend.dao.LeaveCountDao;
import com.nest.nestdigitalappbackend.model.Employee;
import com.nest.nestdigitalappbackend.model.LeaveCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeDao edao;

    @Autowired
    private LeaveCountDao ldao;

    @CrossOrigin(value = "*")
    @PostMapping(path = "/addemployee", consumes = "application/json", produces = "application/json")
    public HashMap<String,String> employeeAdd(@RequestBody Employee e){
        edao.save(e);
        LeaveCount l = new LeaveCount();
        l.setEmpId(e.getId());
        l.setYear("2022");
        l.setCasualLeave("20");
        l.setSickLeave("7");
        l.setSpecialLeave("3");
        ldao.save(l);
        HashMap<String, String> map = new HashMap<>();
        map.put("status","success");
        return map;
    }

    @CrossOrigin(value = "*")
    @PostMapping(path = "/deleteemployee", consumes = "application/json", produces = "application/json")
    public HashMap<String, String> employeeDelete(@RequestBody Employee e){
        edao.deleteEmployee(e.getId());
        HashMap<String, String> map = new HashMap<>();
        map.put("status","success");
        return map;
    }

    @CrossOrigin(value = "*")
    @GetMapping("/viewemployee")
    public List<Employee> employeeView(){
        return (List<Employee>) edao.findAll();
    }

    @CrossOrigin(value = "*")
    @PostMapping(value = "/searchemployee", consumes = "application/json", produces = "application/json")
    public List<Employee> searchEmployee(@RequestBody Employee e){
        return (List<Employee>) edao.searchEmployee(e.getName());
    }
}
