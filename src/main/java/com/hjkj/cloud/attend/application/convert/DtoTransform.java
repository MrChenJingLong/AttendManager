package com.hjkj.cloud.attend.application.convert;

import com.hjkj.cloud.attend.domain.model.*;
import com.hjkj.cloud.attend.infrastructure.constant.Constant;
import com.hjkj.cloud.attend.infrastructure.constant.enums.ResultCode;
import com.hjkj.cloud.attend.infrastructure.exception.ServiceException;
import com.hjkj.cloud.attend.ui.dto.terminal.DutyDto;
import com.hjkj.cloud.attend.ui.dto.terminal.TerminalDto;
import com.hjkj.cloud.attend.ui.dto.terminal.UserDto;
import com.hjkj.cloud.attend.ui.dto.web.*;

import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.util.Date;

public class DtoTransform {

    public static Terminal copyOfTerminalDto(@NotNull TerminalDto terminalDto) {
        Terminal terminal = new Terminal();
        try {
            terminal.setId(terminalDto.getId());
            if (null != terminalDto.getDepart_id() && !"".equals(terminalDto.getDepart_id())) {
                terminal.relateDepartId(terminalDto.getDepart_id());
            }
            terminal.setName(terminalDto.getName());
            terminal.setOnlineState(terminalDto.getStatus());
            terminal.setMacAddr(terminalDto.getMac_addr());
            terminal.setTmAllowNum(terminalDto.getAllow_user_num());
            terminal.setTmCompRecords(terminalDto.getComp_records());
            terminal.setTmIp(terminalDto.getIp());
            terminal.setTmPort(terminalDto.getPort());
            terminal.setTmRegnum(terminalDto.getReg_user_num());
            if (null != terminalDto.getReg_time() && !"".equals(terminalDto.getReg_time())) {
                terminal.setTmRegTime(Constant.SIMPLE_DATE_FORMAT.parse(terminalDto.getReg_time()));
            } else {
                terminal.setTmRegTime(new Date());
            }
            terminal.setTmStrangerRecords(terminalDto.getStra_records());
            terminal.setTmTotalCap(terminalDto.getTotal_cap());
            terminal.setTmUsableCap(terminalDto.getUsag_cap());
            terminal.setVersion(terminalDto.getVersion());
            terminal.setAcceptState(terminalDto.isAutoAccept() ? 1 : 0);
            terminal.setTransmitState(terminalDto.isAutoTrans() ? 1 : 0);
        } catch (ParseException e) {
            throw new ServiceException(ResultCode.FAILED.code,"日志格式转换出错");
        }
        return terminal;
    }

    public static User copyOfUserDto(@NotNull UserDto userDto) {
        User user = new User();
        try {
            user.setId(userDto.getUser_id());
            user.setCardNum(userDto.getCard_num());
            user.setName(userDto.getUser_name());
            user.setSex(userDto.getUser_sex());
            user.setIcNo(userDto.getIc_card());
            user.setPassword(userDto.getPassword());
            user.setTerminalRole(userDto.getRole());
            if (userDto.getReg_time() != null && !"".equals(userDto.getReg_time())) {
                user.setRegTime(Constant.SIMPLE_DATE_FORMAT.parse(userDto.getReg_time()));
            }
            user.setAvatar(userDto.getUser_img());
            user.setTemplate(userDto.getUser_template());
            if (userDto.getAgent() != null) {
                user.setAgent(copyOfUserDto(userDto.getAgent()));
            }
            user.setPost_id(userDto.getPost_id());
            user.setDepart_id(userDto.getDepart_id());
            user.setRole_id(userDto.getRole_id());
            user.setBornDate(userDto.getBorn_date());
            user.setWorkState(userDto.getWork_state());
            user.setLeaveDate(userDto.getLeave_date());
            user.setHireDate(userDto.getHire_date());
            user.setIsAttendancer(userDto.getIs_attendancer());
            user.setReg_state(userDto.getReg_state());

        } catch (ParseException e) {
            throw new ServiceException(ResultCode.FAILED.code,"日志格式转换出错");
        }
        return user;
    }

    public static User copyOfAccount(@NotNull Account account) {
        User user = new User();

        user.setCardNum(account.getUsername());
        user.setPassword(account.getPassword());

        return user;
    }

    public static Role copyOfRoleDto(@NotNull RoleDto roleDto) {
        Role role = new Role();
        role.setId(roleDto.getRole_id());
        role.setName(roleDto.getRole_name());
        role.setValue(roleDto.getRole_value());
        role.setSort(roleDto.getRole_sort());
        return role;
    }

