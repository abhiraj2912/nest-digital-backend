package com.nest.nestdigitalappbackend.controller;

import com.nest.nestdigitalappbackend.dao.EmployeeLogDao;
import com.nest.nestdigitalappbackend.model.EmployeeLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class EmployeeLogController {

    @Autowired
    private EmployeeLogDao eldao;


    @CrossOrigin(value = "*")
    @PostMapping(path = "/addemployeelog", consumes = "application/json", produces = "application/json")
    public HashMap<String,String> addEmployeeLog(@RequestBody EmployeeLog el){
        eldao.save(el);
        HashMap<String,String> map = new HashMap<>();
        map.put("status","success");
        return map;
    }

    @CrossOrigin(value = "*")
    @GetMapping("/emplogview")
    public List<Map<String,String>> viewEmpLog(){
        return eldao.viewEmployeeLog();
    }

    @CrossOrigin(value = "*")
    @PostMapping(path = "/emplogdate", consumes = "application/json", produces = "application/json")
    public List<Map<String,String>> employeeLogByDate(@RequestBody EmployeeLog el){
        return eldao.employeeLogByDate(el.getDate());
    }
}
