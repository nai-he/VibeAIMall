package com.asyyy.shixun.home;

public class Comment {
    private int commentId;
    private int goodsId;
    private String userName;
    private String content;
    private float rating;
    private String createTime;

    public Comment() {
    }

    public Comment(int commentId, int goodsId, String userName, String content, float rating, String createTime) {
        this.commentId = commentId;
        this.goodsId = goodsId;
        this.userName = userName;
        this.content = content;
        this.rating = rating;
        this.createTime = createTime;
    }

    public Comment(int goodsId, String userName, String content, float rating, String createTime) {
        this.goodsId = goodsId;
        this.userName = userName;
        this.content = content;
        this.rating = rating;
        this.createTime = createTime;
    }

    // Getters and Setters
    public int getCommentId() { return commentId; }
    public void setCommentId(int commentId) { this.commentId = commentId; }
    public int getGoodsId() { return goodsId; }
    public void setGoodsId(int goodsId) { this.goodsId = goodsId; }
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public float getRating() { return rating; }
    public void setRating(float rating) { this.rating = rating; }
    public String getCreateTime() { return createTime; }
    public void setCreateTime(String createTime) { this.createTime = createTime; }
}
