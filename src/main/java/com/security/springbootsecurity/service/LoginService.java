package com.security.springbootsecurity.service;

import com.security.springbootsecurity.domain.ResponseResult;
import com.security.springbootsecurity.entity.vo.UserVO;
import org.springframework.transaction.annotation.Transactional;

public interface LoginService {
    /**
     * 登录
     * @param userVO=》仅存入用户名和密码
     * @return
     */
    @Transactional
    ResponseResult login(UserVO userVO);

    ResponseResult logout();
}
