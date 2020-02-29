package com.course.authentication.service;

import com.course.authentication.pojo.Examdetail;

import java.util.List;

/**
 * @create 2019-12-28  16:27
 */
public interface ExamdetailService {

    //添加
    public Integer insertExamdetail(Examdetail examdetail,List<String> nameList,List<Double> vualeList);

    //查询所有的考核细则  根据考核方式  目标 二选一  版本必填
    public List<Examdetail> listAllExamDetailByExamTypeOrTarget(Integer targetId,Integer examtypeId,String version);
}
