package com.course.authentication.controller;

import com.course.authentication.pojo.Course;
import com.course.authentication.pojo.Student;
import com.course.authentication.service.StudentService;
import com.course.authentication.util.Result;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @create 2019-12-20  21:09  学生管理
 */
@CrossOrigin
@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;

    //添加学生
    @PostMapping("/addStudent")
    public Result addStudent(Student student){
        if(StringUtils.isBlank(student.getName()) || StringUtils.isBlank(student.getClassName()) || StringUtils.isBlank(student.getGrade()) ||
                StringUtils.isBlank(student.getMajor()) || StringUtils.isBlank(student.getStudentNum())){
            return Result.build(400,"输入的数据不能为空！！！！");
        }
        Integer integer = studentService.insertStudent(student);
        if(integer>0){
            return Result.ok();
        }else if (integer == 0){
            return Result.build(500,"输入的学生已经存在本系统！！");
        }else {
            return Result.build(500,"系统错误！！");
        }
    }

    //修改学生信息
    @PostMapping("/updateStudent")
    public Result updateStudent(Student student){
        if(StringUtils.isBlank(student.getName()) || StringUtils.isBlank(student.getClassName()) || StringUtils.isBlank(student.getGrade()) ||
                StringUtils.isBlank(student.getMajor()) || StringUtils.isBlank(student.getStudentNum())){
            return Result.build(400,"输入的数据不能为空！！！！");
        }
        Integer integer = studentService.updateStudent(student);
        if(integer >0){
            return Result.ok();
        }else {
            return Result.build(500,"系统出现故障，请稍后重试！！！");
        }
    }

    //删除学生
    @PostMapping("/deleteStudent")
    public Result delelteStudent(Integer id){
        Integer integer = studentService.deleteStudent(id);
        if(integer>0){
            return Result.ok();
        }else {
            return Result.build(500,"系统出现故障，请稍后重试！！！");
        }
    }

    //查询所有学生
    @PostMapping("/listStudent")
    public Result listStudent(String grade,@RequestParam(required=true,defaultValue="1") Integer pageNum){
        if("".equals(grade)){
            grade = null;
        }
        PageHelper.startPage(pageNum,3);
        List<Student> studentList = studentService.listStudent(grade);
        PageInfo<Student> teacherPageInfo = new PageInfo<>(studentList);
        if(studentList.size()>0){
            return Result.build(200,"有数据",teacherPageInfo);
        }else {
            return Result.build(500,"系统错误！！");
        }
    }

    //查询所有学生 不分页
    @PostMapping("/listAllStudent")
    public Result listAllStudent(String grade){
        if("".equals(grade)){
            grade = null;
        }
        List<Student> studentList = studentService.listStudent(grade);
        if(studentList.size()>0){
            return Result.build(200,"有数据",studentList);
        }else {
            return Result.build(500,"系统错误！！");
        }
    }

    //模糊查询某几位学生
    @PostMapping("/listStudentByName")
    public Result listStudentByName(String name){
        if(StringUtils.isBlank(name)){
            return Result.build(400,"输入的数据不能为空！！！！");
        }
        List<Student> studentList = studentService.listStudentByName(name);
        if(studentList.size()>0){
            return Result.build(200,"有数据！！",studentList);
        }else {
            return Result.build(500,"没有数据！！！");
        }

    }

}
