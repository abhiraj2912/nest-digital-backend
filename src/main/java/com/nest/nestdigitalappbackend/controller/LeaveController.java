package com.nest.nestdigitalappbackend.controller;

import com.nest.nestdigitalappbackend.dao.LeaveApplicationDao;
import com.nest.nestdigitalappbackend.dao.LeaveCountDao;
import com.nest.nestdigitalappbackend.model.EmployeeLog;
import com.nest.nestdigitalappbackend.model.LeaveApplication;
import com.nest.nestdigitalappbackend.model.LeaveCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Period;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class LeaveController {

    @Autowired
    private LeaveApplicationDao ladao;

    @Autowired
    private LeaveCountDao lcdao;

    @CrossOrigin(value = "*")
    @PostMapping(path = "/applyleave", consumes = "application/json", produces = "application/json")
    public HashMap<String,String> applyLeave(@RequestBody LeaveApplication la){
        ladao.save(la);
        HashMap<String,String> map = new HashMap<>();
        map.put("status","success");
        return map;
    }

    @CrossOrigin(value = "*")
    @PostMapping(path = "/myleave", consumes = "application/json", produces = "application/json")
    public List<LeaveApplication> viewMyLeave(@RequestBody LeaveApplication la){
        return ladao.viewMyLeave(la.getEmpId());
    }

    @CrossOrigin(value = "*")
    @PostMapping(path = "/viewleave", consumes = "application/json", produces = "application/json")
    public List<Map<String,String>> pendingApproval(){
        return ladao.pendingLeave();
    }

    @CrossOrigin(value = "*")
    @PostMapping(path = "/leavestatus", consumes = "application/json", produces = "application/json")
    public HashMap<String,String> updateStatus(@RequestBody LeaveApplication la){
        ladao.updateStatus(la.getId(),la.getStatus());
        HashMap<String,String> map = new HashMap<>();
        map.put("status","success");
        return map;
    }
}
