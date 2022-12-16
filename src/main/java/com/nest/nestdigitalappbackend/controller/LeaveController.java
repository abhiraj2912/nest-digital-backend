package com.nest.nestdigitalappbackend.controller;

import com.nest.nestdigitalappbackend.dao.LeaveApplicationDao;
import com.nest.nestdigitalappbackend.dao.LeaveCountDao;
import com.nest.nestdigitalappbackend.model.EmployeeLog;
import com.nest.nestdigitalappbackend.model.LeaveApplication;
import com.nest.nestdigitalappbackend.model.LeaveCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
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
        LocalDate ld = LocalDate.now();
        la.setAppliedDate(String.valueOf(ld));
        ladao.save(la);
        HashMap<String,String> map = new HashMap<>();
        switch (la.getLeaveType()) {
            case "Casual" ->lcdao.casualLeave(la.getId(), la.getEmpId());
            case "Sick" -> lcdao.sickLeave(la.getId(), la.getEmpId());
            case "Special" ->lcdao.specialLeave(la.getId(), la.getEmpId());
        }
        map.put("status","success");
        return map;
    }

    @CrossOrigin(value = "*")
    @PostMapping(path = "/myleave", consumes = "application/json", produces = "application/json")
    public List<LeaveApplication> viewMyLeave(@RequestBody LeaveApplication la){
        return ladao.viewMyLeave(la.getEmpId());
    }

    @CrossOrigin(value = "*")
    @GetMapping("/viewleave")
    public List<Map<String,String>> pendingApproval(){
        return ladao.pendingLeave();
    }

    @CrossOrigin(value = "*")
    @GetMapping("/viewallleave")
    public List<Map<String,String>> viewLeave(){
        return ladao.viewAllLeave();
    }
    @CrossOrigin(value = "*")
    @PostMapping(path = "/updatestatus", consumes = "application/json", produces = "application/json")
    public HashMap<String,String> updateStatus(@RequestBody LeaveApplication la){
        ladao.updateStatus(la.getId(),la.getStatus());
        List<LeaveApplication> empId = ladao.fetchDetails(la.getId());
        if (la.getStatus().equals("Rejected")){
            List <LeaveApplication> type = ladao.fetchDetails(la.getId());
            switch (type.get(0).getLeaveType()) {
                case "Casual" -> lcdao.rejCasualLeave(la.getId(), empId.get(0).getEmpId());
                case "Sick" -> lcdao.rejSickLeave(la.getId(), empId.get(0).getEmpId());
                case "Special" -> lcdao.rejSpecialLeave(la.getId(), empId.get(0).getEmpId());
            }
        }
        HashMap<String,String> map = new HashMap<>();
        map.put("status","success");
        return map;
    }

    @CrossOrigin(value = "*")
    @PostMapping(path = "/empViewPending", consumes = "application/json", produces = "application/json")
    public List<LeaveApplication> empViewPending(@RequestBody LeaveApplication la){
        return ladao.viewStatus(la.getEmpId());
    }

    @CrossOrigin(value = "*")
    @PostMapping(path = "/getcount", consumes = "application/json", produces = "application/json")
    public List<LeaveCount> getLeaveCount(@RequestBody LeaveCount lc){
        return lcdao.getCount(lc.getEmpId());
    }

}
