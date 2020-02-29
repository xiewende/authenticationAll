package com.course.authentication.pojo;

public class Examdetail {
    private Integer id;

    private String name;

    private Double weight;

    private Double score;

    private Integer examtypeId;

    private Integer targetId;

    private String version;

    private Examtype examtype;
    private Target target;

    public Examtype getExamtype() {
        return examtype;
    }

    public void setExamtype(Examtype examtype) {
        this.examtype = examtype;
    }

    public Target getTarget() {
        return target;
    }

    public void setTarget(Target target) {
        this.target = target;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Integer getExamtypeId() {
        return examtypeId;
    }

    public void setExamtypeId(Integer examtypeId) {
        this.examtypeId = examtypeId;
    }

    public Integer getTargetId() {
        return targetId;
    }

    public void setTargetId(Integer targetId) {
        this.targetId = targetId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }
}