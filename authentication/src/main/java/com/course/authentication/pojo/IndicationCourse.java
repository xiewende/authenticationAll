package com.course.authentication.pojo;

public class IndicationCourse {
    private Integer id;

    private Integer indicationId;

    private Integer courseId;

    private Double weight;

    private Indication indication;
    private Course course;

    public Indication getIndication() {
        return indication;
    }

    public void setIndication(Indication indication) {
        this.indication = indication;
    }

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

    public Integer getIndicationId() {
        return indicationId;
    }

    public void setIndicationId(Integer indicationId) {
        this.indicationId = indicationId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }
}