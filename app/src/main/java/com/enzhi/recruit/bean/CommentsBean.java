package com.enzhi.recruit.bean;

public  class CommentsBean {
    /**
     * comment : 评论
     */

    private String id;
    private String comment;


    public CommentsBean(String id, String comment) {
        this.id = id;
        this.comment = comment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
