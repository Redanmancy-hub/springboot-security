package com.security.springbootsecurity.controller;


import com.security.springbootsecurity.domain.ResponseResult;
import com.security.springbootsecurity.entity.vo.UserVO;
import com.security.springbootsecurity.service.LoginService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController

public class SecurityController {

    @Resource
    private LoginService loginService;

    /**
     * 测试
     */
    @GetMapping("/test")
    public String test(){
        return "test";
    }
    /**
     * 登录
     * @return
     */
    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody UserVO userVO){
        return loginService.login(userVO);
    }

    @GetMapping("/logout")
    public ResponseResult logout(){
        return loginService.logout();
    }

}
