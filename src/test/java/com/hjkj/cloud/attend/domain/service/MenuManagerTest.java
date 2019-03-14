package com.hjkj.cloud.attend.domain.service;

import com.hjkj.cloud.attend.domain.model.Menu;
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
public class MenuManagerTest {

    @Autowired
    private MenuManager menuManager;

    @Test
    @Transactional
    public void saveMenu() throws Exception {
        Menu menu = new Menu();
//        menu.setId(StringUtils.genUUID(8));
//        menu.setName("考勤管理");
//        menu.setUrl("/attend_mgr");
//        menu.setSort(5);
//        menu.setUserLimit(1);
//        menu.setType(1);
//        menuManager.saveMenu(menu);

//        menu = new Menu();
//        menu.setId(StringUtils.genUUID(8));
//        menu.relateParentId("2c59e67f");
//        menu.setName("班次管理");
//        menu.setUrl("/attend_mgr/classes");
//        menu.setSort(1);
//        menu.setUserLimit(1);
//        menu.setType(11);
//        menuManager.saveMenu(menu);
//
        menu = new Menu();
        menu.setId(StringUtils.genUUID(8));
        menu.relateParentId("2c59e67f");
        menu.setName("时段管理");
        menu.setUrl("/attend_mgr/clock");
        menu.setSort(2);
        menu.setUserLimit(1);
        menu.setType(11);
        menuManager.saveMenu(menu);
//
//        menu = new Menu();
//        menu.setId(StringUtils.genUUID(8));
//        menu.relateParentId("2c59e67f");
//        menu.setName("智能排班");
//        menu.setUrl("/attend_mgr/schedule");
//        menu.setSort(3);
//        menu.setUserLimit(1);
//        menu.setType(11);
//        menuManager.saveMenu(menu);

    }

    @Test
    public void deleteMenuById() throws Exception {
        menuManager.deleteMenuById("db8d06ae");
    }

    @Test
    public void allotPost() throws Exception {
        List<String> menuIdList = new ArrayList<>();
        menuIdList.add("8d62eea3");
        menuIdList.add("0fdd7dff");
        menuIdList.add("3f460682");
        menuIdList.add("aebb90e9");

        menuIdList.add("0a8e5e2b");
        menuIdList.add("91f227b1");
        menuIdList.add("30df1fc8");
        menuIdList.add("4c6e75dd");

        menuIdList.add("c0b2dbc5");
        menuIdList.add("51325569");
        menuIdList.add("890aaa2d");
        menuIdList.add("fb2b692c");
        menuManager.allotPost(menuIdList,"2e7edf36");
    }

}