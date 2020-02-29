package com.course.authentication.service;

import com.course.authentication.pojo.Teacher;

/**
 * @create 2019-12-28  19:17
 */
public interface LoginService {

    //登陆
    public Teacher Login(String teacherNum, String password);

}
