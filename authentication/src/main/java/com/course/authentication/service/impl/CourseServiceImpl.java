package com.course.authentication.service.impl;

import com.course.authentication.mapper.CourseMapper;
import com.course.authentication.mapper.TeacherMapper;
import com.course.authentication.pojo.Course;
import com.course.authentication.pojo.CourseExample;
import com.course.authentication.pojo.Teacher;
import com.course.authentication.pojo.TeacherExample;
import com.course.authentication.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @create 2019-12-22  19:43
 */
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public Integer insertCourse(Course course ,String teacherName) {

        //判断是否重新录入
        CourseExample courseExample = new CourseExample();
        CourseExample.Criteria criteria = courseExample.createCriteria();
        criteria.andCourseNumEqualTo(course.getCourseNum());
        criteria.andVersionEqualTo(course.getVersion());
        List<Course> courseList = courseMapper.selectByExample(courseExample);
        if(courseList.size()>0){
            return 0;
        }

        //根据教师名查询教师id
        TeacherExample example = new TeacherExample();
        TeacherExample.Criteria criteria1 = example.createCriteria();
        criteria1.andNameEqualTo(teacherName);
        List<Teacher> teacherList = teacherMapper.selectByExample(example);
        Teacher teacher = teacherList.get(0);


        //老师id塞进去，学时改一下
        course.setExperTime(course.getExperTime()*60);
        course.setTheoryTime(course.getTheoryTime()*60);
        course.setTeacherId(teacher.getId());
        return courseMapper.insert(course);
    }

    @Override
    public Integer updateCourse(Course course,String teacherName) {
        //判断是否重新录入
        CourseExample courseExample = new CourseExample();
        CourseExample.Criteria criteria = courseExample.createCriteria();
        criteria.andCourseNumEqualTo(course.getCourseNum());
        criteria.andVersionEqualTo(course.getVersion());
        List<Course> courseList = courseMapper.selectByExample(courseExample);
        if(courseList.size()>0){
            return 0;
        }

        //查找教师id
        TeacherExample teacherExample = new TeacherExample();
        TeacherExample.Criteria teacherExampleCriteria = teacherExample.createCriteria();
        teacherExampleCriteria.andNameEqualTo(teacherName);
        List<Teacher> teacherList = teacherMapper.selectByExample(teacherExample);
        Teacher teacher = teacherList.get(0);


        //老师id塞进去，学时改一下
        course.setExperTime(course.getExperTime()*60);
        course.setTheoryTime(course.getTheoryTime()*60);
        course.setTeacherId(teacher.getId());

        return courseMapper.updateByPrimaryKey(course);
    }

    @Override
    public List<Course> listCourseByOneYear(String version) {
        List<Course> courseList ;
        if(version == null){
            courseList = courseMapper.listCourseNoByVersion();
        }else {
            courseList = courseMapper.listCourseByVersion(version);
        }

        //修改理论学时和数据库的转化关系
        for(Course course : courseList){
            course.setExperTime(course.getExperTime()/60);
            course.setTheoryTime(course.getTheoryTime()/60);
        }
        return courseList;
    }

    @Override
    public Integer deleleCourse(Integer id) {

        return courseMapper.deleteByPrimaryKey(id);
    }

    //根据课程名模糊查询课程
    @Override
    public List<Course> findCourse(String name) {
        CourseExample courseExample = new CourseExample();
        CourseExample.Criteria criteria = courseExample.createCriteria();
        criteria.andNameLike("%"+name+"%");
        List<Course> courseList = courseMapper.selectByExample(courseExample);

        for(Course course : courseList){
            //查找对应的课程编撰者
            Teacher teacher = teacherMapper.selectByPrimaryKey(course.getTeacherId());
            course.setTeacher(teacher);

            //理论学时和时间学时的转化
            course.setTheoryTime(course.getTheoryTime()/60);
            course.setExperTime(course.getExperTime()/60);
        }
        return courseList;
    }
}
