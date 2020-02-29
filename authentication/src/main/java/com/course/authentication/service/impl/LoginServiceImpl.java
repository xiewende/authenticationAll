package com.course.authentication.service.impl;

import com.course.authentication.mapper.RoleMapper;
import com.course.authentication.mapper.TeacherMapper;
import com.course.authentication.pojo.Role;
import com.course.authentication.pojo.Teacher;
import com.course.authentication.pojo.TeacherExample;
import com.course.authentication.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;

/**
 * @create 2019-12-28  19:19
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Teacher Login(String teacherNum, String password) {
        TeacherExample example = new TeacherExample();
        TeacherExample.Criteria criteria = example.createCriteria();
        //判断用户名是否存在
        criteria.andTeacherNumEqualTo(teacherNum);
        List<Teacher> teacherList = teacherMapper.selectByExample(example);
        if(teacherList == null || teacherList.size() == 0){                     //不存在
            return null;
        }

        //判断密码是否正确
        Teacher teacher = teacherList.get(0);
        //填入的密码加墨
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        if(!teacher.getPassword().equals(password)){                      //密码不正确
            return null;
        }

        //将角色设置进去
        Role role = roleMapper.selectByPrimaryKey(teacher.getRoleId());
        teacher.setRole(role);
        return teacher;


    }
}
