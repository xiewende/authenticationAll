package com.course.authentication.service;

import com.course.authentication.pojo.Examtype;

import java.util.List;

/**
 * @create 2019-12-28  13:37
 */
public interface ExamTypeService {

    //查询所有的考核方式
    List<Examtype> listAllExamType();
}
