package com.course.authentication.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.DigestUtils;

/**
 * @create 2019-12-28  19:10
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MD5Test {

    @Test
    public void test1(){
        String initpassword = "admin";
        initpassword = DigestUtils.md5DigestAsHex(initpassword.getBytes());
        System.out.println(initpassword);
    }
}
