package com.course.authentication.controller;

import com.course.authentication.pojo.Examtype;
import com.course.authentication.service.ExamTypeService;
import com.course.authentication.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @create 2019-12-28  13:41
 */
@CrossOrigin
@RestController
public class ExamTypeController {

    @Autowired
    private ExamTypeService examTypeService;

    //查询所有的考核方式
    @GetMapping("/listAllExamType")
    public Result listAllExamType(){
        List<Examtype> examtypeList = examTypeService.listAllExamType();
        if(examtypeList.size()>0){
            return Result.build(200,"有数据！！",examtypeList);
        }else {
            return Result.build(400,"无数据！！！");
        }
    }
}
