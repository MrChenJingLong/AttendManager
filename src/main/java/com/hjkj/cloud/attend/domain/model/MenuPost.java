package com.hjkj.cloud.attend.domain.model;

public class MenuPost {

    private String menuId;
    private String postId;

    public MenuPost() {
    }

    public MenuPost(String menuId, String postId) {
        this.menuId = menuId;
        this.postId = postId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }
}
