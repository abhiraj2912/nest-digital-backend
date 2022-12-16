package com.nest.nestdigitalappbackend.controller;

import com.nest.nestdigitalappbackend.dao.EmployeeDao;
import com.nest.nestdigitalappbackend.dao.EmployeeLogDao;
import com.nest.nestdigitalappbackend.dao.LeaveApplicationDao;
import com.nest.nestdigitalappbackend.model.Employee;
import com.nest.nestdigitalappbackend.model.EmployeeLog;
import com.nest.nestdigitalappbackend.model.LeaveApplication;
import com.nest.nestdigitalappbackend.model.Security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class EmployeeLogController {

    @Autowired
    private EmployeeLogDao eldao;

    @Autowired
    private EmployeeDao edao;

    @Autowired
    private LeaveApplicationDao ladao;

    @CrossOrigin(value = "*")
    @PostMapping(path = "/addemployeelog", consumes = "application/json", produces = "application/json")
    public HashMap<String,String> addEmployeeLog(@RequestBody EmployeeLog el){
        List<LeaveApplication> result = ladao.securityCheck(el.getEmpId());
        HashMap<String,String> map = new HashMap<>();
        if(result.size()==0){
            eldao.save(el);
            map.put("status","success");
            return map;
        }
        else {
            map.put("status","failed");
            return map;
        }
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

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/fetchempid", consumes = "application/json", produces = "application/json")
    public HashMap<String, String> fetchId(@RequestBody Employee e){
        HashMap <String, String> map = new HashMap<>();
        List<Employee> result = edao.fetchId(e.getEmpCode());
        if(result.size()==0){
            map.put("status","failed");
            return map;
        }
        else {
            int id = result.get(0).getId();
            map.put("id",String.valueOf(id));
            map.put("status","success");
            return map;
        }

    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/addempexitlog", consumes = "application/json", produces = "application/json")
    public HashMap<String,String> addExitEmployeeLog(@RequestBody EmployeeLog el){
        eldao.addEmpExitLog(el.getId(),el.getExitTime());
        HashMap<String,String> map = new HashMap<>();
        map.put("status","success");
        return map;
    }

    @CrossOrigin(value = "*")
    @PostMapping(path = "/emplogtoday", consumes = "application/json", produces = "application/json")
    public List<Map<String,String>> employeeLogToday(@RequestBody Employee e){
        LocalDate ld = LocalDate.now();
        String date = String.valueOf(ld);
        return eldao.employeeLogToday(e.getEmpCode(), date);
    }

}
