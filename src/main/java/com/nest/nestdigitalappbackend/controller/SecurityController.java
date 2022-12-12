package com.nest.nestdigitalappbackend.controller;

import com.nest.nestdigitalappbackend.dao.SecurityDao;
import com.nest.nestdigitalappbackend.model.Security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class SecurityController {

    @Autowired
    private SecurityDao sdao;

    @CrossOrigin(value = "*")
    @PostMapping(path = "/addsecurity", consumes = "application/json", produces = "application/json")
    public HashMap<String,String> addSecurity(@RequestBody Security s){
        sdao.save(s);
        HashMap<String,String> map = new HashMap<>();
        map.put("status","success");
        return map;
    }

    @CrossOrigin(value = "*")
    @PostMapping(path = "/deletesecurity", consumes = "application/json", produces = "application/json")
    public HashMap<String, String> securityDelete(@RequestBody Security s){
        sdao.deleteEmployee(s.getId());
        HashMap<String, String> map = new HashMap<>();
        map.put("status","success");
        return map;
    }

    @CrossOrigin(value = "*")
    @GetMapping("/viewsecurity")
    public List<Security> securityView(){
        return (List<Security>) sdao.findAll();
    }


}
