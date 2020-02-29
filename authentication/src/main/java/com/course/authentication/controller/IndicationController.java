package com.course.authentication.controller;

import com.course.authentication.pojo.Indication;
import com.course.authentication.service.IndicationService;
import com.course.authentication.util.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @create 2019-12-23  19:42  指标管理
 */
@CrossOrigin
@RestController
public class IndicationController {

    @Autowired
    private IndicationService indicationService;

    //添加指标
    @PostMapping("/addIndication")
    public Result addIndication(Indication indication){
        if(StringUtils.isBlank(indication.getContent()) || StringUtils.isBlank(indication.getIndicationNum()) || StringUtils.isBlank(indication.getVersion())){
            return Result.build(400,"数据为空！！");
        }
        Integer integer = indicationService.insertIndication(indication);
        if(integer>0){
            return Result.build(200,"添加成功！！！");
        }else {
            return Result.build(500,"系统错误！！");
        }
    }

    //修改指标
    @PostMapping("updateIndication")
    public Result updateIndication(Indication indication){
        if(StringUtils.isBlank(indication.getContent()) || StringUtils.isBlank(indication.getIndicationNum()) || StringUtils.isBlank(indication.getVersion())){
            return Result.build(400,"数据为空！！");
        }
        Integer integer = indicationService.updateIndication(indication);
        if(integer>0){
            return Result.ok();
        }else {
            return Result.build(500,"系统错误！！");
        }
    }

    //查询所有的指标
    @PostMapping("/listIndication")
    public Result listIndication(String version){
        if("".equals(version)){
            version = null;
        }
        List<Indication> indicationList = indicationService.listIndication(version);
        if(indicationList.size()>0){
            return Result.build(200,"有数据",indicationList);
        }else {
            return Result.build(400,"无数据");
        }
    }

    //删除某一个指标
    @PostMapping("/deletaIndication")
    public Result deletaIndication(Integer id){
        Integer i = indicationService.deleteIndication(id);
        if(i>0){
            return Result.ok();
        }else {
            return Result.build(500,"系统错误！！");
        }
    }



}
