package com.course.authentication.service.impl;

import com.course.authentication.mapper.StudentMapper;
import com.course.authentication.pojo.Student;
import com.course.authentication.pojo.StudentExample;
import com.course.authentication.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @create 2019-12-20  21:01
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;
    @Override
    public Integer insertStudent(Student student) {
        //判断是否重复
        StudentExample example = new StudentExample();
        StudentExample.Criteria criteria = example.createCriteria();
        criteria.andStudentNumEqualTo(student.getStudentNum());
        List<Student> students = studentMapper.selectByExample(example);
        if(students.size()>0){
            return 0;
        }
        //没有重复
        return studentMapper.insert(student);
    }

    @Override
    public Integer updateStudent(Student student) {
        //有id 前端有传过来
        //没有就找
        StudentExample studentExample = new StudentExample();
        StudentExample.Criteria criteria = studentExample.createCriteria();
        criteria.andStudentNumEqualTo(student.getStudentNum());
        List<Student> students = studentMapper.selectByExample(studentExample);
        Student student1 = students.get(0);
        student.setId(student1.getId());
        return studentMapper.updateByPrimaryKeySelective(student);
    }

    @Override
    public List<Student> listStudent(String grade) {

        StudentExample example = new StudentExample();
        StudentExample.Criteria criteria = example.createCriteria();
        if(grade!=null){
            criteria.andGradeEqualTo(grade);
        }
        return studentMapper.selectByExample(example);
    }

    @Override
    public Integer deleteStudent(Integer id) {
        return studentMapper.deleteByPrimaryKey(id);
    }

    //模糊查询一系列学生
    @Override
    public List<Student> listStudentByName(String name) {
        StudentExample studentExample = new StudentExample();
        StudentExample.Criteria criteria = studentExample.createCriteria();
        criteria.andNameLike("%"+name+"%");
        List<Student> studentList = studentMapper.selectByExample(studentExample);
        return studentList;
    }
}
