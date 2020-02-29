package com.course.authentication.service.impl;

import com.course.authentication.mapper.SelcourseMapper;
import com.course.authentication.mapper.TeacherMapper;
import com.course.authentication.pojo.Selcourse;
import com.course.authentication.pojo.SelcourseExample;
import com.course.authentication.pojo.Teacher;
import com.course.authentication.pojo.TeacherExample;
import com.course.authentication.service.SelcourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @create 2019-12-24  19:49
 */
@Service
public class SelcourseServiceImpl implements SelcourseService {

    @Autowired
    private SelcourseMapper selcourseMapper;
    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public Integer insertSelcourse(Selcourse selcourse, List<Integer> listStudentID,String leaderName) {
        //b标志是否成功
        int tag = 0;

        //判断是否是第一次提交
        if(selcourse.getLeaderId()==null){
            TeacherExample teacherExample = new TeacherExample();
            TeacherExample.Criteria teacherExampleCriteria = teacherExample.createCriteria();
            teacherExampleCriteria.andNameEqualTo(leaderName);
            Teacher teacher = teacherMapper.selectByExample(teacherExample).get(0);
            selcourse.setLeaderId(teacher.getId());
        }

        for(Integer studengId : listStudentID ){
            //判断是否在数据酷里面得了
            Selcourse selcourse1 = selcourseMapper.chechSelcourse(selcourse.getCourseId(), studengId, selcourse.getVersion());
            if(selcourse1!=null){
                continue;
            }
            selcourse.setStudentId(studengId);
            selcourseMapper.insert(selcourse);
            tag++;
        }
        return tag;
    }

    //查询某一门课的选课信息 若输入教师则查询关于这门课这个老师下的所有学生
    @Override
    public List<Selcourse> listAllOrTeacherSelcourse(Integer courseId, Integer teacherId,String term ,String version,String leaderName) {



        List<Selcourse> selcourseList;
        //if(teacherId != null){
            selcourseList = selcourseMapper.listAllSelcourseByCourseByTeacher(courseId,teacherId,term,version);
       // }else {
           // selcourseList = selcourseMapper.listAllSelcourseByCourse(courseId);
      //  }

        //取拿课程负责人
        for(Selcourse selcourse : selcourseList){
            Teacher leader = teacherMapper.selectByPrimaryKey(selcourse.getLeaderId());
            selcourse.setLeadTeacher(leader);
        }

        return selcourseList;
    }

    @Override
    public Integer deleteSelcourse(Integer courseId,Integer studentId,String version) {
        SelcourseExample example = new SelcourseExample();
        SelcourseExample.Criteria criteria = example.createCriteria();
        criteria.andCourseIdEqualTo(courseId);
        criteria.andStudentIdEqualTo(studentId);
        criteria.andVersionEqualTo(version);
        return selcourseMapper.deleteByExample(example);
    }
}
