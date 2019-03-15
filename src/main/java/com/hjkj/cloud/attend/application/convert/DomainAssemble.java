package com.hjkj.cloud.attend.application.convert;

import com.hjkj.cloud.attend.domain.model.*;
import com.hjkj.cloud.attend.infrastructure.constant.Constant;
import com.hjkj.cloud.attend.ui.dto.terminal.DutyDto;
import com.hjkj.cloud.attend.ui.dto.terminal.TerminalDto;
import com.hjkj.cloud.attend.ui.dto.terminal.UserDto;
import com.hjkj.cloud.attend.ui.dto.web.DepartDto;
import com.hjkj.cloud.attend.ui.dto.web.MenuDto;
import com.hjkj.cloud.attend.ui.dto.web.PostDto;
import com.hjkj.cloud.attend.ui.dto.web.RoleDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class DomainAssemble {

    public static Page<RoleDto> copyOfRolePage(Page<Role> rolePage) {
        List<Role> roleList = rolePage.getContent();
        List<RoleDto> roleDtoList = new ArrayList<>();
        RoleDto roleDto;
        for (Role role : roleList) {
            roleDto = copyOfRole(role);
            roleDtoList.add(roleDto);
        }
        return new PageImpl<>(roleDtoList,rolePage.getPageable(),rolePage.getTotalElements());
    }

    public static RoleDto copyOfRole(Role role) {
        RoleDto roleDto = new RoleDto();
        roleDto.setRole_id(role.getId());
        roleDto.setRole_name(role.getName());
        roleDto.setRole_value(role.getValue());
        roleDto.setRole_sort(role.getSort());
        return roleDto;
    }

    public static List<RoleDto> copyOfRoleList(List<Role> roleList) {
        List<RoleDto> roleDtoList = new ArrayList<>();
        if (roleList == null) {
            return roleDtoList;
        }
        roleList.forEach(role -> roleDtoList.add(copyOfRole(role)));
        return roleDtoList;
    }


    public static UserDto copyOfUser(User user) {
        UserDto userDto = copyOfSimpleUser(user);
        userDto.setUser_img(user.getAvatar());
        userDto.setUser_template(user.getTemplate());
        return userDto;
    }

    public static UserDto copyOfSimpleUser(User user) {
        UserDto userDto = new UserDto();
        userDto.setUser_id(user.getId());
        userDto.setCard_num(user.getCardNum());
        userDto.setIc_card(user.getIcNo());
        userDto.setPassword(user.getPassword());
        if (user.getRegTime() != null) {
            userDto.setReg_time(Constant.SIMPLE_DATE_FORMAT.format(user.getRegTime()));
        }
        userDto.setBorn_date(user.getBornDate());
        userDto.setHire_date(user.getHireDate());
        userDto.setRole(user.getTerminalRole());
        userDto.setUser_name(user.getName());
        if (user.getAgent() != null) {
            UserDto agent = new UserDto();
            agent.setUser_id(user.getAgent().getId());
            agent.setCard_num(user.getAgent().getCardNum());
            agent.setUser_name(user.getAgent().getName());
            userDto.setAgent(agent);
        }
        userDto.setWork_state(user.getWorkState());
        userDto.setIs_attendancer(user.getIsAttendancer());
        userDto.setUser_sex(user.getSex());
        return userDto;
    }

    public static List<UserDto> copyOfSimpleUserList(List<User> userList) {
        List<UserDto> userDtoList = new ArrayList<>();
        if (userList == null || userList.size() <= 0) {
            return userDtoList;
        }
        userList.forEach(user -> {
            userDtoList.add(copyOfSimpleUser(user));
        });
        return userDtoList;
    }

    public static Page<UserDto> copyOfUserPage(Page<User> userPage) {
        List<User> userList = userPage.getContent();
        List<UserDto> userDtoList = new ArrayList<>();
        for (User user : userList) {
            UserDto userDto = copyOfSimpleUser(user);
            userDto.setUser_img(user.getAvatar());
            if (user.getTerminals() != null && user.getTerminals().size() > 0) {
                userDto.setReg_state(1);
            } else {
                userDto.setReg_state(0);
            }
            userDtoList.add(userDto);
        }
        return new PageImpl<>(userDtoList,userPage.getPageable(),userPage.getTotalElements());
    }


    public static DepartDto copyOfDepartment(Department department) {
        DepartDto departDto = new DepartDto();
        departDto.setDepart_id(department.getId());
        departDto.setDepart_name(department.getName());
        departDto.setTag(department.getTag());
        departDto.setFlag(department.getFlag());
        departDto.setIs_private(department.getIs_private());
        departDto.setLevel(department.getLevel());
        departDto.setSort(department.getSort());
        if (department.getParentDepartment() != null) {
            departDto.setParent_id(department.getParentDepartment().getId());
        }
        return departDto;
    }

    public static Page<DepartDto> copyOfDepartPage(Page<Department> departPage) {
        List<Department> departmentList = departPage.getContent();
        List<DepartDto> departDtoList = new ArrayList<>();
        departmentList.forEach(department -> departDtoList.add(copyOfDepartment(department)));
        return new PageImpl<>(departDtoList,departPage.getPageable(),departPage.getTotalElements());
    }

    public static List<DepartDto> copyOfDepartments(List<Department> departList) {
        List<DepartDto> departDtoList = new ArrayList<>();
        if (departList == null || departList.size() <= 0) {
            return departDtoList;
        }
        departList.forEach((depart -> departDtoList.add(copyOfDepartment(depart))));
        return departDtoList;
    }

    public static MenuDto copyOfMenu(Menu menu) {
        MenuDto menuDto = new MenuDto();
        menuDto.setMenu_id(menu.getId());
        if (!StringUtils.isEmpty(menu.getParentMenu())) {
            menuDto.setParent_id(menu.getParentMenu().getId());
        }
        menuDto.setName(menu.getName());
        menuDto.setIcon(menu.getIcon());
        menuDto.setRef(menu.getRef());
        menuDto.setSort(menu.getSort());
        menuDto.setType(menu.getType());
        menuDto.setUrl(menu.getUrl());
        menuDto.setType(menu.getType());
        menuDto.setUser_limit(menu.getUserLimit());
        menuDto.setHidden(menu.isHidden());
        return menuDto;
    }

    public static List<MenuDto> copyOfMenuList(List<Menu> menuList) {
        List<MenuDto> menuDtoList = new ArrayList<>();
        if (menuList == null || menuList.size() <= 0) {
            return menuDtoList;
        }
        for (Menu menu : menuList) {
            menuDtoList.add(copyOfMenu(menu));
        }
        return menuDtoList;
    }

    public static Page<MenuDto> copyOfMenuPage(Page<Menu> menuPage) {
        List<Menu> menuList = menuPage.getContent();
        List<MenuDto> menuDtoList = new ArrayList<>();
        MenuDto menuDto;
        for (Menu menu : menuList) {
            menuDto = copyOfMenu(menu);
            menuDtoList.add(menuDto);
        }
        return new PageImpl<>(menuDtoList);
    }

    public static String genFullDepartName(Department department) {
        String departName = department.getName();

        if (department.getParentDepartment() != null) {
            departName = genFullDepartName(department.getParentDepartment()) + " > " + departName;
        }

        return departName;
    }

    public static PostDto copyOfPost(Post post) {
        PostDto postDto = new PostDto();
        postDto.setPost_id(post.getId());
        postDto.setPost_name(post.getName());
        postDto.setPost_flag(post.getFlag());
        postDto.setPost_sort(post.getSort());
        postDto.setPost_tag(post.getTag());
        postDto.setPost_value(post.getValue());
        if (post.getDepartment() != null) {
            postDto.setDepart_id(post.getDepartment().getId());
            postDto.setDepart_name(genFullDepartName(post.getDepartment()));
        }
        if (post.getRole() != null) {
            postDto.setRole_id(post.getRole().getId());
            postDto.setRole_name(post.getRole().getName());
        }

        return postDto;
    }

    public static List<PostDto> copyOfPostList(List<Post> postList) {
        List<PostDto> postDtoList = new ArrayList<>();
        if (postList == null) {
            return postDtoList;
        }
        postList.forEach(post -> {
            postDtoList.add(copyOfPost(post));
        });
        return postDtoList;

    }

    public static Page<PostDto> copyOfPostPage(Page<Post> postPage) {
        List<Post> postList = postPage.getContent();
        List<PostDto> postDtoList = new ArrayList<>();
        PostDto postDto;
        for (Post post : postList) {
            postDto = copyOfPost(post);
            postDtoList.add(postDto);
        }
        return new PageImpl<>(postDtoList,postPage.getPageable(),postPage.getTotalElements());
    }

    public static TerminalDto copyOfTerminal(Terminal terminal) {
        TerminalDto terminalDto = new TerminalDto();
        terminalDto.setId(terminal.getId());
        terminalDto.setName(terminal.getName());
        if (terminal.getDepartment() != null) {
            terminalDto.setDepart_id(terminal.getDepartment().getId());
            terminalDto.setDepart_name(terminal.getDepartment().getName());
        }
        terminalDto.setStatus(terminal.getOnlineState());
        terminalDto.setMac_addr(terminal.getMacAddr());
        terminalDto.setIp(terminal.getTmIp());
        terminalDto.setPort(terminal.getTmPort());
        terminalDto.setAllow_user_num(terminal.getTmAllowNum());
        terminalDto.setComp_records(terminal.getTmCompRecords());
        if (!StringUtils.isEmpty(terminal.getTmRegTime())) {
            terminalDto.setReg_time(Constant.SIMPLE_DATE_FORMAT.format(terminal.getTmRegTime()));
        }
        terminalDto.setReg_user_num(terminal.getTmRegnum());
        terminalDto.setStra_records(terminal.getTmStrangerRecords());
        terminalDto.setTotal_cap(terminal.getTmTotalCap());
        terminalDto.setUsag_cap(terminal.getTmUsableCap());
        terminalDto.setVersion(terminal.getVersion());
        terminalDto.setAutoAccept(terminal.getAcceptState() == 1);
        terminalDto.setAutoTrans(terminal.getTransmitState() == 1);
        return terminalDto;
    }

    public static List<TerminalDto> copyOfTerminalList(List<Terminal> terminalList) {
        List<TerminalDto> terminalDtoList = new ArrayList<>();
        if (terminalList == null) {
            return terminalDtoList;
        }
        terminalList.forEach(terminal -> {
            TerminalDto terminalDto = copyOfTerminal(terminal);
            terminalDtoList.add(terminalDto);
        });
        return terminalDtoList;
    }

    public static Page<TerminalDto> copyOfTerminalPage(Page<Terminal> terminalPage) {
        List<Terminal> terminalList = terminalPage.getContent();
        List<TerminalDto> terminalDtoList = new ArrayList<>();
        terminalList.forEach(terminal ->{
            TerminalDto terminalDto = copyOfTerminal(terminal);
            terminalDtoList.add(terminalDto);
        });
        return new PageImpl<>(terminalDtoList,terminalPage.getPageable(),terminalPage.getTotalElements());
    }

    public static DutyDto copyOfDuty(Duty duty) {
        DutyDto dutyDto = new DutyDto();
        dutyDto.setDuty_id(duty.getId());
        if (null != duty.getDate()) {
            String strDate = Constant.SIMPLE_DATE_FORMAT.format(duty.getDate());
            String[] split = strDate.split(" ");
            if (split.length == 2) {
                dutyDto.setAttend_date(split[0]);
                dutyDto.setAttend_time(split[1]);
            }
        }
        dutyDto.setCompare_score(duty.getCompareScore());
        if (null != duty.getUser()) {
            dutyDto.setUser_id(duty.getUser().getId());
            dutyDto.setUser_name(duty.getUser().getName());
        }
        if (null != duty.getTerminal()) {
            dutyDto.setTm_id(duty.getTerminal().getId());
            dutyDto.setTm_name(duty.getTerminal().getName());
            dutyDto.setMac_addr(duty.getTerminal().getMacAddr());
        }
        return dutyDto;
    }

    public static Page<DutyDto> copyOfDutyPage(Page<Duty> dutyPage) {
        List<Duty> dutyList = dutyPage.getContent();
        List<DutyDto> dutyDtoList = new ArrayList<>();
        DutyDto dutyDto;
        for (Duty duty : dutyList) {
            dutyDto = copyOfDuty(duty);
            dutyDtoList.add(dutyDto);
        }
        return new PageImpl<>(dutyDtoList,dutyPage.getPageable(),dutyPage.getTotalElements());
    }




}
