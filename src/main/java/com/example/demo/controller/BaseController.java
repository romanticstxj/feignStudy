package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.QueryParam;

@RestController
public class BaseController {

    @GetMapping("/test")
    public String test(@RequestHeader String token){
        System.out.println(token);
        return "hello";
    }

    @GetMapping("/find")
    public String find(@QueryParam("var") String var){
        System.out.println(var);
        return "hello";
    }


}
