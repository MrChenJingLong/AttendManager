package com.hjkj.cloud.attend.domain.model;

public class UserPost {

    private String userId;
    private String postId;

    public UserPost() {
    }

    public UserPost(String userId, String postId) {
        this.userId = userId;
        this.postId = postId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }
}
