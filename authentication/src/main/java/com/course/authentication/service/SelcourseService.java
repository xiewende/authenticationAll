package com.course.authentication.service;

import com.course.authentication.pojo.Selcourse;

import java.util.List;

/**
 * @create 2019-12-24  19:27
 */
public interface SelcourseService {

    //添加选课 某门课 某位老师 多位学生
    public Integer insertSelcourse(Selcourse selcourse,List<Integer> listStudentID,String leaderName);

    //查询某一门课的选课信息 若输入教师则查询关于这门课这个老师下的所有学生
    public List<Selcourse> listAllOrTeacherSelcourse(Integer courseId,Integer teacherId,String term,String version,String leaderName);

    //删除某门课下的某条选课记录
    public Integer deleteSelcourse(Integer courseId,Integer studentId,String version);
}
