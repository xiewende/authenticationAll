package com.course.authentication.pojo;

import java.util.List;

public class Course {
    private Integer id;

    private String courseNum;

    private String name;

    private Integer theoryTime;

    private Integer experTime;

    private Double credit;

    private String type;

    private String property;

    private String version;

    private Integer teacherId;

    private Teacher teacher;

    private List<Exam> listExam;



    public List<Exam> getListExam() {
        return listExam;
    }

    public void setListExam(List<Exam> listExam) {
        this.listExam = listExam;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCourseNum() {
        return courseNum;
    }

    public void setCourseNum(String courseNum) {
        this.courseNum = courseNum == null ? null : courseNum.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getTheoryTime() {
        return theoryTime;
    }

    public void setTheoryTime(Integer theoryTime) {
        this.theoryTime = theoryTime;
    }

    public Integer getExperTime() {
        return experTime;
    }

    public void setExperTime(Integer experTime) {
        this.experTime = experTime;
    }

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property == null ? null : property.trim();
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }
}