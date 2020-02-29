package com.course.authentication.controller;

import com.course.authentication.pojo.Target;
import com.course.authentication.service.TargetService;
import com.course.authentication.util.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @create 2019-12-27  13:00  目标管理
 */
@CrossOrigin
@RestController
public class TargetController {

    @Autowired
    private TargetService targetService;

    //添加目标
    @PostMapping("/addTarget")
    public Result addTarget(Target target){
        if(StringUtils.isBlank(target.getContent()) || StringUtils.isBlank(target.getTargerNum()) || StringUtils.isBlank(target.getVersion()) || target.getCourseId()==null){
            return Result.build(400,"数据不能为空！！！");
        }
        System.out.println(target.getContent()+"=contene");
        Integer integer = targetService.insertTarget(target);
        if(integer>0){
            return Result.build(200,"添加成功！！！");
        }else {
            return Result.build(500,"系统错误，骚后重试！！1");
        }
    }

    //修改目标
    @PostMapping("/updateTarget")
    public Result updateTarget(Target target){

        if(StringUtils.isBlank(target.getContent()) == false || StringUtils.isBlank(target.getTargerNum()) == false || StringUtils.isBlank(target.getVersion()) == false || target.getCourseId() == null){
            return Result.build(400,"数据不能为空！！！");
        }

        Integer integer = targetService.updateTarget(target);
        if(integer>0){
            return Result.build(200,"修改成功！！！");
        }else {
            return Result.build(500,"系统错误，骚后重试！！1");
        }
    }

    //删除目标
    @PostMapping("/deleteTarget")
    public Result deleteTageget(Integer id){
        Integer integer = targetService.delleteTarget(id);
        if(integer>0){
            return Result.build(200,"删除成功！！！");
        }else {
            return Result.build(500,"系统错误，骚后重试！！1");
        }
    }

    //查询所有目标，根据版本和课程名
    @PostMapping("/listAllTarget")
    public Result listAllTarget(String courseName,String version){
        if("".equals(courseName)){
            courseName = null;
        }
        if("".equals(version)){
            version = null;
        }

        List<Target> targets = targetService.listAllTarget(courseName, version);
        if(targets == null || targets.size()>0){
            return Result.build(200,"有数据！！！",targets);
        }else {
            return Result.build(400,"无数据！！！");
        }
    }

    //无条件查询所有目标 考核细则那里使用
    @GetMapping("/listAllTargetByNo")
    public Result listAllTargetByNo(){
        List<Target> targetList = targetService.listAllTargetByNo();
        if(targetList == null || targetList.size()>0){
            return Result.build(200,"有数据！！！",targetList);
        }else {
            return Result.build(400,"无数据！！！");
        }
    }

}
