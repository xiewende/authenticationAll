package com.course.authentication.service.impl;

import com.course.authentication.mapper.FinallyMapper;
import com.course.authentication.pojo.GraduationDachengdu;
import com.course.authentication.pojo.PersonalDachengdu;
import com.course.authentication.pojo.Totaldachengdu;
import com.course.authentication.service.FinallyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @create 2019-12-29  17:21
 */
@Service
public class FinallyServiceImpl implements FinallyService {

    @Autowired
    private FinallyMapper finallyMapper;

    //总体达成度
    @Override
    public List<Totaldachengdu> checkTotaldachengdu(String courseName, String version) {
        return finallyMapper.checkTotaldachengdu(courseName,version);
    }

    //个人大程度
    @Override
    public List<PersonalDachengdu> chechPersonalDachengdu(String courseName, String version) {
        return finallyMapper.checkPersonalDachengdu(courseName,version);
    }

    @Override
    public List<GraduationDachengdu> checkGraduationDachengdu(String version) {
        return finallyMapper.checkGraduationDachengdu(version);
    }
}
