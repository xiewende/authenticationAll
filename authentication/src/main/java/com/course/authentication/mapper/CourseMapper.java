package com.course.authentication.mapper;

import com.course.authentication.pojo.Course;
import com.course.authentication.pojo.CourseExample;
import java.util.List;

import com.course.authentication.pojo.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CourseMapper {
    //excel 拿选了这门课的学生信息
    List<Student> listStudentByCourse(String courseName,String version);
    //拿到课程下的所有考核细则
    List<String> listExamdetailByCourse(String courseName,String version);


    //查询所有课程，按照大纲年份查询 返回的值包括 课程编撰者信息
    List<Course> listCourseByVersion(@Param(value = "version") String version);

    //查询所有课程， 返回的值包括 课程编撰者信息
    List<Course> listCourseNoByVersion();

    int countByExample(CourseExample example);

    int deleteByExample(CourseExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Course record);

    int insertSelective(Course record);

    List<Course> selectByExample(CourseExample example);

    Course selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Course record, @Param("example") CourseExample example);

    int updateByExample(@Param("record") Course record, @Param("example") CourseExample example);

    int updateByPrimaryKeySelective(Course record);

    int updateByPrimaryKey(Course record);
}