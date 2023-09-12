package com.security.springbootsecurity.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.security.springbootsecurity.domain.LoginUser;
import com.security.springbootsecurity.entity.User;
import com.security.springbootsecurity.mapper.MenuMapper;
import com.security.springbootsecurity.mapper.UserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private MenuMapper menuMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //查询用户信息
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUserName,username);
        User user = userMapper.selectOne(lambdaQueryWrapper);
        if (Objects.isNull(user)){
            throw new RuntimeException("用户名错误");
        }
        // 查询对应用户的权限信息
//        List<String> list = Arrays.asList("test","admin");
        List<String> list = menuMapper.selectPermsByUserId(user.getId());

        //把查询到的数据封装到UserDetails返回
        return new LoginUser(user,list);
    }
}
