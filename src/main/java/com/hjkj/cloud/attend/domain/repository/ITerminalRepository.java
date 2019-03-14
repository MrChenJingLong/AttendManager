package com.hjkj.cloud.attend.domain.repository;

import com.hjkj.cloud.attend.domain.model.Terminal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public interface ITerminalRepository extends JpaRepository<Terminal,String>,JpaSpecificationExecutor<Terminal> {


    Optional<Terminal> findByMacAddr(@Param("mac_addr") String macAddr);

    List<Terminal> findByAcceptState(@Param("accept_state") int acceptState);

    @EntityGraph(value = "Terminal.findAll", type = EntityGraph.EntityGraphType.FETCH)
    List<Terminal> findAll();

    @EntityGraph(value = "Terminal.findAll", type = EntityGraph.EntityGraphType.FETCH)
    Page<Terminal> findAll(Pageable pageable);

    @EntityGraph(value = "Terminal.findAll", type = EntityGraph.EntityGraphType.FETCH)
    Page<Terminal> findAll(@Nullable Specification<Terminal> spec, Pageable pageable);

    @Transactional
    @Modifying
    @Query(nativeQuery = true,value = "update terminal set registered_num=(select count(*) from user_terminal where terminal_id=?1) where id=?1")
    void refreshRegisterNumber(String tmId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true,value = "delete from user_terminal where terminal_id=?1 and user_id in ?2")
    void deleteUsersByTerminalIdAndUserIds(String tmId,List<String> userIds);

    @Transactional
    @Modifying
    @Query(nativeQuery = true,value = "delete from user_terminal where terminal_id=?1")
    void deleteUsersByTerminalId(String tmId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true,value = "delete from user_terminal where terminal_id in ?1")
    void deleteUsersByTerminalIds(List<String> tmIds);

    @Transactional
    @Modifying
    @Query(nativeQuery = true,value = "delete from terminal where id in ?1")
    void deleteTerminalBatch(List<String> tmIds);

}
