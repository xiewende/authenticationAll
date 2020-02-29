package com.course.authentication.controller;

import com.course.authentication.pojo.Indication;
import com.course.authentication.service.IndicationCourseService;
import com.course.authentication.util.Result;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @create 2019-12-27  19:48
 */
@CrossOrigin
@RestController
public class IndicationCourseController {

    @Autowired
    private IndicationCourseService indicationCourseService;

    //添加某歌指标  全部的课程对应
    @PostMapping("/addIndicationCourse")
    public Result addIndicationCourse(Integer indicationId, String courseIdList , String weightList){

        List<Double> list = new ArrayList<>();  //weight 去空
        List<Integer> listCourseId = new ArrayList<>(); //courseIdList去重

        //将json字符串的id 和 权重 转化成数组
        JSONArray jsonArray = JSONArray.fromObject(courseIdList);
        List<Integer> listCourseID = (List<Integer> )JSONArray.toCollection(jsonArray, Integer.class);

        JSONArray jsonArray1 = JSONArray.fromObject(weightList);
        List<String> listWeight = (List<String> )JSONArray.toCollection(jsonArray1, Double.class);

        //weight 去空
        for(String str : listWeight){
            if(str!=null){
                list.add(Double.parseDouble(str));
            }
        }

        //courseIdList去重
        for(Integer integer : listCourseID){
            if(!listCourseId.contains(integer)){
                listCourseId.add(integer);
            }
        }

        for(int i=0 ;i<listCourseID.size();i++){
            System.out.println("指标和权重的对应关系");
            System.out.println(listCourseID.get(i)+"="+list.get(i));
        }

        //判断权重和是否为1
        Double sum = 0.0;
        for(Double weight : list){
            sum = sum+weight;
        }
        if(sum!=1){
            return Result.build(400,"权值和不为 1 ，请重新输入");
        }

        //符合条件进行插入
        Integer integer = indicationCourseService.insertIndicationCourse(indicationId, listCourseId, list);
        if(integer>0){
            return Result.build(200,"添加成功！！！");
        }else if(integer==0){
            return Result.build(400,"已经添加，切勿重新添加！！！");
        }else{
            return Result.build(500,"系统出现错误，请稍后重试！！！");
        }

    }

    //根据某一指标查询所有的课程权重对应
    @PostMapping("/listAllIndicationCourseByIndicationId")
    public Result listAllIndicationCourseByIndicationId(Integer indicationId){
        List<Indication> indications = indicationCourseService.listAllIndicationCourseByIndicationId(indicationId);
        if(indications.size()>0){
            return Result.build(200,"有数据！！",indications.get(0));
        }else {
            return Result.build(400,"无数据！！！");
        }
    }

    //TODO

}
