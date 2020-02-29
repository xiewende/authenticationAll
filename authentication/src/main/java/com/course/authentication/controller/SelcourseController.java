package com.course.authentication.controller;

import com.course.authentication.pojo.Selcourse;
import com.course.authentication.pojo.Student;
import com.course.authentication.service.SelcourseService;
import com.course.authentication.util.Result;
import com.course.authentication.util.SelCourseList;
import com.fasterxml.jackson.annotation.JsonAlias;
import net.sf.json.JSONArray;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @create 2019-12-24  20:00
 */
@CrossOrigin
@RestController
public class SelcourseController {

    @Autowired
    private SelcourseService selcourseService;

    //添加选课 某门课 某位老师 多位学生
    @PostMapping("/addSelcourse")
    public Result addSelcourse(Selcourse selcourse, String studentIdList,String leaderName){
        List<Integer> listStudentid = new ArrayList<>();
        //将json字符串的id转化成数组
        JSONArray jsonArray = JSONArray.fromObject(studentIdList);
        List<Integer> listStudentID = (List<Integer> )JSONArray.toCollection(jsonArray, Integer.class);

        //帮助前端去重
        for(Integer i :listStudentID){
            if(!listStudentid.contains(i)){
                listStudentid.add(i);
            }
        }


        if(selcourse.getLeaderId()==null && StringUtils.isBlank(leaderName)){
            return Result.build(400,"输入的数据不能为空！！！");
        }

        if(selcourse.getCourseId()==null || selcourse.getTeacherId()==null
                || StringUtils.isBlank(selcourse.getTerm()) || StringUtils.isBlank(selcourse.getVersion())){
            return Result.build(400,"输入的数据不能为空！！！");
        }

        Integer integer = selcourseService.insertSelcourse(selcourse, listStudentid,leaderName);
        if(integer>0){
            return Result.build(200,"添加成功！！！");
        }else {
            return Result.build(500,"该学生已经被安排了课程！！！");
        }
    }

    //查询某一门课的选课信息 若输入教师则查询关于这门课这个老师下的所有学生
    @PostMapping("/listAllTeacherSelcourse")
    public Result listAllOrTeacherSelcourse(Integer courseId, Integer teacherId,String term,String version,String leaderName){
        if(courseId==null || teacherId == null || StringUtils.isBlank(term) || StringUtils.isBlank(version)){
            return Result.build(400,"输入的数据不能为空！！！");
        }

        List<Selcourse> selcourseList = selcourseService.listAllOrTeacherSelcourse(courseId, teacherId,term,version,leaderName);

        if(selcourseList.size()>0){

            //取第一个拿课程id，课程名 教师id教师名
            Selcourse sel = selcourseList.get(0);

            //封装数据给慧玲
            SelCourseList selCourseListResult = new SelCourseList();
            //拿课程id，课程名
            selCourseListResult.setCourseId(sel.getCourseId());
            selCourseListResult.setCourseName(sel.getCourse().getName());
            //教师id教师名
            selCourseListResult.setTeacherId(sel.getTeacherId());
            selCourseListResult.setTeacherName(sel.getTeacher().getName());
            //负责人id,负责人姓名
            selCourseListResult.setLeadId(sel.getLeaderId());
            selCourseListResult.setLeadName(sel.getLeadTeacher().getName());

            //封装学生了
            List<Student> studentList = new ArrayList<>();
            for(Selcourse selcourse : selcourseList){
                Student student = selcourse.getStudent();
                System.out.println("name="+student.getName());
                studentList.add(student);
            }
            selCourseListResult.setStudents(studentList);

            return Result.build(200,"有数据",selCourseListResult);
        }else {
            return Result.build(500,"无数据");
        }
    }

    //删除某门课下的某条选课记录
    @PostMapping("/deleteSelcourse")
    public Result deleteSelcourse(Integer courseId,Integer studentId,String version){
        if(courseId == null || studentId == null || StringUtils.isBlank(version)){
            return Result.build(400,"输入的数据有误！！！");
        }
        Integer integer = selcourseService.deleteSelcourse(courseId,studentId,version);
        if(integer>0){
            return Result.build(200,"删除成功！！！");
        }else {
            return Result.build(500,"系统错误！！！");
        }
    }
}
