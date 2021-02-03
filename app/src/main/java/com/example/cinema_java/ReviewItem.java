package com.example.cinema_java;

public class ReviewItem {
    private int userImage;
    private String id;
    private String time;
    private int rate;
    private String comment;
    private String recommend;

    public ReviewItem(int userImage, String id, String time, int rate, String comment, String recommend) {
        this.userImage = userImage;
        this.id = id;
        this.time = time;
        this.rate = rate;
        this.comment = comment;
        this.recommend = recommend;
    }

    public int getUserImage() {
        return userImage;
    }

    public void setUserImage(int userImage) {
        this.userImage = userImage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }
}
