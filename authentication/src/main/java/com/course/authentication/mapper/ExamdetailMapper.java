package com.course.authentication.mapper;

import com.course.authentication.pojo.Examdetail;
import com.course.authentication.pojo.ExamdetailExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface ExamdetailMapper {


    List<Examdetail> listAllExamDetailByExamType(Integer examtypeId, String version);
    List<Examdetail> listAllExamDetailByTarget(Integer targetId, String version);
    List<Examdetail> listAllExamDetailByExamTypeAndTarget(Integer targetId, Integer examtypeId, String version);


    int countByExample(ExamdetailExample example);

    int deleteByExample(ExamdetailExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Examdetail record);

    int insertSelective(Examdetail record);

    List<Examdetail> selectByExample(ExamdetailExample example);

    Examdetail selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Examdetail record, @Param("example") ExamdetailExample example);

    int updateByExample(@Param("record") Examdetail record, @Param("example") ExamdetailExample example);

    int updateByPrimaryKeySelective(Examdetail record);

    int updateByPrimaryKey(Examdetail record);
}