package com.security.springbootsecurity.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class TestController {

    @PreAuthorize("hasAnyAuthority('system:dept:list')")
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
}
