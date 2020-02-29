package com.course.authentication.controller;

import com.course.authentication.pojo.Course;
import com.course.authentication.service.ExamService;
import com.course.authentication.util.Result;
import net.sf.json.JSONArray;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @create 2019-12-28  13:26
 */
@CrossOrigin
@RestController
public class ExamController {

    @Autowired
    private ExamService examService;

    //添加某一门课的对应考核方式
    @PostMapping("/addExam")
    public Result addExam(Integer courseId,String version, String examTypeIdList , String ratioList){

        List<Double> list = new ArrayList<>();  //ratio 去空
        List<Integer> listExamTypeId = new ArrayList<>(); // examTypeId去重

        //将json字符串的id 和 权重 转化成数组
        JSONArray jsonArray = JSONArray.fromObject(examTypeIdList);
        List<Integer> listExamTypeID = (List<Integer> )JSONArray.toCollection(jsonArray, Integer.class);

        JSONArray jsonArray1 = JSONArray.fromObject(ratioList);
        List<String> listRatio = (List<String> )JSONArray.toCollection(jsonArray1, Double.class);

        //courseIdList去重
        for(Integer integer : listExamTypeID){
            if(!listExamTypeId.contains(integer)){
                listExamTypeId.add(integer);
            }
        }

        //ratio 去空
        for(int i=listRatio.size()-1 ; i>=0 ; i--){
            if(!StringUtils.isBlank(listRatio.get(i))){
                list.add(Double.parseDouble(listRatio.get(i)));
            }else {
                listExamTypeId.remove(i);
            }
        }
      //倒序一下
        Collections.reverse(list);

        for(int i=0 ;i<listExamTypeId.size();i++){
            System.out.println("考核方式和权重的对应关系");
            System.out.println(listExamTypeId.get(i)+"="+list.get(i));
        }

        //判断权重和是否为1
        Double sum = 0.0;
        for(Double ratio : list){
            sum = sum+ratio;
        }
        if(sum!=1){
            return Result.build(400,"权值和不为 1 ，请重新输入");
        }

        //符合条件进行插入
        Integer integer = examService.insertExam(courseId,version,listExamTypeId,list);
        if(integer>0){
            return Result.build(200,"添加成功！！！");
        }else if(integer==0){
            return Result.build(400,"已经添加，切勿重新添加！！！");
        }else{
            return Result.build(500,"系统出现错误，请稍后重试！！！");
        }

    }

    //根据课程id和版本查询 对应考核放肆
    @PostMapping("/listAllExamByCourseIdAndVersion")
    public Result listAllExamByCourseIdAndVersion(Integer courseId, String version){
        if(courseId==null || StringUtils.isBlank(version)){
            return Result.build(400,"输入数据为空！！！重新摄入！！");
        }
        Course course = examService.listAllExamByCourseIdAndVersion(courseId, version);
        if(course!=null){
            return Result.build(200,"有数据！！",course);
        }else {
            return Result.build(500,"无数据！！");
        }
    }
}
