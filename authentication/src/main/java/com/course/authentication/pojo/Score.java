package com.course.authentication.pojo;

public class Score {
    private Integer id;

    private Integer selcourseId;

    private Integer examdetailId;

    private Double scores;

    private String term;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSelcourseId() {
        return selcourseId;
    }

    public void setSelcourseId(Integer selcourseId) {
        this.selcourseId = selcourseId;
    }

    public Integer getExamdetailId() {
        return examdetailId;
    }

    public void setExamdetailId(Integer examdetailId) {
        this.examdetailId = examdetailId;
    }

    public Double getScores() {
        return scores;
    }

    public void setScores(Double scores) {
        this.scores = scores;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term == null ? null : term.trim();
    }
}