package com.pitest01.controller.userController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
    @RequestMapping("/userIndex")
    public String sayHello(){
        return "user/userIndex";
    }
}
