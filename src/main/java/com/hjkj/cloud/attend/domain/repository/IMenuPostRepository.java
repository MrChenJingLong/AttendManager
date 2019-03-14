package com.hjkj.cloud.attend.domain.repository;

import com.hjkj.cloud.attend.domain.model.MenuPost;

import java.util.List;

/**
 * 菜单岗位关联表
 */
public interface IMenuPostRepository {

    /**
     * 批量插入数据
     */
    void insertMenuPostBatch(List<MenuPost> menuPosts);

}
