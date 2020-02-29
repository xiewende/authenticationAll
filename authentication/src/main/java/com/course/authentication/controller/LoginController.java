package com.course.authentication.controller;

import com.course.authentication.pojo.Teacher;
import com.course.authentication.service.LoginService;
import com.course.authentication.util.Result;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

/**
 * @create 2019-12-28  19:16
 */
@CrossOrigin
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @PostMapping("/login")
    public Result login(String teacherNum, String password){
        if(StringUtils.isBlank(teacherNum) || StringUtils.isBlank(password)){
            return Result.build(400,"账号密码不能为空！！！");
        }
        Teacher teacher = loginService.Login(teacherNum, password);
        if(teacher == null){
            return Result.build(400,"用户账户或者密码错误！！！");
        }

        //登陆成功，将对象存入redis  同时设置过期时间 30 minutes
        //
        //JSONObject jsonObject = JSONObject.fromObject(teacher);
        redisTemplate.opsForValue().set("LoginUser","LoginUser" ,30*60, TimeUnit.SECONDS);

        return Result.build(200,"登陆成功！！！",teacher);

    }

    @GetMapping("/loginout")
    public Result loginout(){
        Boolean loginUser = redisTemplate.delete("LoginUser");
        if(loginUser){
            return Result.build(200,"退出成功！！！");
        }else {
            return Result.build(500,"系统出现故障");
        }
    }

}
