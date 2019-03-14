package com.hjkj.cloud.attend.domain.repository;

import com.hjkj.cloud.attend.domain.model.Menu;
import com.hjkj.cloud.attend.domain.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public interface IMenuRepository extends JpaRepository<Menu,String>,JpaSpecificationExecutor<Menu> {

    @Transactional
    void deleteMenusByIdIn(List<String> idList);

    @Query(nativeQuery = true,value = "select * from menu where id in (select menu_id from menu_post where role_id = ?1)")
    List<Menu> findMenusByRoleId(String roleId);

    @Modifying
    @Query(nativeQuery = true,value = "delete from menu_post where post_id = ?1")
    void clearMenusByPostId(String postId);

    @Modifying
    @Query(nativeQuery = true,value = "delete from menu_post where menu_id in ?1")
    void deleteMenusByMenuIds(List<String> menuIds);

    @Query(nativeQuery = true,value = "select id from menu")
    List<String> findAllMenuIds();
}
