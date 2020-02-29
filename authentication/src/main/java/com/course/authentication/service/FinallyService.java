package com.course.authentication.service;

import com.course.authentication.pojo.GraduationDachengdu;
import com.course.authentication.pojo.PersonalDachengdu;
import com.course.authentication.pojo.Totaldachengdu;

import java.util.List;

/**
 * @create 2019-12-29  17:20
 */
public interface FinallyService {

    //总体达成度
    List<Totaldachengdu> checkTotaldachengdu(String courseName, String version);

    //个人大程度
    List<PersonalDachengdu> chechPersonalDachengdu(String courseName, String version);

    //毕业要求大程度
    List<GraduationDachengdu> checkGraduationDachengdu(String version);
}
