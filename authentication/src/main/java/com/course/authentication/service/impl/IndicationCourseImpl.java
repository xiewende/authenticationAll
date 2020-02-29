package com.course.authentication.service.impl;

import com.course.authentication.mapper.IndicationCourseMapper;
import com.course.authentication.pojo.Indication;
import com.course.authentication.pojo.IndicationCourse;
import com.course.authentication.pojo.IndicationCourseExample;
import com.course.authentication.service.IndicationCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @create 2019-12-27  19:36
 */
@Service
public class IndicationCourseImpl implements IndicationCourseService {

    @Autowired
    private IndicationCourseMapper indicationCourseMapper;

    @Override
    public Integer insertIndicationCourse(Integer indicationId, List<Integer> courseIdList, List<Double> weightList) {

        //是否在数据库中
        IndicationCourseExample example = new IndicationCourseExample();
        IndicationCourseExample.Criteria criteria = example.createCriteria();
        criteria.andIndicationIdEqualTo(indicationId);
        List<IndicationCourse> indicationCourses = indicationCourseMapper.selectByExample(example);
        if(indicationCourses.size()>0 ){
            return 0;
        }

        //判断标志
        Integer tag = 0;

        //插入得对象
        IndicationCourse indicationCourse = new IndicationCourse();
        //设置indicationId
        indicationCourse.setIndicationId(indicationId);

        //遍历courseIdList weightList 拿对应的数据进行插入
        for(int i =0 ;i<courseIdList.size() ; i++){
            Integer courseId = courseIdList.get(i);
            Double weight = weightList.get(i);

            //

            //封装到对象进行插入
            indicationCourse.setCourseId(courseId);
            indicationCourse.setWeight(weight);

            //执行插入
            indicationCourseMapper.insert(indicationCourse);
            tag++;
        }
        return tag;
    }

    @Override
    public List<Indication> listAllIndicationCourseByIndicationId(Integer indicationId) {
        return indicationCourseMapper.listAllIndicationCourseByIndicationId(indicationId);
    }


}
