package com.hjkj.cloud.attend.domain.repository;

import com.hjkj.cloud.attend.domain.model.Department;
import com.hjkj.cloud.attend.domain.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public interface IPostRepository extends JpaRepository<Post,String>,JpaSpecificationExecutor<Post> {

    @Transactional
    @EntityGraph(value = "Post.findAll", type = EntityGraph.EntityGraphType.FETCH)
    Page<Post> findAll(Pageable pageable);

    @Transactional
    @EntityGraph(value = "Post.findAll", type = EntityGraph.EntityGraphType.FETCH)
    Page<Post> findAll(@Nullable Specification<Post> spec, Pageable pageable);

    @Transactional
    @EntityGraph(value = "Post.findAll", type = EntityGraph.EntityGraphType.FETCH)
    List<Post> findAll();

    @Transactional
    @EntityGraph(value = "Post.findUsers", type = EntityGraph.EntityGraphType.FETCH)
    List<Post> findAll(@Nullable Specification<Post> spec, Sort sort);

    @EntityGraph(value = "Post.findById", type = EntityGraph.EntityGraphType.FETCH)
    Optional<Post> findById(String postId);

    @Transactional
    void deletePostsByIdIn(List<String> idList);
}