    public static Post copyOfPostDto(PostDto postDto) {
        Post post = new Post();
        if (postDto == null) { return post;}
        post.setId(postDto.getPost_id());
        if (postDto.getDepart_id() != null && !"".equals(postDto.getDepart_id())) {
            post.relateDepartId(postDto.getDepart_id());
        }
        if (postDto.getRole_id() != null && !"".equals(postDto.getRole_id())) {
            post.relateRoleId(postDto.getRole_id());
        }
        post.setName(postDto.getPost_name());
        post.setValue(postDto.getPost_value());
        post.setFlag(postDto.getPost_flag());
        post.setSort(postDto.getPost_sort());
        post.setTag(postDto.getPost_tag());
        return post;
    }

    public static Department copyOfDepartmentDto(@NotNull DepartDto departDto) {
        Department depart = new Department();
        if (departDto == null) {
            return depart;
        }
        depart.setId(departDto.getDepart_id());
        if (departDto.getParent_id() != null && !"".equals(departDto.getParent_id())) {
            depart.relateParentId(departDto.getParent_id());
        }
        depart.setName(departDto.getDepart_name());
        depart.setFlag(departDto.getFlag());
        depart.setIs_private(departDto.getIs_private());
        depart.setLevel(departDto.getLevel());
        depart.setSort(departDto.getSort());
        depart.setTag(departDto.getTag());
        return depart;
    }

    public static Duty copyOfDutyDto(@NotNull DutyDto dutyDto) {
        Duty duty = new Duty();
        duty.setId(dutyDto.getDuty_id());
        duty.setDate(new Date());
        duty.setTime(dutyDto.getAttend_time());
        duty.setCompareScore(dutyDto.getCompare_score());
        return duty;
    }

    public static Menu copyOfMenuDto(@NotNull MenuDto menuDto) {
        Menu menu = new Menu();
        if (menuDto == null) {
            return menu;
        }
        menu.setId(menuDto.getMenu_id());
        if (menuDto.getParent_id() != null && !"".equals(menuDto.getParent_id())) {
            menu.relateParentId(menuDto.getParent_id());
        }
        menu.setUrl(menuDto.getUrl());
        menu.setUserLimit(menuDto.getUser_limit());
        menu.setType(menuDto.getType());
        menu.setSort(menuDto.getSort());
        menu.setName(menuDto.getName());
        menu.setIcon(menuDto.getIcon());
        menu.setRef(menuDto.getRef());
        return menu;
    }

    /**
     * 班次开放实体转换为数据库实体类
     * @param classesDto
     * @return
     */
    public static Classes copyOfClassesDto(@NotNull ClassesDto classesDto){
        Classes classes = new Classes();
        if (classesDto == null) {
            return null;
        }
        try {
            if(null != classesDto.getAtt_cla_name()){
                classes.setName(classesDto.getAtt_cla_name());
            }
            if(classesDto.getAtt_cla_isElastic() > 0){
                classes.setIsElastic(classesDto.getAtt_cla_isElastic());
            }
            if(classesDto.getAtt_cla_totalHours() > 0){
                classes.setTotalHours(classesDto.getAtt_cla_totalHours());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return classes;
    }
    /**
     * 班次开放实体转换为班次时段数据库实体类
     * @param classClockDto
     * @return
     */
    public static ClassClock copyOfClassClockDto(@NotNull ClassClockDto classClockDto){
        ClassClock classClock = new ClassClock();
        if (classClockDto == null) {
            return null;
        }
        try {
            classClock.setAllowEarlyTime(classClockDto.getAllowEarlyTime());
            classClock.setAllowLateTime(classClockDto.getAllowLateTime());
            classClock.setOffWorkClockDate(classClockDto.getOffWorkClockDate());
            classClock.setOffWorkClockState(classClockDto.getOffWorkClockState());
            classClock.setOffWorkDate(classClockDto.getOffWorkDate());
            classClock.setOnWorkClockDate(classClockDto.getOnWorkClockDate());
            classClock.setOnWorkClockState(classClockDto.getOnWorkClockState());
            classClock.setOnWorkDate(classClockDto.getOnWorkDate());
            classClock.setClasses(new Classes(classClockDto.getCla_id()));
            classClock.setClock(new Clock(classClockDto.getClo_id()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return classClock;
    }

}
