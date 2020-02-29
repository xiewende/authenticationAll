package com.course.authentication.service.impl;

import com.course.authentication.mapper.ExamMapper;
import com.course.authentication.pojo.Course;
import com.course.authentication.pojo.Exam;
import com.course.authentication.pojo.ExamExample;
import com.course.authentication.pojo.ExamtypeExample;
import com.course.authentication.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @create 2019-12-28  13:24
 */
@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamMapper examMapper;

    //添加课程考核方式
    @Override
    public Integer insertExam(Integer courseId, String version, List<Integer> examtypeIdList, List<Double> ratioList) {

        //判断在数据库中是否存在该记录
        ExamExample examExample = new ExamExample();
        ExamExample.Criteria criteria = examExample.createCriteria();
        criteria.andVersionEqualTo(version);
        criteria.andCourseIdEqualTo(courseId);
        List<Exam> exams = examMapper.selectByExample(examExample);
        if(exams.size()>0){
            return 0;
        }

        //判断添加标志
        Integer tag = 0;

        //获取插入的对象
        Exam exam = new Exam();
        //设置courseId和版本
        exam.setCourseId(courseId);
        exam.setVersion(version);

        //遍历examtypeIdList和ratioList 进行插入
        for(int i=0 ;i<examtypeIdList.size() ; i++){
            Integer examTypeid = examtypeIdList.get(i);
            Double ratio = ratioList.get(i);

            exam.setExamtypeId(examTypeid);
            exam.setRatio(ratio);

            //执行插入
            examMapper.insert(exam);
            tag++;
        }

        return tag;
    }

    //查询课程考核方式
    @Override
    public Course listAllExamByCourseIdAndVersion(Integer courseId, String version) {
        return examMapper.listAllExamByCourseIdAndVersion(courseId,version);
    }
}
