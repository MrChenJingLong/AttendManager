package com.hjkj.cloud.attend.domain.service;

import com.alibaba.fastjson.JSON;
import com.hjkj.cloud.attend.domain.model.Menu;
import com.hjkj.cloud.attend.domain.model.MenuPost;
import com.hjkj.cloud.attend.domain.model.Post;
import com.hjkj.cloud.attend.domain.repository.IMenuPostRepository;
import com.hjkj.cloud.attend.domain.repository.IMenuRepository;
import com.hjkj.cloud.attend.domain.repository.IPostRepository;
import com.hjkj.cloud.attend.infrastructure.constant.enums.ResultCode;
import com.hjkj.cloud.attend.infrastructure.exception.ServiceException;
import com.hjkj.cloud.attend.infrastructure.mysql.repositoryImpl.AbstractBatchRepositoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MenuManager extends AbstractBatchRepositoryImpl<Menu> {

    private final Logger log = LoggerFactory.getLogger(MenuManager.class);

    @Autowired
    private IMenuRepository menuRepository;
    @Autowired
    private IPostRepository postRepository;
    @Autowired
    private IMenuPostRepository menuPostRepository;

    @Transactional
    public void saveMenu(Menu menu) {
        if (StringUtils.isEmpty(menu.getId())) {
            menu.setId(com.hjkj.cloud.attend.infrastructure.utils.StringUtils.genUUID(8));
        }
        if (menu.getParentMenu() != null){
            Optional<Menu> parentMenu = menuRepository.findById(menu.getParentMenu().getId());
            parentMenu.ifPresent(menu::setParentMenu);
        }

        menuRepository.saveAndFlush(menu);
    }

    public void deleteMenuById(String menuId) {
        menuRepository.deleteById(menuId);
    }

    public void delMenusByIds(List<String> menuIdList) {
        menuRepository.deleteMenusByIdIn(menuIdList);
    }

    @Transactional
    public void updateMenu(Menu menu) {
        Menu oldMenu = findMenuById(menu.getId());
        if (!StringUtils.isEmpty(menu.getName())) {
            oldMenu.setName(menu.getName());
        }
        if (!StringUtils.isEmpty(menu.getParentMenu())) {
            oldMenu.setParentMenu(menu.getParentMenu());
        }
        if (!StringUtils.isEmpty(menu.getIcon())) {
            oldMenu.setIcon(menu.getIcon());
        }
        if (!StringUtils.isEmpty(menu.getRef())) {
            oldMenu.setRef(menu.getRef());
        }
        if (!StringUtils.isEmpty(menu.getUrl())) {
            oldMenu.setUrl(menu.getUrl());
        }

        oldMenu.setType(menu.getType());
        oldMenu.setSort(menu.getSort());
        oldMenu.setUserLimit(menu.getUserLimit());

        menuRepository.saveAndFlush(oldMenu);

    }

    //给菜单重新分配岗位
    @Transactional
    public void allotPost(List<String> menuIdList, String postId) {
//        Post post = findPostById(postId);
        //清空原来的分配
        menuRepository.clearMenusByPostId(postId);
        //重新分配岗位
//        List<Menu> menuList = new ArrayList<>();
        List<MenuPost> menuPosts = new ArrayList<>();
        MenuPost menuPost;
        for (String menuId : menuIdList) {
//            Menu menu = findMenuById(menuId);
//            menu.getPosts().add(post);
//            menuList.add(menu);
            menuPost = new MenuPost(menuId,postId);
            menuPosts.add(menuPost);
        }
       menuPostRepository.insertMenuPostBatch(menuPosts);
//        bitchUpdate(menuList);
        log.info("allot menu ids:" + menuIdList + " by post:" + postId);
    }

    public Page<Menu> findMenuCriteria(Integer page, Integer size, Menu menuQuery) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.ASC, "id");
        if (menuQuery == null) {
            return menuRepository.findAll(pageable);
        }
        Page<Menu> menuPage = menuRepository.findAll(new Specification<Menu>(){
            @Override
            public Predicate toPredicate(Root<Menu> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();
                if(null != menuQuery.getName() && !"".equals(menuQuery.getName())){
                    list.add(criteriaBuilder.equal(root.get("name").as(String.class), menuQuery.getName()));
                } else if(menuQuery.getSort() != -1){
                    list.add(criteriaBuilder.equal(root.get("sort").as(Integer.class), menuQuery.getSort()));
                } else if (menuQuery.getType() != -1) {
                    list.add(criteriaBuilder.equal(root.get("type").as(Integer.class), menuQuery.getType()));
                }
                Predicate[] p = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(p));
            }
        },pageable);
        return menuPage;
    }

    public Menu findMenuById(String menuId) {
        Optional<Menu> menu = menuRepository.findById(menuId);
        return menu.orElseThrow(() -> new ServiceException(ResultCode.FAILED.code,"找不到菜单[" + menuId + "]"));
    }

    public Post findPostById(String postId) {
        Optional<Post> post = postRepository.findById(postId);
        if (!post.isPresent()) {
            throw new ServiceException(ResultCode.FAILED.code,"岗位[" + postId + "]不存在");
        }
        return post.get();
    }

}
