package com.security.springbootsecurity;

import com.security.springbootsecurity.entity.User;
import com.security.springbootsecurity.mapper.MenuMapper;
import com.security.springbootsecurity.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class SpringbootSecurityApplicationTests {
    @Resource
    private UserMapper userMapper;

    @Resource
    private MenuMapper menuMapper;

    @Test
    void selectDB() {
/*        List<User> users = userMapper.selectList(null);
        System.out.println(users);*/
    }

    @Test
    void testSelectPermsByUserId(){
        List<String> list = menuMapper.selectPermsByUserId(2L);
        System.out.println(list);
    }

}
