package com.sda.springdata2.chapter2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWorldController {
    @RequestMapping(value = "/index")
    public String getIndex(){return "index";}
}
