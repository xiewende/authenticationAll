package com.course.authentication.mapper;

import com.course.authentication.pojo.GraduationDachengdu;
import com.course.authentication.pojo.PersonalDachengdu;
import com.course.authentication.pojo.Totaldachengdu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @create 2019-12-29  17:12
 */
@Mapper
public interface FinallyMapper {

    List<Totaldachengdu> checkTotaldachengdu(String courseName,String version);

    List<PersonalDachengdu> checkPersonalDachengdu(String courseName,String version);

    List<GraduationDachengdu> checkGraduationDachengdu(String version);
}
