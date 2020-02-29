package com.course.authentication.service;

import com.course.authentication.pojo.Course;
import com.course.authentication.pojo.Indication;

import java.util.List;

/**
 * @create 2019-12-28  13:00
 */
public interface ExamService {

    //添加对应关系 课程和考核放肆
    public Integer insertExam(Integer courseId , String version,List<Integer> examtypeIdList , List<Double> ratioList);

    //根据课程名来查所有的 对应关系
    public Course listAllExamByCourseIdAndVersion(Integer courseId,String version);
}
