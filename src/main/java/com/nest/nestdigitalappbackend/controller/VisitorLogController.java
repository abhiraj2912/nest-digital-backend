package com.nest.nestdigitalappbackend.controller;

import com.nest.nestdigitalappbackend.dao.VisitorDao;
import com.nest.nestdigitalappbackend.model.EmployeeLog;
import com.nest.nestdigitalappbackend.model.VisitorLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

@RestController
public class VisitorLogController {

    @Autowired
    private VisitorDao vdao;

    @CrossOrigin(value = "*")
    @PostMapping(path = "/addvisitorlog", consumes = "application/json", produces = "application/json")
    public HashMap<String,String> addVisitorLog(@RequestBody VisitorLog v){
        vdao.save(v);
        HashMap<String,String> map = new HashMap<>();
        map.put("status","success");
        return map;
    }

    @CrossOrigin(value = "*")
    @GetMapping("/visitorlog")
    public List<VisitorLog> viewVisitorLog(){
        return (List<VisitorLog>) vdao.findAll();
    }

    @CrossOrigin(value = "*")
    @PostMapping(path = "/visitorbydate", consumes = "application/json", produces = "application/json")
    public List<VisitorLog> visitorByDate(@RequestBody VisitorLog v){
        return vdao.visitorByDate(v.getDate());
    }


    @CrossOrigin(origins = "*")
    @PostMapping(path = "/addvisitorexitlog", consumes = "application/json", produces = "application/json")
    public HashMap<String,String> addVisExitLog(@RequestBody VisitorLog vl){
        vdao.addVisExitLog(vl.getId(),vl.getExitTime());
        HashMap<String,String> map = new HashMap<>();
        map.put("status","success");
        return map;
    }

    @CrossOrigin(value = "*")
    @PostMapping(path = "/visitorbyname", consumes = "application/json", produces = "application/json")
    public List<VisitorLog> visitorByName(@RequestBody VisitorLog v){
        LocalDate ld = LocalDate.now();
        String date = String.valueOf(ld);
        return vdao.visitorByName(v.getName(),date);
    }
}
