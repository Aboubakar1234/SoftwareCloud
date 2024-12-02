package com.example.projecttest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;


@RestController
public class hello {

   // @GetMapping("/")
    public String hello(){
        return "helllo";
    }
}
