package com.hjkj.cloud.attend.domain.service;

import com.hjkj.cloud.attend.domain.model.*;
import com.hjkj.cloud.attend.domain.repository.*;
import com.hjkj.cloud.attend.infrastructure.utils.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableJpaAuditing
public class InitSQLData {

    @Autowired
    private IDepartmentRepository departmentRepository;
    @Autowired
    private IMenuRepository menuRepository;
    @Autowired
    private MenuManager menuManager;
    @Autowired
    private IRoleRepository roleRepository;
    @Autowired
    private IPostRepository postRepository;
    @Autowired
    private ITerminalRepository terminalRepository;
    @Autowired
    private IUserRepository userRepository;

    @Test
    public void run() {

    }

    @Test
    public void saveDepartment() throws Exception {
        Department depart = new Department();
        depart.setId(StringUtils.genUUID(8));
        depart.setName("我的公司");
        depart.setLevel(0);
        depart.setSort(0);
        depart.setFlag(2);
        depart.setIs_private(1);
        departmentRepository.saveAndFlush(depart);
    }

    private List<Menu> genParentMenus() {
        List<Menu> menuList = new ArrayList<>();
        Menu menu1 = new Menu("我的考勤","/attend","attend",1);
        Menu menu2 = new Menu("考勤管理","/attend_mgr","attend_mgr",3);
        Menu menu3 = new Menu("组织结构","/org","org",4);
        Menu menu4 = new Menu("终端管理","/terminal","terminal",6);
        menuList.add(menu1);
        menuList.add(menu2);
        menuList.add(menu3);
        menuList.add(menu4);
        return menuList;
    }

    private List<Menu> genChildrenMenus() {
        List<Menu> menuList = new ArrayList<>();

        //我的考勤
        Menu menu1 = new Menu("期间考勤","/attend/time","attend_time",1,"29c364ce");
        Menu menu2 = new Menu("考勤日历","/attend/day","attend_day",2,"29c364ce");
        Menu menu3 = new Menu("我的月报","/attend/month","attend_month",3,"29c364ce");
        menuList.add(menu1);
        menuList.add(menu2);
        menuList.add(menu3);

        //考勤管理
        Menu menu4 = new Menu("时段管理","/attend_mgr/clock","clock",1,"1bb0f573");
        Menu menu5 = new Menu("班次管理","/attend_mgr/classes","attend_day",2,"1bb0f573");
        Menu menu6 = new Menu("周期排班","/attend_mgr/schedule","schedule",3,"1bb0f573");
        Menu menu7 = new Menu("排班管理","/attend_mgr/schedule_mgr","schedule_mgr",4,"1bb0f573");
        menuList.add(menu4);
        menuList.add(menu5);
        menuList.add(menu6);
        menuList.add(menu7);

        //组织结构
        Menu menu8 = new Menu("人员管理","/org/user","user",1,"c155e477");
        Menu menu9 = new Menu("部门管理","/org/depart","depart",2,"c155e477");
        Menu menu10 = new Menu("角色管理","/org/role","role",3,"c155e477");
        menuList.add(menu8);
        menuList.add(menu9);
        menuList.add(menu10);
        //终端管理
        Menu menu11 = new Menu("终端设备管理","/terminal/device","device",1,"1772de33");
        Menu menu12 = new Menu("终端升级管理","/terminal/upgrade","upgrade",3,"1772de33");
        Menu menu13 = new Menu("终端参数管理","/terminal/param","param",2,"1772de33");
        menuList.add(menu11);
        menuList.add(menu12);
        menuList.add(menu13);

        return menuList;
    }

    @Test
    public void saveMenus() {
//        menuRepository.saveAll(genParentMenus());
        menuRepository.saveAll(genChildrenMenus());
    }

    @Test
    public void saveRoles() {
        List<Role> roles = new ArrayList<>();
        Role role1 = new Role();
        role1.setId(StringUtils.genUUID(8));
        role1.setName("普通用户");
        role1.setValue(3);

        Role role2 = new Role();
        role2.setId(StringUtils.genUUID(8));
        role2.setName("普通管理员");
        role2.setValue(2);

        Role role3 = new Role();
        role3.setId(StringUtils.genUUID(8));
        role3.setName("超级管理员");
        role3.setValue(1);

        roles.add(role1);
        roles.add(role2);
        roles.add(role3);

        roleRepository.saveAll(roles);

    }

    @Test
    public void savePosts() throws Exception {
        List<Post> postList = new ArrayList<>();
        Post post1 = new Post();
        post1.setId(StringUtils.genUUID(8));
        post1.setName("普通员工");
        post1.setValue(3);
        post1.relateDepartId("f8ee6f86");
        post1.relateRoleId("b21eb8e5");

        Post post2 = new Post();
        post2.setId(StringUtils.genUUID(8));
        post2.setName("经理");
        post2.setValue(2);
        post2.relateDepartId("f8ee6f86");
        post2.relateRoleId("a0b078ae");
//
        Post post3 = new Post();
        post3.setId(StringUtils.genUUID(8));
        post3.setName("老板");
        post3.setValue(1);
        post3.relateDepartId("f8ee6f86");
        post3.relateRoleId("bedb4ec0");

        postList.add(post1);
        postList.add(post2);
        postList.add(post3);

        postRepository.saveAll(postList);

    }

    @Test
    public void saveTerminalInfo() throws Exception {
        Optional<Department> department = departmentRepository.findById("f8ee6f86");
        List<Terminal> terminalList = new ArrayList<>();
        for (int i = 1;i <= 10;i++) {
            Terminal terminal = new Terminal();
            terminal.setId(StringUtils.genUUID(8));
            terminal.setDepartment(department.get());
            terminal.setMacAddr("0000000" + i);
            terminal.setTmAllowNum(1000);
            terminal.setTmCompRecords(0);
            terminal.setTmIp("127.0.0.1");
            terminal.setTmPort(8721);
            terminal.setTmRegnum(0);
            terminal.setTmRegTime(new Date());
            terminal.setTmStrangerRecords(0);
            terminal.setTmTotalCap(100);
            terminal.setTmUsableCap(0);
            terminal.setVersion("V1.1");
            terminal.setAcceptState(1);
            terminal.setTransmitState(1);
            terminalList.add(terminal);
        }
        terminalRepository.saveAll(terminalList);

    }

    @Test
    public void saveUsers() throws Exception {
        List<User> userList = new ArrayList<>();
        for (int i = 1;i <= 1000;i++) {
            User user = new User();
            user.setId(StringUtils.genUUID(8));
            user.setCardNum(10000 + i + "");
            user.setName("su");
            user.setPassword("123456");
            user.setRegTime(new Date());
            user.setAvatar("");
            user.setTemplate("");
            user.setIsAttendancer(1);
            user.setHireDate(new Date());
            user.setWorkState(1);
            user.setBornDate(new Date());
            user.setTerminalRole("1003");
            userList.add(user);
        }
        userRepository.saveAll(userList);
    }

    @Test
    public void allotGrant() {
        List<String> menuIdList = menuRepository.findAllMenuIds();
        menuManager.allotPost(menuIdList,"74c98bd6");
//        Post post = new Post("74c98bd6");
//        Optional<User> user = userRepository.findById("6af8597d");
//        user.get().getPosts().add(post);
//        userRepository.saveAndFlush(user.get());
    }


}

