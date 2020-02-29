package com.course.authentication.controller;

import com.course.authentication.pojo.Teacher;
import com.course.authentication.service.TeacherService;
import com.course.authentication.util.Result;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @create 2019-12-20  19:07  教师管理
 */
@CrossOrigin
@RestController
public class TeacherController {
    @Autowired
    private TeacherService  teacherService;

    //添加教师
    @PostMapping("/addTeacher")
    public Result addTeacher(Teacher teacher, String rolename){

        if(rolename == null || teacher.getName() == null  || teacher.getTeacherNum() == null){
            System.out.println("null");
            return Result.build(400,"输入数据不能够为空！！！！");
        }
        if(rolename.equals("") || teacher.getName().equals("") ||  teacher.getTeacherNum().equals("")){
            System.out.println("  lai");
            return Result.build(400,"输入数据不能够为空！！！！");
        }
        Integer integer = teacherService.insertTeacher(teacher, rolename);
        if(integer>0){
            return Result.ok();
        }else if (integer == 0){
            return Result.build(500,"输入的教师已经存在本系统！！");
        }else {
            return Result.build(500,"系统错误！！");
        }
    }

    //修改教师
    @PostMapping("/updateTeacher")
    public Result updateTeacher(Teacher teacher, String rolename){
        if(StringUtils.isBlank(rolename)|| StringUtils.isBlank(teacher.getName()) || StringUtils.isBlank(teacher.getTeacherNum())){
            return Result.build(400,"输入数据不能够为空！！！！");
        }
        Integer integer = teacherService.updateTeacher(teacher, rolename);
        if(integer>0){
            return Result.ok();
        }else {
            return Result.build(500,"系统错误！！");
        }
    }
    //分页查询所有教师 pageNum
    @PostMapping("/listTeacher")
    public Result listTeacher(@RequestParam(required=true,defaultValue="1") Integer pageNum){
        PageHelper.startPage(pageNum,3);
        List<Teacher> teacherList = teacherService.listTeacher();
        PageInfo<Teacher> teacherPageInfo = new PageInfo<>(teacherList);
        if(teacherList != null){
            return Result.build(200,"有数据",teacherPageInfo);
        }else {
            return Result.build(500,"无数据！！");
        }
    }

    //查询所有的老师集合  供后续使用  添加课程时选择课程编撰者
    @GetMapping("/listAllTeacher")
    public Result listAllTeacher(){
        List<Teacher> teacherList = teacherService.listTeacher();
        if(teacherList != null){
            return Result.build(200,"有数据",teacherList);
        }else {
            return Result.build(500,"无数据！！");
        }
    }



    @PostMapping("/deleteTeacher")
    public Result deleteTeacher(Integer id){
        System.out.println("id="+id);
        Integer integer = teacherService.deleteTeacher(id);
        if(integer>0){
            return Result.ok();
        }else {
            return Result.build(500,"系统错误！！");
        }
    }


    //模糊查询某个教师
    @PostMapping("/findTeacher")
    public Result findTeacher(String keyword){
        if(StringUtils.isBlank(keyword)){
           return Result.build(400,"数据为空");
        }
        List<Teacher> teacherlist = teacherService.findTeacherByLike(keyword);
        if(teacherlist.size()>0){
            return Result.build(200,"找到了！！",teacherlist);
        }else {
            return Result.build(500,"没有此用户！！");
        }
    }







}
