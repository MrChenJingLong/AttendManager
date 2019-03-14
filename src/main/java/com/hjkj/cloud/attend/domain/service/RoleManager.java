package com.hjkj.cloud.attend.domain.service;

import com.hjkj.cloud.attend.domain.model.Role;
import com.hjkj.cloud.attend.domain.repository.IRoleRepository;
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

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleManager {

    @Autowired
    private IRoleRepository roleRepository;

    public Page<Role> findRoleCriteria(Integer page, Integer size, Role roleQuery) {
        page = page < 0 ? 0 : page;
        size = size == 0 ? Integer.MAX_VALUE : size;
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.ASC, "id");
        if (roleQuery == null) {
            return roleRepository.findAll(pageable);
        }
        return roleRepository.findAll((Specification<Role>) (root, query, cb) -> {
            List<Predicate> list = new ArrayList<Predicate>();
            if(null != roleQuery.getId() && !"".equals(roleQuery.getId())){
                list.add(cb.equal(root.get("id").as(String.class), roleQuery.getId()));
            }
            if(null != roleQuery.getName() && !"".equals(roleQuery.getName())){
                list.add(cb.like(root.get("name").as(String.class), "%" + roleQuery.getName() + "%"));
            }
            Predicate[] p = new Predicate[list.size()];
            return cb.and(list.toArray(p));
        },pageable);
    }

    public void saveRole(Role role) {
        if (StringUtils.isEmpty(role.getId())) {
            role.setId(com.hjkj.cloud.attend.infrastructure.utils.StringUtils.genUUID(8));
        }
        roleRepository.saveAndFlush(role);
    }

    @Transactional
    public void updateRole(Role role) {
        Role oldRole = findRoleById(role.getId());
        if (!StringUtils.isEmpty(role.getName())) {
            oldRole.setName(role.getName());
        }
        oldRole.setSort(role.getSort());
        oldRole.setValue(role.getValue());
        roleRepository.saveAndFlush(oldRole);
    }

    @Transactional
    public void delRoleById(String roleId,boolean isDel) {
        Role role = findRoleById(roleId);
        if (!isDel && role.getPosts().size() > 0) {
            throw new ServiceException(ResultCode.FAILED.code,"该角色包含岗位数据，无法被删除");
        }
        roleRepository.deleteById(roleId);
    }

    @Transactional
    public void delRolesByIds(List<String> roleIdList,boolean isDel) {
        for (String roleId : roleIdList) {
            delRoleById(roleId,isDel);
        }
    }

    public Role findRoleById(String roleId) {
        Optional<Role> role = roleRepository.findById(roleId);
        if (!role.isPresent()) {
            throw new ServiceException(ResultCode.FAILED.code,"角色[" + roleId + "]不存在");
        }
        return role.get();
    }

}
