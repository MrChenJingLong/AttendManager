package com.hjkj.cloud.attend.domain.service;

import com.hjkj.cloud.attend.domain.model.Department;
import com.hjkj.cloud.attend.domain.repository.IDepartmentRepository;
import com.hjkj.cloud.attend.infrastructure.utils.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableJpaAuditing
public class DepartmentManagerTest {


    @Autowired
    private DepartmentManager departmentManager;

    @Test
    public void saveDepartment() throws Exception {
        Department depart = new Department();
        depart.setId("3da8fee3");
        depart.setName("我的公司");
        depart.setLevel(0);
        depart.setSort(0);
        depart.setFlag(2);
        depart.setIs_private(1);
        departmentManager.saveDepartment(depart);
    }

    @Test
    public void findFullDepartById() throws Exception {
        Department depart = departmentManager.findFullDepartById("7faea816");
        System.out.println(collFullDepartIds(depart));
    }

    private String genFullDepart(Department depart) {
        StringBuilder sb = new StringBuilder();
        sb.append("{name:").append(depart.getName()).append(",children:[");
        if (depart.getChildren() == null || depart.getChildren().size() <= 0) {
            sb.append("]");
        } else {
            depart.getChildren().forEach(child -> {
                sb.append(genFullDepart(child)).append(",");
            });
            sb.deleteCharAt(sb.length() - 1);
        }
        sb.append("}");
        return sb.toString();
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

}