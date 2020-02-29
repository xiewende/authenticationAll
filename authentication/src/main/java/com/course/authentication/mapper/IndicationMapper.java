package com.course.authentication.mapper;

import com.course.authentication.pojo.Indication;
import com.course.authentication.pojo.IndicationExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface IndicationMapper {
    int countByExample(IndicationExample example);

    int deleteByExample(IndicationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Indication record);

    int insertSelective(Indication record);

    List<Indication> selectByExample(IndicationExample example);

    Indication selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Indication record, @Param("example") IndicationExample example);

    int updateByExample(@Param("record") Indication record, @Param("example") IndicationExample example);

    int updateByPrimaryKeySelective(Indication record);

    int updateByPrimaryKey(Indication record);
}