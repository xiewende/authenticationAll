package com.course.authentication.pojo;

public class Target {
    private Integer id;

    private String content;

    private Integer courseId;

    private String version;

    private String targerNum;

    private Course course;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }

    public String getTargerNum() {
        return targerNum;
    }

    public void setTargerNum(String targerNum) {
        this.targerNum = targerNum == null ? null : targerNum.trim();
    }
}