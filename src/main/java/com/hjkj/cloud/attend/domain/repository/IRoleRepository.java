package com.hjkj.cloud.attend.domain.repository;

import com.hjkj.cloud.attend.domain.model.Role;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public interface IRoleRepository extends JpaRepository<Role,String>,JpaSpecificationExecutor<Role> {

    @Transactional
    void deleteRolesByIdIn(List<String> idList);

    @EntityGraph(value = "Role.findRoleByIdIn", type = EntityGraph.EntityGraphType.FETCH)
    List<Role> findRoleByIdIn(List<String> idList);

    @EntityGraph(value = "Role.findById", type = EntityGraph.EntityGraphType.FETCH)
    Optional<Role> findById(String roleId);

    @EntityGraph(value = "Role.findAll", type = EntityGraph.EntityGraphType.FETCH)
    List<Role> findAll();

}
