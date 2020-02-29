package com.course.authentication.mapper;

import com.course.authentication.pojo.Indication;
import com.course.authentication.pojo.IndicationCourse;
import com.course.authentication.pojo.IndicationCourseExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface IndicationCourseMapper {

    //查询某一指标下的所有课程权重对应
    List<Indication> listAllIndicationCourseByIndicationId(Integer indicationId);

    int countByExample(IndicationCourseExample example);

    int deleteByExample(IndicationCourseExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(IndicationCourse record);

    int insertSelective(IndicationCourse record);

    List<IndicationCourse> selectByExample(IndicationCourseExample example);

    IndicationCourse selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") IndicationCourse record, @Param("example") IndicationCourseExample example);

    int updateByExample(@Param("record") IndicationCourse record, @Param("example") IndicationCourseExample example);

    int updateByPrimaryKeySelective(IndicationCourse record);

    int updateByPrimaryKey(IndicationCourse record);
}