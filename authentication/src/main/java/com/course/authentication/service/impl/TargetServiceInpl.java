package com.course.authentication.service.impl;


import com.course.authentication.mapper.CourseMapper;
import com.course.authentication.mapper.TargetMapper;

import com.course.authentication.pojo.Course;
import com.course.authentication.pojo.CourseExample;
import com.course.authentication.pojo.Target;

import com.course.authentication.pojo.TargetExample;
import com.course.authentication.service.TargetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @create 2019-12-27  12:46
 */
@Service
public class TargetServiceInpl implements TargetService {

    @Autowired
    private TargetMapper targetMapper;
    @Autowired
    private CourseMapper courseMapper;


    @Override
    public Integer insertTarget(Target target) {
        return targetMapper.insert(target);
    }

    @Override
    public Integer updateTarget(Target target) {
        return targetMapper.updateByPrimaryKey(target);
    }

    @Override
    public Integer delleteTarget(Integer id) {
        return targetMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Target> listAllTarget(String courseName, String version) {

        TargetExample targetExample = new TargetExample();
        TargetExample.Criteria criteria = targetExample.createCriteria();


        if(courseName !=null ){
            CourseExample example =new CourseExample();
            CourseExample.Criteria criteria1 = example.createCriteria();
            criteria1.andNameLike("%"+courseName+"%");
            List<Course> courselist = courseMapper.selectByExample(example);
            if(courselist.size()>0){
                //id集合
                List<Integer> integerList = new ArrayList<>();
                for(Course course : courselist){
                    integerList.add(course.getId());
                }
                criteria.andCourseIdIn(integerList);
            }else {
                return null;
            }

        }

        if(version != null){
            criteria.andVersionEqualTo(version);
        }

        List<Target> targets = targetMapper.selectByExample(targetExample);
        //找到课程名设置进去
        for(Target target : targets){
            Course course = courseMapper.selectByPrimaryKey(target.getCourseId());
            target.setCourse(course);
        }
        return targets;
    }

    @Override
    public List<Target> listAllTargetByNo() {
        TargetExample targetExample = new TargetExample();
        List<Target> targetList = targetMapper.selectByExample(targetExample);
        return targetList;
    }
}
