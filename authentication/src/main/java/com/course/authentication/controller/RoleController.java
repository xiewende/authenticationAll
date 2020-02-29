package com.course.authentication.controller;

import com.course.authentication.pojo.Role;
import com.course.authentication.service.Roleservice;
import com.course.authentication.util.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @create 2019-12-19  21:08  角色管理
 */
@CrossOrigin
@RestController
public class RoleController {

    @Autowired
    private Roleservice roleservice;

    //添加角色
    @PostMapping("/inserRole")
    public Result inserRole(Role role){
        if(StringUtils.isBlank(role.getName()) || StringUtils.isBlank(role.getDescription())){
            return Result.build(400,"数据为空！！");
        }

        Integer i = roleservice.inserRole(role);
        if(i>0){
            return Result.ok();
        }else {
            return Result.build(500,"系统错误！！");
        }
    }

    //查询所有角色
    @GetMapping("/listRole")
    public Result listRole(){
        List<Role> listRole = roleservice.listRole();
        if(listRole != null){
             return Result.build(200,"有数据",listRole);
        }else {
            return Result.build(500,"系统错误！！");
        }
    }

    //删除角色
    @PostMapping("/deleteRole")
    public Result delete(Integer id){
        Integer i = roleservice.deleteRole(id);
        if(i>0){
            return Result.ok();
        }else {
            return Result.build(500,"系统错误！！");
        }
    }

    //修改角色
    @PostMapping("/updateRole")
    public Result updateRole(Role role){
        Integer in = roleservice.updateRole(role);
        if(in>0){
            return Result.ok();
        }else {
            return Result.build(500,"系统错误！！");
        }
    }

}
