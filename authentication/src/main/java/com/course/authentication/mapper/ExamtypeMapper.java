package com.course.authentication.mapper;

import com.course.authentication.pojo.Examtype;
import com.course.authentication.pojo.ExamtypeExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ExamtypeMapper {
    int countByExample(ExamtypeExample example);

    int deleteByExample(ExamtypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Examtype record);

    int insertSelective(Examtype record);

    List<Examtype> selectByExample(ExamtypeExample example);

    Examtype selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Examtype record, @Param("example") ExamtypeExample example);

    int updateByExample(@Param("record") Examtype record, @Param("example") ExamtypeExample example);

    int updateByPrimaryKeySelective(Examtype record);

    int updateByPrimaryKey(Examtype record);
}