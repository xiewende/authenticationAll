package com.course.authentication.service.impl;

import com.course.authentication.mapper.RoleMapper;
import com.course.authentication.mapper.TeacherMapper;
import com.course.authentication.pojo.Role;
import com.course.authentication.pojo.RoleExample;
import com.course.authentication.pojo.Teacher;
import com.course.authentication.pojo.TeacherExample;
import com.course.authentication.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;

/**
 * @create 2019-12-20  18:15
 */
@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private TeacherMapper teacherMapper;


    @Override
    public Integer insertTeacher(Teacher teacher, String rolename) {

        //设置老师的初始密码为000000
        String initpassword = "000000";
        initpassword = DigestUtils.md5DigestAsHex(initpassword.getBytes());
        teacher.setPassword(initpassword);

        //判断是否重复教师
        TeacherExample teacherExample = new TeacherExample();
        TeacherExample.Criteria teacherExampleCriteria = teacherExample.createCriteria();
        teacherExampleCriteria.andTeacherNumEqualTo(teacher.getTeacherNum());
        List<Teacher> teacherList = teacherMapper.selectByExample(teacherExample);
        if(teacherList.size()>0){
            return 0;
        }

        RoleExample roleExample = new RoleExample();
        RoleExample.Criteria roleExampleCriteria = roleExample.createCriteria();
        roleExampleCriteria.andNameEqualTo(rolename);
        List<Role> roleList = roleMapper.selectByExample(roleExample);
        Role role = roleList.get(0);
        teacher.setRoleId(role.getId());


        return teacherMapper.insert(teacher);
    }

    @Override
    public Integer updateTeacher(Teacher teacher, String rolename) {

        //密码加密
       /* String md5password = DigestUtils.md5DigestAsHex(teacher.getPassword().getBytes());
        teacher.setPassword(md5password);*/
        Teacher teacher1 = teacherMapper.selectByPrimaryKey(teacher.getId());
        teacher.setPassword(teacher1.getPassword());
        RoleExample roleExample = new RoleExample();
        RoleExample.Criteria roleExampleCriteria = roleExample.createCriteria();
        roleExampleCriteria.andNameEqualTo(rolename);
        List<Role> roleList = roleMapper.selectByExample(roleExample);
        Role role = roleList.get(0);
        teacher.setRoleId(role.getId());
        return teacherMapper.updateByPrimaryKey(teacher);
    }

    @Override
    public Integer deleteTeacher(Integer id) {
        return teacherMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Teacher> listTeacher() {
        return teacherMapper.listAllTeacherAndRole();
    }

    @Override
    public List<Teacher> findTeacherByLike(String name) {
        TeacherExample example = new TeacherExample();
        TeacherExample.Criteria criteria = example.createCriteria();
        //criteria.andNameEqualTo(name);
        criteria.andNameLike("%"+name+"%");
        List<Teacher> teacherList = teacherMapper.selectByExample(example);
        /*if(teacherList.size()>0){
            Teacher teacher = teacherList.get(0);
            Role role = roleMapper.selectByPrimaryKey(teacher.getRoleId());
            teacher.setRole(role);
            return teacher;
        }*/
        for(Teacher teacher : teacherList){
            Role role = roleMapper.selectByPrimaryKey(teacher.getRoleId());
            teacher.setRole(role);
        }
        return teacherList;
    }
}
