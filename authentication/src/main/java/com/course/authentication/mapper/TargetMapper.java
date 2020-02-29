package com.course.authentication.mapper;

import com.course.authentication.pojo.Target;
import com.course.authentication.pojo.TargetExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface TargetMapper {
    int countByExample(TargetExample example);

    int deleteByExample(TargetExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Target record);

    int insertSelective(Target record);

    List<Target> selectByExample(TargetExample example);

    Target selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Target record, @Param("example") TargetExample example);

    int updateByExample(@Param("record") Target record, @Param("example") TargetExample example);

    int updateByPrimaryKeySelective(Target record);

    int updateByPrimaryKey(Target record);
}