package com.enzhi.recruit.bean;

import java.util.List;

public  class ResumesBean {
    /**
     * school : 毕业院校
     * education : 学历
     * job : 求职
     * description : 个人简介
     * comments : [{"comment":"评论"}]
     */

    private String school;
    private String education;
    private String job;
    private String description;
    private String salary;


    public ResumesBean(String school, String education, String job, String description, String salary, List<CommentsBean> comments) {
        this.school = school;
        this.education = education;
        this.job = job;
        this.description = description;
        this.salary = salary;
        this.comments = comments;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    private List<CommentsBean> comments;

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<CommentsBean> getComments() {
        return comments;
    }

    public void setComments(List<CommentsBean> comments) {
        this.comments = comments;
    }

}
