package com.course.authentication.service;

import com.course.authentication.pojo.Course;

import java.util.List;

/**
 * @create 2019-12-22  19:24
 */
public interface CourseService {

    //添加课程
    public Integer insertCourse(Course course,String teacherName);

    //修改课程
    public Integer updateCourse(Course course,String teacherName);

    //看看某一年的课程
    public  List<Course> listCourseByOneYear(String version);

    //删除课程
    public Integer deleleCourse(Integer id);

    //模糊查询课程
    public List<Course> findCourse(String name);


}
