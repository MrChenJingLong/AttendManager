package com.hjkj.cloud.attend.domain.repository;

import com.hjkj.cloud.attend.domain.model.Department;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public interface IDepartmentRepository extends JpaRepository<Department,String> ,JpaSpecificationExecutor<Department> {

    @EntityGraph(value = "Department.findDepartmentByLevel", type = EntityGraph.EntityGraphType.FETCH)
    Optional<Department> findDepartmentByLevel(@Param("level") int level);

    @EntityGraph(value = "Department.findById", type = EntityGraph.EntityGraphType.FETCH)
    Optional<Department> findById(String departId);

    @Transactional
    List<Department> findDepartmentByParentDepartment_Id(String parentId);

}
