package com.hjkj.cloud.attend.domain.service;

import com.hjkj.cloud.attend.domain.model.Department;
import com.hjkj.cloud.attend.domain.model.Post;
import com.hjkj.cloud.attend.domain.model.User;
import com.hjkj.cloud.attend.domain.repository.IDepartmentRepository;
import com.hjkj.cloud.attend.infrastructure.constant.enums.ResultCode;
import com.hjkj.cloud.attend.infrastructure.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.*;

@Service
public class DepartmentManager {

    @Autowired
    private IDepartmentRepository departmentRepository;

    public void saveDepartment(Department department) {
        if (StringUtils.isEmpty(department.getId())) {
            department.setId(com.hjkj.cloud.attend.infrastructure.utils.StringUtils.genUUID(8));
        }
        if (department.getParentDepartment() != null) {
            Optional<Department> parentDepart = departmentRepository.findById(department.getParentDepartment().getId());
            parentDepart.ifPresent(department::setParentDepartment);
        }
        departmentRepository.saveAndFlush(department);
    }

    public Page<Department> findDepartCriteria(Integer page, Integer size, Department departQuery) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.ASC, "id");
        if (departQuery == null) {
            return departmentRepository.findAll(pageable);
        }
        return departmentRepository.findAll((Specification<Department>) (root, query, cb) -> {
            List<Predicate> list = new ArrayList<Predicate>();
            if(null != departQuery.getName() && !"".equals(departQuery.getName())){
                list.add(cb.like(root.get("name").as(String.class), "%" + departQuery.getName() + "%"));
            }
            Predicate[] p = new Predicate[list.size()];
            return cb.and(list.toArray(p));
        },pageable);
    }

    @Transactional
    public Department findFullDepartById(String departId) {
        Department department = findDepartById(departId);
        department.setChildren(findDepartChildrens(department.getId()));
        return department;
    }

    private List<String> collFullDepartIds(Department depart) {
        List<String> departmentList = new ArrayList<>();
        departmentList.add(depart.getId());
        if (depart.getChildren() == null || depart.getChildren().size() <= 0) {
            return departmentList;
        }
        depart.getChildren().forEach(child -> {
            departmentList.addAll(collFullDepartIds(child));
        });
        return departmentList;
    }

    public List<String> containsDepartIds(String departId) {
        if (StringUtils.isEmpty(departId)) {
            List<Department> departments = departmentRepository.findAll();
            List<String> departIds = new ArrayList<>();
            departments.forEach(department -> {
                departIds.add(department.getId());
            });
            return departIds;
        }
        Department department = findFullDepartById(departId);
        return collFullDepartIds(department);
    }

    public List<Department> findDepartChildrens(String departId) {
        List<Department> children = departmentRepository.findDepartmentByParentDepartment_Id(departId);
        if (children == null) {
            return Collections.emptyList();
        }
        children.forEach(department -> {
            department.setChildren(findDepartChildrens(department.getId()));
        });
        return children;
    }

    @Transactional
    public void deleteFullDepart(String departId,boolean isDel) {
        List<Department> children = departmentRepository.findDepartmentByParentDepartment_Id(departId);
        if (children == null || children.size() <= 0) {
            deleteDepartById(departId,false);
            return;
        }
        children.forEach(department -> {
            deleteFullDepart(department.getId(),isDel);
            deleteDepartById(department.getId(),isDel);
        });
        deleteDepartById(departId,false);
    }

    @Transactional
    public void deleteDepartById(String departId,boolean isDel) {
        Department department = findDepartById(departId);
        if (!isDel) {
            if (department.getPosts().size() > 0) {
                throw new ServiceException(ResultCode.FAILED.code,"该部门包含岗位数据，无法被删除");
            }
            if (department.getTerminals().size() > 0) {
                throw new ServiceException(ResultCode.FAILED.code,"该部门包含终端数据，无法被删除");
            }
        }
        departmentRepository.deleteById(departId);
    }

    @Transactional
    public void updateDepartment(Department department) {
        Department oldDepart = findDepartById(department.getId());
        oldDepart.setLevel(department.getLevel());
        oldDepart.setIs_private(department.getIs_private());
        oldDepart.setSort(department.getSort());
        oldDepart.setFlag(department.getFlag());
        if (!StringUtils.isEmpty(department.getName())) {
            oldDepart.setName(department.getName());
        }
        if (!StringUtils.isEmpty(department.getTag())) {
            oldDepart.setTag(department.getTag());
        }
        if (!StringUtils.isEmpty(department.getParentDepartment())) {
            oldDepart.setParentDepartment(department.getParentDepartment());
        }
        departmentRepository.saveAndFlush(oldDepart);
    }

    public Department findDepartById(String departId) {
        Optional<Department> department = departmentRepository.findById(departId);
        return department.orElseThrow(() -> new ServiceException(ResultCode.FAILED.code,"找不到部门[" + departId + "]"));
    }

}
