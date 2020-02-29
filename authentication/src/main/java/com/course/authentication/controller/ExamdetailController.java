package com.course.authentication.controller;

import com.course.authentication.pojo.Examdetail;
import com.course.authentication.service.ExamdetailService;
import com.course.authentication.util.Result;
import net.sf.json.JSONArray;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @create 2019-12-28  16:57
 */
@CrossOrigin
@RestController
public class ExamdetailController {

    @Autowired
    private ExamdetailService examdetailService;

    @PostMapping("/addExamdetail")
    public Result addExamdetail(Examdetail examdetail,String nameList,String vualeList){

        //namejihe
        List<String> nameSet = new ArrayList<>();
        List<Double> vualeSet = new ArrayList<>();

        //将json字符串的id 和 权重 转化成数组
        JSONArray jsonArray = JSONArray.fromObject(nameList);
        List<String> names = (List<String> )JSONArray.toCollection(jsonArray, Integer.class);

        JSONArray jsonArray1 = JSONArray.fromObject(vualeList);
        List<String> vuales = (List<String> )JSONArray.toCollection(jsonArray1, Double.class);

        //去空或者去空
        for(int i=0 ; i<names.size() ;i++){
            if(!StringUtils.isBlank(names.get(i))){
                nameSet.add(names.get(i));
            }
            if(!StringUtils.isBlank(vuales.get(i))){
                vualeSet.add(Double.parseDouble(vuales.get(i)));
            }
        }

        //测试数据
        for(int i=0 ;i<nameSet.size();i++){
            System.out.println("考核方式细则和权重或者分数的对应关系");
            System.out.println(nameSet.get(i)+"="+vualeSet.get(i));
        }

        //判断是否为1或者100
        double sum = 0.0;
        for(Double vuale : vualeSet){
            sum = sum+vuale;
        }
        System.out.println("sum="+sum);
        if(sum != 1.0 && sum!=100.0){
            return Result.build(400,"输入得分值或者权重和不符合规则！！请重新输入！！");
        }

        Integer integer = examdetailService.insertExamdetail(examdetail, nameSet, vualeSet);
        if(integer>0){
            return Result.build(200,"添加成功！！！");
        }else if(integer==0){
            return Result.build(400,"已经添加，切勿重新添加！！！");
        }else{
            return Result.build(500,"系统出现错误，请稍后重试！！！");
        }


    }


    //查询所有的考核细则  根据考核方式  目标 二选一  版本必填
    @PostMapping("/listAllExamDetailByExamTypeOrTarget")
    public Result listAllExamDetailByExamTypeOrTarget(Integer targetId, Integer examtypeId, String version){
        if(StringUtils.isBlank(version)){
            return Result.build(400,"输入的版本选择条件不能为空！！！请选择！！");
        }
        if("".equals(targetId) && "".equals(examtypeId)){
            return Result.build(400,"输入的选择条件不能为空！！！请选择！！");
        }
        if(targetId == null && examtypeId == null){
            return Result.build(400,"输入的选择条件不能为空！！！请选择！！");
        }
        if("".equals(targetId)){
            targetId = null;
        }
        if("".equals(examtypeId)){
            examtypeId=null;
        }

        List<Examdetail> examdetailList = examdetailService.listAllExamDetailByExamTypeOrTarget(targetId, examtypeId, version);

        if(examdetailList.size()>0){
            return Result.build(200,"有数据！！！",examdetailList);
        }else {
            return Result.build(400,"没有数据！！！");
        }


    }
}
