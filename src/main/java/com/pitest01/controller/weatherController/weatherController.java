package com.pitest01.controller.weatherController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class weatherController {
    @RequestMapping("/weatherIndex")
    public String sayHello(){
        return "util/weather/weatherIndex";
    }
}
