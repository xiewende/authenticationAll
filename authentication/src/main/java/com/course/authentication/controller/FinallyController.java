package com.course.authentication.controller;

import com.course.authentication.pojo.GraduationDachengdu;
import com.course.authentication.pojo.PersonalDachengdu;
import com.course.authentication.pojo.Totaldachengdu;
import com.course.authentication.service.FinallyService;
import com.course.authentication.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @create 2019-12-29  17:22
 */
@CrossOrigin
@RestController
public class FinallyController {

    @Autowired
    private FinallyService finallyService;

    //查询总体大程度
    @PostMapping("/checkTotaldachengdu")
    public Result checkTotaldachengdu(String courseName, String version){
        System.out.println("courserName="+courseName);
        System.out.println("version="+version);
        List<Totaldachengdu> totaldachengduList = finallyService.checkTotaldachengdu(courseName, version);
        if(totaldachengduList.size()>0){
            return Result.build(200,"查到了！！！",totaldachengduList);
        }else {
            return Result.build(500,"没有查到，骚后重试！！！");
        }
    }

    //查询个人达成度
    @PostMapping("/chechPersonalDachengdu")
    public Result chechPersonalDachengdu(String courseName, String version){
        System.out.println("courserName="+courseName);
        System.out.println("version="+version);
        List<PersonalDachengdu> personalDachengdus = finallyService.chechPersonalDachengdu(courseName, version);
        if(personalDachengdus.size()>0){
            return Result.build(200,"查到了！！！",personalDachengdus);
        }else {
            return Result.build(500,"没有查到，骚后重试！！！");
        }
    }

    //查询毕业要求大程度
    @PostMapping("/checkGraduationDachengdu")
    public Result checkGraduationDachengdu(String version)
    {
        List<GraduationDachengdu> dachengdus = finallyService.checkGraduationDachengdu(version);
        if(dachengdus.size()>0){
            return Result.build(200,"查到了！！！",dachengdus);
        }else {
            return Result.build(500,"没有查到，骚后重试！！！");
        }
    }




}
