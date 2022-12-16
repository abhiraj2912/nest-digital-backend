package com.nest.nestdigitalappbackend.controller;

import com.nest.nestdigitalappbackend.dao.EmployeeDao;
import com.nest.nestdigitalappbackend.dao.LeaveCountDao;
import com.nest.nestdigitalappbackend.model.Employee;
import com.nest.nestdigitalappbackend.model.LeaveCount;
import com.nest.nestdigitalappbackend.model.Security;
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
        l.setCasualLeave(20);
        l.setSickLeave(7);
        l.setSpecialLeave(3);
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

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/emplogin", consumes = "application/json", produces = "application/json")
    public HashMap<String, String> Login(@RequestBody Employee e){
        HashMap <String, String> map = new HashMap<>();
        List<Employee> result = edao.LoginCheck(e.getUsername(),e.getPassword());
        if(result.size()==0){
            map.put("status","failed");
            return map;
        }
        else {
            int id = result.get(0).getId();
            int empCode = result.get(0).getEmpCode();
            map.put("id",String.valueOf(id));
            map.put("empCoe",String.valueOf(empCode));
            map.put("status","success");
            return map;
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/empinfo", consumes = "application/json", produces = "application/json")
    public List<Employee> employeeInfo(@RequestBody Employee e){
        return (List<Employee>) edao.employeeInfo(e.getId());
    }
}
