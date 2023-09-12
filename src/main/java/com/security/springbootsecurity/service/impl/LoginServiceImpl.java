package com.security.springbootsecurity.service.impl;

import com.security.springbootsecurity.domain.LoginUser;

import com.security.springbootsecurity.domain.ResponseResult;
import com.security.springbootsecurity.entity.vo.UserVO;
import com.security.springbootsecurity.service.LoginService;
import com.security.springbootsecurity.utils.JwtUtil;
import com.security.springbootsecurity.utils.RedisCache;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private RedisCache redisCache;

    /**
     * 登录
     * @param userVO=》仅存入用户名和密码
     * @return
     */
    @Override
    public ResponseResult login(UserVO userVO) {
        //获取AuthenticationManage authenticate进行用户验证
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(userVO.getUserName(), userVO.getPassword());
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        //认证未通过，返回一个异常
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("认证未通过");
        }
        ////认证通过，使用userid生成一个jwt jwt存入ResponseResult返回
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        //获取用户id
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);

        Map<String, String> map = new HashMap<>();
        map.put("token", jwt);
        //把完整的信息存入redis userid作为key
        redisCache.setCacheObject("token"+userId,loginUser);
        return new ResponseResult(200,"登录成功",map);
    }

    /**
     * 登出
     * @return
     */
    @Override
    public ResponseResult logout() {
        //获取SecurityContextHolder中的用户id
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userId = loginUser.getUser().getId();
        //删除redis中的值
        redisCache.deleteObject("login"+userId);
        return new ResponseResult(200,"登录成功");
    }
}
