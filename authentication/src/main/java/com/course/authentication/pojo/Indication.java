package com.course.authentication.pojo;

import java.util.List;

public class Indication {
    private Integer id;

    private String indicationNum;

    private String content;

    private String version;

    private List<IndicationCourse> indicationCourseList;

    public List<IndicationCourse> getIndicationCourseList() {
        return indicationCourseList;
    }

    public void setIndicationCourseList(List<IndicationCourse> indicationCourseList) {
        this.indicationCourseList = indicationCourseList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIndicationNum() {
        return indicationNum;
    }

    public void setIndicationNum(String indicationNum) {
        this.indicationNum = indicationNum == null ? null : indicationNum.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }
}