package com.course.authentication.service;

import com.course.authentication.pojo.Student;

import java.util.List;

/**
 * @create 2019-12-20  19:59
 */
public interface StudentService {

    //添加学生
    Integer insertStudent(Student student);

    //修改学生
    Integer updateStudent(Student student);

    //查询学生
    List<Student> listStudent(String grade);

    //删除一个学生
    Integer deleteStudent(Integer id);

    //模糊查询某几位学生
    List<Student> listStudentByName(String name);

}
