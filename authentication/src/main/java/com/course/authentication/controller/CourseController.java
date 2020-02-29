package com.course.authentication.controller;

import com.course.authentication.pojo.Course;
import com.course.authentication.service.CourseService;
import com.course.authentication.util.Result;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @create 2019-12-22  19:53  课程管理
 */
@CrossOrigin
@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;

    //添加课程 课程编撰者的传入的是他的id
    @PostMapping("/addCourse")
    public Result addCourse(Course course,String teacherName){
        if(StringUtils.isBlank(course.getCourseNum()) || course.getCredit()==null || course.getExperTime()==null || StringUtils.isBlank(course.getName())
        ||StringUtils.isBlank(course.getProperty()) || course.getTheoryTime()==null || StringUtils.isBlank(course.getType()) || StringUtils.isBlank(course.getVersion()) || StringUtils.isBlank(teacherName)){
            return Result.build(400,"数据为空！！");
        }
        Integer integer = courseService.insertCourse(course,teacherName);
        if(integer>0){
            return Result.ok();
        }else if(integer == 0){
            return Result.build(500,"该教师已经存在！！！");
        }else {
            return Result.build(500,"系统错误！！！");
        }
    }

    //修改课程
    @PostMapping("/updateCourse")
    public Result updateCourse(Course course,String teacherName){
        if(StringUtils.isBlank(course.getCourseNum()) || course.getCredit()==null || course.getExperTime()==null || StringUtils.isBlank(course.getName())
                ||StringUtils.isBlank(course.getProperty()) || course.getTheoryTime()==null || StringUtils.isBlank(course.getType()) || StringUtils.isBlank(course.getVersion()) || StringUtils.isBlank(teacherName)){
            return Result.build(400,"数据为空！！");
        }
        Integer i = courseService.updateCourse(course,teacherName);
        if(i>0){
            return Result.ok();
        }else if(i == 0){
            return Result.build(500,"该教师已经存在！！！");
        }else {
            return Result.build(500,"系统错误！！！");
        }
    }

    //删除课程
    @PostMapping("/deleteCourse")
    public Result deleteCourse(Integer id){
        Integer integer = courseService.deleleCourse(id);
        if(integer>0){
            return Result.build(200,"删除成功！！！");
        }else {
            return Result.build(500,"系统错误！！！");
        }
    }

    //查询所有课程  按大纲年份查询  分页
    @PostMapping("/listCourseByVersion")
    public Result listCourseByVersion(String version,@RequestParam(required=true,defaultValue="1") Integer pageNum){
        System.out.println("vesion="+version);
        /*if(StringUtils.isBlank(version)){
            return Result.build(400,"请输入版本号！！");
        }*/
        if("".equals(version)){
            version = null;
        }
        PageHelper.startPage(pageNum,3);
        List<Course> courseList = courseService.listCourseByOneYear(version);
        PageInfo<Course> teacherPageInfo = new PageInfo<>(courseList);
        if(courseList.size()>0){
            return Result.build(200,"有结果！！！",teacherPageInfo);
        }else {
            return Result.build(400,"查本版本目前无课程！！！");
        }
    }

    //查询所有课程  按大纲年份查询  不分页
    @GetMapping("/listCourse")
    public Result listCourse(){
        List<Course> courseList = courseService.listCourseByOneYear(null);
        if(courseList.size()>0){
            return Result.build(200,"有结果！！！",courseList);
        }else {
            return Result.build(400,"查本版本目前无课程！！！");
        }
    }

    //根据课程名去查询
    @PostMapping("/findCourse")
    public Result findCourse(String name){
        if(StringUtils.isBlank(name)){
            return Result.build(400,"数据为空！！");
        }
        List<Course> courseList = courseService.findCourse(name);
        if(courseList.size()>0){
            return Result.build(200,"有数据！！",courseList);
        }else {
            return Result.build(400,"没有数据！！！");
        }
    }

}
