package com.course.authentication.service;

import com.course.authentication.pojo.Indication;

import java.util.List;

/**
 * @create 2019-12-27  18:42
 */
public interface IndicationCourseService {

    //添加对应关系
    public Integer insertIndicationCourse(Integer indicationId,List<Integer> courseIdList ,List<Double> weightList);

    //根据指标来查所有的 对应关系
    public List<Indication> listAllIndicationCourseByIndicationId(Integer indicationId );


}
