package com.course.authentication.dao;

import com.course.authentication.mapper.TeacherMapper;
import com.course.authentication.pojo.Teacher;
import com.course.authentication.service.TeacherService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @create 2019-12-20  16:32
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class TeacherTest {

    @Autowired
    private TeacherMapper mapper;

    @Autowired
    private TeacherService teacherService;

    @Test
    public void inserrt(){
        Teacher teacher = new Teacher();
        teacher.setName("李四");
        //teacher.setPassword("123456");
        teacher.setTeacherNum("201711");
        teacher.setRoleId(1);
        mapper.insert(teacher);
    }

    @Test
    public void select(){
        List<Teacher> teachers = mapper.listAllTeacherAndRole();
        System.out.println(teachers.size());
    }
}
