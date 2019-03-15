package com.enzhi.recruit.bean;
public  class UsersBean {
    /**
     * id : 804
     * account : 111
     * password : 222
     * sex : 0
     * age : 9
     * school : 11
     * education : 111
     */

    private int id;
    private String account;
    private String password;
    private String sex;
    private String age;
    private String company;
    private String job;
    private int userType;

    public UsersBean(){

    }

    public UsersBean(int id, String account, String password, String sex, String age, String company, String job, int userType) {
        this.id = id;
        this.account = account;
        this.password = password;
        this.sex = sex;
        this.age = age;
        this.company = company;
        this.job = job;
        this.userType = userType;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }


    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
        }

    @Override
    public String toString() {
        return "UsersBean{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                ", age='" + age + '\'' +
                ", company='" + company + '\'' +
                ", job='" + job + '\'' +
                ", userType=" + userType +
                '}';
    }
}