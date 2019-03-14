package com.hjkj.cloud.attend.domain.repository;

import com.hjkj.cloud.attend.domain.model.UserPost;

import java.util.List;

/**
 *  用户岗位关联表
 */
public interface IUserPostRepository {

    /**
     * 批量插入数据
     */
    void insertUserPostBatch(List<UserPost> userPosts);

}
