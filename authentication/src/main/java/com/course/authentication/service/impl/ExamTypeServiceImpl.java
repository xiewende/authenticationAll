package com.course.authentication.service.impl;

import com.course.authentication.mapper.ExamtypeMapper;
import com.course.authentication.pojo.Examtype;
import com.course.authentication.pojo.ExamtypeExample;
import com.course.authentication.service.ExamTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @create 2019-12-28  13:38
 */
@Service
public class ExamTypeServiceImpl implements ExamTypeService {

    @Autowired
    private ExamtypeMapper examtypeMapper;

    @Override
    public List<Examtype> listAllExamType() {
        ExamtypeExample examtypeExample = new ExamtypeExample();
        return examtypeMapper.selectByExample(examtypeExample);
    }
}
