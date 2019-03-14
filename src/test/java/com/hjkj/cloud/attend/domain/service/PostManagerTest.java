package com.hjkj.cloud.attend.domain.service;

import com.hjkj.cloud.attend.domain.model.Department;
import com.hjkj.cloud.attend.domain.model.Post;
import com.hjkj.cloud.attend.domain.model.User;
import com.hjkj.cloud.attend.infrastructure.utils.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableJpaAuditing
public class PostManagerTest {

    @Autowired
    private PostManager postManager;

    @Test
    @Transactional
    public void findPostCriteria() throws Exception {
        Post postQuery = new Post();
        postQuery.relateDepartId("fdd966ec");
        Page<Post> postPage = postManager.findPostCriteria(0, 3, postQuery);
        postPage.forEach((post -> {
            System.out.println(post.getId());
            System.out.println(post.getName());
        }));
    }

    @Test
    @Transactional
    public void savePost() throws Exception {
        Post post = new Post();
        post.setId(StringUtils.genUUID(8));
        post.setName("普通员工");
        post.setValue(3);
        post.relateDepartId("fdd966ec");
        post.relateRoleId("285c7243");
        postManager.savePost(post);

        post = new Post();
        post.setId(StringUtils.genUUID(8));
        post.setName("经理");
        post.setValue(2);
        post.relateDepartId("fdd966ec");
        post.relateRoleId("b3a0f13c");
        postManager.savePost(post);
//
        post = new Post();
        post.setId(StringUtils.genUUID(8));
        post.setName("老板");
        post.setValue(1);
        post.relateDepartId("13361303");
        post.relateRoleId("6c9ae27b");
        postManager.savePost(post);

    }

    @Test
    public void deletePostById() throws Exception {
        postManager.deletePostById("7f6f292d",false);
    }



    @Test
    public void allotDepart() throws Exception {
        List<String> postIdList = new ArrayList<>();
        postIdList.add("4b58d78b");
        postIdList.add("77b89c77");
        postIdList.add("6998f9d8");
        postManager.allotDepart(postIdList,"dcc57632");
    }

    @Test
    public void allotRole() throws Exception {
        List<String> postIdList = new ArrayList<>();
        postIdList.add("d590180b");
        postIdList.add("8ddf8582");
        postIdList.add("770352ac");
        postManager.allotRole(postIdList,"5e0d8357");
    }

    @Test
    public void findPostById() throws Exception {
        Post post = postManager.findPostById("2e7edf36");
        post.getMenus().forEach(menu -> {
            System.out.println(menu.getId() + ":" + menu.getName());
        });
    }
}