package com.hjkj.cloud.attend.domain.repository;

import com.hjkj.cloud.attend.domain.model.Duty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.*;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IDutyRepository extends JpaRepository<Duty,String>,JpaSpecificationExecutor<Duty> {

    @EntityGraph(value = "Duty.findAll", type = EntityGraph.EntityGraphType.FETCH)
    Page<Duty> findAll(Pageable pageable);

    @EntityGraph(value = "Duty.findAll", type = EntityGraph.EntityGraphType.FETCH)
    Page<Duty> findAll(@Nullable Specification<Duty> spec, Pageable pageable);

    @Modifying
    @Query(nativeQuery = true,value = "delete from duty where user_id=?1")
    void deleteDutyByUserId(String userId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true,value = "delete from duty where user_id in ?1")
    void deleteDutyByUserIds(List<String> userIds);

    @Modifying
    @Query(nativeQuery = true,value = "delete from duty where terminal_id=?1")
    void deleteDutyByTerminalId(String tmId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true,value = "delete from duty where terminal_id in ?1")
    void deleteDutyByTerminalIds(List<String> tmIds);

}
