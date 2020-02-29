package com.course.authentication.mapper;

import com.course.authentication.pojo.TargetIndication;
import com.course.authentication.pojo.TargetIndicationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TargetIndicationMapper {
    int countByExample(TargetIndicationExample example);

    int deleteByExample(TargetIndicationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TargetIndication record);

    int insertSelective(TargetIndication record);

    List<TargetIndication> selectByExample(TargetIndicationExample example);

    TargetIndication selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TargetIndication record, @Param("example") TargetIndicationExample example);

    int updateByExample(@Param("record") TargetIndication record, @Param("example") TargetIndicationExample example);

    int updateByPrimaryKeySelective(TargetIndication record);

    int updateByPrimaryKey(TargetIndication record);
}