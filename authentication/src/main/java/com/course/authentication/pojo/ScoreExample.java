package com.course.authentication.pojo;

import java.util.ArrayList;
import java.util.List;

public class ScoreExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ScoreExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andSelcourseIdIsNull() {
            addCriterion("selcourse_id is null");
            return (Criteria) this;
        }

        public Criteria andSelcourseIdIsNotNull() {
            addCriterion("selcourse_id is not null");
            return (Criteria) this;
        }

        public Criteria andSelcourseIdEqualTo(Integer value) {
            addCriterion("selcourse_id =", value, "selcourseId");
            return (Criteria) this;
        }

        public Criteria andSelcourseIdNotEqualTo(Integer value) {
            addCriterion("selcourse_id <>", value, "selcourseId");
            return (Criteria) this;
        }

        public Criteria andSelcourseIdGreaterThan(Integer value) {
            addCriterion("selcourse_id >", value, "selcourseId");
            return (Criteria) this;
        }

        public Criteria andSelcourseIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("selcourse_id >=", value, "selcourseId");
            return (Criteria) this;
        }

        public Criteria andSelcourseIdLessThan(Integer value) {
            addCriterion("selcourse_id <", value, "selcourseId");
            return (Criteria) this;
        }

        public Criteria andSelcourseIdLessThanOrEqualTo(Integer value) {
            addCriterion("selcourse_id <=", value, "selcourseId");
            return (Criteria) this;
        }

        public Criteria andSelcourseIdIn(List<Integer> values) {
            addCriterion("selcourse_id in", values, "selcourseId");
            return (Criteria) this;
        }

        public Criteria andSelcourseIdNotIn(List<Integer> values) {
            addCriterion("selcourse_id not in", values, "selcourseId");
            return (Criteria) this;
        }

        public Criteria andSelcourseIdBetween(Integer value1, Integer value2) {
            addCriterion("selcourse_id between", value1, value2, "selcourseId");
            return (Criteria) this;
        }

        public Criteria andSelcourseIdNotBetween(Integer value1, Integer value2) {
            addCriterion("selcourse_id not between", value1, value2, "selcourseId");
            return (Criteria) this;
        }

        public Criteria andExamdetailIdIsNull() {
            addCriterion("examdetail_id is null");
            return (Criteria) this;
        }

        public Criteria andExamdetailIdIsNotNull() {
            addCriterion("examdetail_id is not null");
            return (Criteria) this;
        }

        public Criteria andExamdetailIdEqualTo(Integer value) {
            addCriterion("examdetail_id =", value, "examdetailId");
            return (Criteria) this;
        }

        public Criteria andExamdetailIdNotEqualTo(Integer value) {
            addCriterion("examdetail_id <>", value, "examdetailId");
            return (Criteria) this;
        }

        public Criteria andExamdetailIdGreaterThan(Integer value) {
            addCriterion("examdetail_id >", value, "examdetailId");
            return (Criteria) this;
        }

        public Criteria andExamdetailIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("examdetail_id >=", value, "examdetailId");
            return (Criteria) this;
        }

        public Criteria andExamdetailIdLessThan(Integer value) {
            addCriterion("examdetail_id <", value, "examdetailId");
            return (Criteria) this;
        }

        public Criteria andExamdetailIdLessThanOrEqualTo(Integer value) {
            addCriterion("examdetail_id <=", value, "examdetailId");
            return (Criteria) this;
        }

        public Criteria andExamdetailIdIn(List<Integer> values) {
            addCriterion("examdetail_id in", values, "examdetailId");
            return (Criteria) this;
        }

        public Criteria andExamdetailIdNotIn(List<Integer> values) {
            addCriterion("examdetail_id not in", values, "examdetailId");
            return (Criteria) this;
        }

        public Criteria andExamdetailIdBetween(Integer value1, Integer value2) {
            addCriterion("examdetail_id between", value1, value2, "examdetailId");
            return (Criteria) this;
        }

        public Criteria andExamdetailIdNotBetween(Integer value1, Integer value2) {
            addCriterion("examdetail_id not between", value1, value2, "examdetailId");
            return (Criteria) this;
        }

        public Criteria andScoresIsNull() {
            addCriterion("scores is null");
            return (Criteria) this;
        }

        public Criteria andScoresIsNotNull() {
            addCriterion("scores is not null");
            return (Criteria) this;
        }

        public Criteria andScoresEqualTo(Double value) {
            addCriterion("scores =", value, "scores");
            return (Criteria) this;
        }

        public Criteria andScoresNotEqualTo(Double value) {
            addCriterion("scores <>", value, "scores");
            return (Criteria) this;
        }

        public Criteria andScoresGreaterThan(Double value) {
            addCriterion("scores >", value, "scores");
            return (Criteria) this;
        }

        public Criteria andScoresGreaterThanOrEqualTo(Double value) {
            addCriterion("scores >=", value, "scores");
            return (Criteria) this;
        }

        public Criteria andScoresLessThan(Double value) {
            addCriterion("scores <", value, "scores");
            return (Criteria) this;
        }

        public Criteria andScoresLessThanOrEqualTo(Double value) {
            addCriterion("scores <=", value, "scores");
            return (Criteria) this;
        }

        public Criteria andScoresIn(List<Double> values) {
            addCriterion("scores in", values, "scores");
            return (Criteria) this;
        }

        public Criteria andScoresNotIn(List<Double> values) {
            addCriterion("scores not in", values, "scores");
            return (Criteria) this;
        }

        public Criteria andScoresBetween(Double value1, Double value2) {
            addCriterion("scores between", value1, value2, "scores");
            return (Criteria) this;
        }

        public Criteria andScoresNotBetween(Double value1, Double value2) {
            addCriterion("scores not between", value1, value2, "scores");
            return (Criteria) this;
        }

        public Criteria andTermIsNull() {
            addCriterion("term is null");
            return (Criteria) this;
        }

        public Criteria andTermIsNotNull() {
            addCriterion("term is not null");
            return (Criteria) this;
        }

        public Criteria andTermEqualTo(String value) {
            addCriterion("term =", value, "term");
            return (Criteria) this;
        }

        public Criteria andTermNotEqualTo(String value) {
            addCriterion("term <>", value, "term");
            return (Criteria) this;
        }

        public Criteria andTermGreaterThan(String value) {
            addCriterion("term >", value, "term");
            return (Criteria) this;
        }

        public Criteria andTermGreaterThanOrEqualTo(String value) {
            addCriterion("term >=", value, "term");
            return (Criteria) this;
        }

        public Criteria andTermLessThan(String value) {
            addCriterion("term <", value, "term");
            return (Criteria) this;
        }

        public Criteria andTermLessThanOrEqualTo(String value) {
            addCriterion("term <=", value, "term");
            return (Criteria) this;
        }

        public Criteria andTermLike(String value) {
            addCriterion("term like", value, "term");
            return (Criteria) this;
        }

        public Criteria andTermNotLike(String value) {
            addCriterion("term not like", value, "term");
            return (Criteria) this;
        }

        public Criteria andTermIn(List<String> values) {
            addCriterion("term in", values, "term");
            return (Criteria) this;
        }

        public Criteria andTermNotIn(List<String> values) {
            addCriterion("term not in", values, "term");
            return (Criteria) this;
        }

        public Criteria andTermBetween(String value1, String value2) {
            addCriterion("term between", value1, value2, "term");
            return (Criteria) this;
        }

        public Criteria andTermNotBetween(String value1, String value2) {
            addCriterion("term not between", value1, value2, "term");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}