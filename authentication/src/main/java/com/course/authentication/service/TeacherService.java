package com.course.authentication.service;

import com.course.authentication.pojo.Teacher;

import java.util.List;

/**
 * @create 2019-12-20  16:31
 */
public interface TeacherService {

    //添加用户
    public Integer insertTeacher(Teacher teacher,String rolename);

    //修改用户
    public Integer updateTeacher(Teacher teacher,String rolename);

    //删除用户
    public Integer deleteTeacher(Integer id);

    //查询所有教师，
    List<Teacher> listTeacher();

    //查询某个教师
    List<Teacher> findTeacherByLike(String name);



}
