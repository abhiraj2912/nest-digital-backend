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

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/seclogin", consumes = "application/json", produces = "application/json")
    public HashMap<String, String> Login(@RequestBody Security s){
        HashMap <String, String> map = new HashMap<>();
        List<Security> result = sdao.LoginCheck(s.getUsername(),s.getPassword());
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
    @PostMapping(path = "/secinfo", consumes = "application/json", produces = "application/json")
    public List<Security> UserInfo(@RequestBody Security s){
        return (List<Security>) sdao.securityInfo(s.getId());
    }



}
