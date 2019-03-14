package com.hjkj.cloud.attend.domain.service;

import com.hjkj.cloud.attend.domain.model.Department;
import com.hjkj.cloud.attend.domain.model.Post;
import com.hjkj.cloud.attend.domain.model.Role;
import com.hjkj.cloud.attend.domain.model.User;
import com.hjkj.cloud.attend.domain.repository.*;
import com.hjkj.cloud.attend.infrastructure.constant.enums.ResultCode;
import com.hjkj.cloud.attend.infrastructure.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostManager {

    @Autowired
    private IPostRepository postRepository;
    @Autowired
    private IRoleRepository roleRepository;
    @Autowired
    private IDepartmentRepository departmentRepository;
    @Autowired
    private IMenuRepository menuRepository;
    @Autowired
    private IUserRepository userRepository;

    public Page<Post> findPostCriteria(Integer page, Integer size, Post postQuery) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.ASC, "id");
        if (postQuery == null) {
            return postRepository.findAll(pageable);
        }
        return postRepository.findAll((Specification<Post>) (Root<Post> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> list = new ArrayList<Predicate>();
            if(null != postQuery.getName() && !"".equals(postQuery.getName())){
                list.add(cb.like(root.get("name").as(String.class), "%" + postQuery.getName() + "%"));
            }
            if (null != postQuery.getDepartment()) {
                list.add(cb.equal(root.get("department").get("id").as(String.class),postQuery.getDepartment().getId()));
            }
            Predicate[] p = new Predicate[list.size()];
            return cb.and(list.toArray(p));
        },pageable);
    }

    public List<Post> findPosts(Post postQuery) {
        Sort sort = new Sort(Sort.Direction.ASC,"sort");
        if (postQuery == null) {
            return postRepository.findAll();
        }
        return postRepository.findAll((Specification<Post>) (Root<Post> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> list = new ArrayList<Predicate>();
            if (null != postQuery.getId() && !"".equals(postQuery.getId())) {
                list.add(cb.equal(root.get("id").as(String.class), postQuery.getId()));
            }
            if (null != postQuery.getRole() && !"".equals(postQuery.getRole().getId()) && postQuery.getRole().getId() != null) {
                list.add(cb.equal(root.get("role").get("id").as(String.class), postQuery.getRole().getId()));
            }
            if (null != postQuery.getDepartment() && !"".equals(postQuery.getDepartment().getId()) && postQuery.getDepartment().getId() != null) {
                list.add(cb.equal(root.get("department").get("id").as(String.class), postQuery.getDepartment().getId()));
            }
            Predicate[] p = new Predicate[list.size()];
            return cb.and(list.toArray(p));
        }, sort);
    }

    public void savePost(Post post) {
        if (StringUtils.isEmpty(post.getId())) {
            post.setId(com.hjkj.cloud.attend.infrastructure.utils.StringUtils.genUUID(8));
        }
        if (post.getDepartment() != null) {
            Optional<Department> department = departmentRepository.findById(post.getDepartment().getId());
            department.ifPresent(post::setDepartment);
        }
        if (post.getRole() != null) {
            Optional<Role> role = roleRepository.findById(post.getRole().getId());
            role.ifPresent(post::setRole);
        }
        postRepository.saveAndFlush(post);
    }

    @Transactional
    public void deletePostById(String postId,boolean isDel) {
        Post post = findPostById(postId);
        if (!isDel && post.getUsers().size() > 0) {
            throw new ServiceException(ResultCode.FAILED.code,"该岗位包含用户数据，无法被删除");
        }
        // 删除menu_post的关联数据
        //userRepository.deleteUserPostById(postId);
        menuRepository.clearMenusByPostId(postId);
        postRepository.deleteById(postId);
    }

    @Transactional
    public void deletePostBitch(List<String> postIds,boolean isDel) {
//        postRepository.deletePostsByIdIn(postIds);
        if (postIds == null) {
            return;
        }
        postIds.forEach(postId -> {
            deletePostById(postId,isDel);
        });
    }

    @Transactional
    public void updatePost(Post post) {
        Post oldPost = findPostById(post.getId());
        if (!StringUtils.isEmpty(post.getDepartment())) {
            Optional<Department> department = departmentRepository.findById(post.getDepartment().getId());
            department.ifPresent(oldPost::setDepartment);
        }
        if (!StringUtils.isEmpty(post.getRole())) {
            Optional<Role> role = roleRepository.findById(post.getRole().getId());
            role.ifPresent(oldPost::setRole);
        }
        if (!StringUtils.isEmpty(post.getName())) {
            oldPost.setName(post.getName());
        }
        if (!StringUtils.isEmpty(post.getTag())) {
            oldPost.setTag(post.getTag());
        }
        oldPost.setSort(post.getSort());
        oldPost.setFlag(post.getFlag());
        oldPost.setValue(post.getValue());
        postRepository.saveAndFlush(oldPost);
    }

    //给岗位分配部门
    @Transactional
    public void allotDepart(List<String> postIdList,String departId) {
        Department department = findDepartById(departId);
        for (String postId : postIdList) {
            Optional<Post> post = postRepository.findById(postId);
            post.ifPresent((p) -> {
                p.setDepartment(department);
                postRepository.saveAndFlush(p);
            });
        }
    }

    public Post findPostById(String postId) {
        Optional<Post> post = postRepository.findById(postId);
        if (!post.isPresent()) {
            throw new ServiceException(ResultCode.FAILED.code,"岗位[" + postId + "]不存在");
        }
        return post.get();
    }

    //在原来的基础上给角色分配岗位
    @Transactional
    public void allotRole(List<String> postIdList,String roleId) {
        Role role = findRoleById(roleId);
        for (String postId : postIdList) {
            Optional<Post> post = postRepository.findById(postId);
            post.ifPresent((p) -> {
                p.setRole(role);
                postRepository.saveAndFlush(p);
            });
        }

    }


    public Role findRoleById(String roleId) {
        Optional<Role> role = roleRepository.findById(roleId);
        if (!role.isPresent()) {
            throw new ServiceException(ResultCode.FAILED.code,"角色[" + roleId + "]不存在");
        }
        return role.get();
    }

    public Department findDepartById(String departId) {
        Optional<Department> department = departmentRepository.findById(departId);
        return department.orElseThrow(() -> new ServiceException(ResultCode.FAILED.code,"找不到部门[" + departId + "]"));
    }

}
