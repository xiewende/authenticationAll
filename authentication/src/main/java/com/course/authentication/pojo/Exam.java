package com.course.authentication.pojo;

public class Exam {
    private Integer id;

    private Double ratio;

    private String version;

    private Integer examtypeId;

    private Integer courseId;

    private Course course;
    private Examtype examtype;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Examtype getExamtype() {
        return examtype;
    }

    public void setExamtype(Examtype examtype) {
        this.examtype = examtype;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getRatio() {
        return ratio;
    }

    public void setRatio(Double ratio) {
        this.ratio = ratio;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }

    public Integer getExamtypeId() {
        return examtypeId;
    }

    public void setExamtypeId(Integer examtypeId) {
        this.examtypeId = examtypeId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }
}