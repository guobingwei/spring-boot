package com.example.controller;

import com.example.domain.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by john on 2016/3/15.
 */
@RestController
@EnableAutoConfiguration
public class HelloController {
    @RequestMapping("/hello")
    List hello() {
        List list = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            Map r = new HashMap<>();
            r.put("id", i);
            r.put("name", i + "jkfk");
            list.add(r);
        }
        return list;
    }

    @Autowired
    UserService service;

    @RequestMapping("/fromdb")
    List getFromDb() {

        List ll = service.getAll();
        return ll;
    }

    @RequestMapping(value = "test-post", method = RequestMethod.POST)
    public String insertData(@RequestBody User user) {
        if(user != null) {
            service.insertData(user);
        }

        return "ok";
    }
}
