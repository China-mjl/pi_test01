package com.pitest01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
    @RequestMapping("/Index")
    public String sayHello(){
        return "index";
    }
}
