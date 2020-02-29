package com.course.authentication.mapper;

import com.course.authentication.pojo.Selcourse;
import com.course.authentication.pojo.SelcourseExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface SelcourseMapper {

    //查询selCourse courseId studentId version  可标配添加学生重复判断
    Selcourse chechSelcourse(Integer courseId,Integer studentId,String version);

    //查询某一课程的所有学生信息
    List<Selcourse> listAllSelcourseByCourse(Integer courseId);

    List<Selcourse> listAllSelcourseByCourseByTeacher(Integer courseId,Integer teacherId,String term,String version);

    int countByExample(SelcourseExample example);

    int deleteByExample(SelcourseExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Selcourse record);

    int insertSelective(Selcourse record);

    List<Selcourse> selectByExample(SelcourseExample example);

    Selcourse selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Selcourse record, @Param("example") SelcourseExample example);

    int updateByExample(@Param("record") Selcourse record, @Param("example") SelcourseExample example);

    int updateByPrimaryKeySelective(Selcourse record);

    int updateByPrimaryKey(Selcourse record);
}