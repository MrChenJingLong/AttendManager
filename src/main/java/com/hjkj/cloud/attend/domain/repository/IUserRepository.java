package com.hjkj.cloud.attend.domain.repository;

import com.hjkj.cloud.attend.domain.model.Terminal;
import com.hjkj.cloud.attend.domain.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public interface IUserRepository extends JpaRepository<User,String>,JpaSpecificationExecutor<User> {

    User findUserByCardNum(@Param("cardNum")String cardNum);

    User findUserByCardNumAndPassword(@Param("cardNum")String cardNum, @Param("password")String password);

    @Query(nativeQuery = true,value = "select count(*) from menu where id in (select menu_id from menu_post where post_id in (select post_id from user_post where user_id=?1)) and url=?2")
    int countByUserIdAndUrl(String userId,String url);

    @Query(nativeQuery = true,value = "select * from department where id=(select department_id from post where id=(select post_id from user_post where user_id=?1))")
    List<Object> findDepartById(String userId);

    @Query(nativeQuery = true,value = "select user_id from user_terminal where terminal_id=?1")
    List<String> findUserIdsByTerminalId(String tmId);

    @Query(nativeQuery = true,value = "select distinct(user_id) from user_terminal where terminal_id in ?1")
    List<String> findUserIdsByTerminalIds(List<String> tmIds);

    @Query(nativeQuery = true,value = "select count(*) from user_terminal where terminal_id=?1")
    int countUsersByTerminalId(String tmId);

    @Query(nativeQuery = true,value = "select count(*) from user_terminal where user_id=(select id from user where card_num=?1) and terminal_id=?2")
    int isExistsByCardNumAndMacAddr(String cardNum,String tmId);

    @Query(nativeQuery = true,value = "select count(*) from user_terminal where user_id=?1 and terminal_id=(select id from terminal where mac_addr=?2)")
    int isExistsUserByMacAddr(String userId,String macAddr);

    @EntityGraph(value = "User.findById", type = EntityGraph.EntityGraphType.FETCH)
    Optional<User> findById(String userId);

    @EntityGraph(value = "User.findAll", type = EntityGraph.EntityGraphType.FETCH)
    Page<User> findAll(Pageable pageable);

    @EntityGraph(value = "User.findAll", type = EntityGraph.EntityGraphType.FETCH)
    Page<User> findAll(@Nullable Specification<User> spec, Pageable pageable);

    @EntityGraph(value = "User.findAll", type = EntityGraph.EntityGraphType.FETCH)
    Page<User> findUsersByTerminalsContainsOrderByRegTime(Terminal terminal,Pageable pageable);

    @EntityGraph(value = "User.findAll", type = EntityGraph.EntityGraphType.FETCH)
    List<User> findAll(@Nullable Specification<User> spec, Sort sort);

    @Query(nativeQuery = true,value = "select u.* from user u left join user_post up on u.id=up.user_id \n" +
            "left join post p on p.id=up.post_id left join department d on d.id=p.department_id where d.id=?1 group by id")
    List<User> findUsersByDepartId(String departId);

    @Query(nativeQuery = true,value = "select u.* from user u left join user_post up on u.id=up.user_id \n" +
            "left join post p on p.id=up.post_id left join role r on r.id=p.role_id where r.id=?1 group by id")
    List<User> findUsersByRoleId(String roleId);

    @Query(nativeQuery = true,value = "select u.* from user u left join user_post up on u.id=up.user_id left join post p on p.id=up.post_id where p.id=?1 group by id")
    List<User> findUsersByPostId(String postId);

    //清空用户集合关联的岗位
    @Modifying
    @Query(nativeQuery = true,value = "delete from user_post where post_id = ?1")
    void clearUsersByPostId(String postId);

    @Modifying
    @Query(nativeQuery = true,value = "delete from user_post where user_id = ?1")
    void clearUsersByUserId(String userId);

    //查询用户终端关联的集合是否存在
    @Query(nativeQuery = true,value = "select count(user_id) from user_terminal where user_id=?1 and terminal_id in ?2")
    int isExistsTerminalsByUser(String userId,List<String> tmIds);

    // 查询用户关联的终端集合
    @Query(nativeQuery = true,value = "select terminal_id from user_terminal where user_id=?1")
    List<String> findTmIdsByUserId(String userId);

    // 批量查询用户关联的终端集合
    @Query(nativeQuery = true,value = "select distinct(terminal_id) from user_terminal where user_id in ?1")
    List<String> findTmIdsByUserIds(List<String> userIds);

    //删除用户终端关联的数据
    @Modifying
    @Query(nativeQuery = true,value = "delete from user_terminal where user_id=?1 and terminal_id=?2")
    void deleteRelateTerminalByUser(String userId,String tmId);

    //查询终端关联的用户集合
    @Query(nativeQuery = true,value = "select user_id from user_terminal where terminal_id=?1")
    List<String> findUserIdsByTmId(String tmId);

    //插入用户终端关联数据
    @Modifying
    @Query(nativeQuery = true,value = "insert into user_terminal(user_id,terminal_id) values(?1,?2)")
    void saveUserTerminal(String userId,String tmId);

    //批量密码重置
    @Transactional
    @Modifying
    @Query(nativeQuery = true,value = "update user set password=?2 where id in ?1")
    void resetPasswordBatch(List<String> userIds,String pwd);

    //批量删除用户
    @Transactional
    @Modifying
    @Query(nativeQuery = true,value = "delete from user where id in ?1")
    void deleteUserBatch(List<String> userIds);

    @Modifying
    @Query(nativeQuery = true,value = "delete from user_post where post_id=?1")
    void deleteUserPostByPostId(String postId);

    @Modifying
    @Query(nativeQuery = true,value = "delete from user_post where user_id=?1")
    void deleteUserPostByUserId(String userId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true,value = "delete from user_post where user_id in ?1")
    void deleteUserPostByUserIds(List<String> userIds);

    @Modifying
    @Query(nativeQuery = true,value = "delete from user_terminal where user_id=?1")
    void deleteUserTerminalByUserId(String userId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true,value = "delete from user_terminal where user_id in ?1")
    void deleteUserTerminalByUserIds(List<String> userIds);

}
