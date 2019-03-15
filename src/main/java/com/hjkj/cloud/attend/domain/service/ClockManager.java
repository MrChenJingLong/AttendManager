package com.hjkj.cloud.attend.domain.service;

import com.hjkj.cloud.attend.application.convert.DtoTransform;
import com.hjkj.cloud.attend.domain.model.ClassClock;
import com.hjkj.cloud.attend.domain.model.Classes;
import com.hjkj.cloud.attend.domain.model.Clock;
import com.hjkj.cloud.attend.domain.repository.IClassClockRepository;
import com.hjkj.cloud.attend.domain.repository.IClassesRepository;
import com.hjkj.cloud.attend.domain.repository.IClockRepository;
import com.hjkj.cloud.attend.infrastructure.constant.enums.ResultCode;
import com.hjkj.cloud.attend.infrastructure.exception.ServiceException;
import com.hjkj.cloud.attend.ui.dto.web.ClassClockDto;
import com.hjkj.cloud.attend.ui.dto.web.ClassesDto;
import com.hjkj.cloud.attend.ui.dto.web.ClockDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ClockManager {

    @Autowired
    IClassesRepository iClassesRepository;

    @Autowired
    IClockRepository iClockRepository;

    @Autowired
    IClassClockRepository iClassClockRepository;


    /**
     * 添加保存班次信息
     * @param classesDto
     */
    public void addClasses(ClassesDto classesDto){
        Classes classes = null;
        String class_id = null;
        //判断若不写带ID则为进行班次新增
        if (StringUtils.isEmpty(classesDto.getAtt_cla_id())) {
            classes = DtoTransform.copyOfClassesDto(classesDto);
            classes.setId(com.hjkj.cloud.attend.infrastructure.utils.StringUtils.genUUID(8));
            Classes claRet = iClassesRepository.saveAndFlush(classes);
            class_id = claRet.getId();
        }else{
            //获取现有班次信息
            Optional<Classes> claPresent = iClassesRepository.findById(classesDto.getAtt_cla_id());
            if(!claPresent.isPresent()){
                throw new ServiceException(ResultCode.FAILED.code,"班次[" + classesDto.getAtt_cla_id() + "]不存在");
            }
            classes = claPresent.get();
            //若有数据且有效则更新修改的值
            if(null != classesDto.getAtt_cla_name()){
                classes.setName(classesDto.getAtt_cla_name());
            }
            if(classesDto.getAtt_cla_isElastic() > 0){
                classes.setIsElastic(classesDto.getAtt_cla_isElastic());
            }
            if(classesDto.getAtt_cla_totalHours() > 0){
                classes.setTotalHours(classesDto.getAtt_cla_totalHours());
            }
            //有效班次ID
            class_id = claPresent.get().getId();
            iClassesRepository.saveAndFlush(classes);
        }

        //如果携带时段信息则添加时段信息
        if(null != classesDto.getClaCloDtosList() && classesDto.getClaCloDtosList().size() > 0){
            ArrayList<ClassClockDto> claCloList = classesDto.getClaCloDtosList();
            for (ClassClockDto claClo:claCloList) {
                claClo.setCla_id(class_id);
                ClassClock classClock = DtoTransform.copyOfClassClockDto(claClo);
                classClock.setId(com.hjkj.cloud.attend.infrastructure.utils.StringUtils.genUUID(8));
                iClassClockRepository.saveAndFlush(classClock);
            }

        }
    }

    /**
     * 添加或者修改时段信息
     * @param clockDto
     */
    public void addOrUpdateClock(ClockDto clockDto){
        Clock clock = null;
        //获取是否更新时段信息
        if(null != clockDto.getClo_id()){
            Optional<Clock> clockPresent = iClockRepository.findById(clockDto.getClo_id());
            if(!clockPresent.isPresent()){
                throw new ServiceException(ResultCode.FAILED.code,"时段[" + clockDto.getClo_id() + "]不存在");
            }
            clock = clockPresent.get();
            clock.setName(clockDto.getClo_name());
            clock.setSort(clockDto.getClo_sort());
        }else{//执行新建时段信息
            clock = new Clock(com.hjkj.cloud.attend.infrastructure.utils.StringUtils.genUUID(8));
            clock.setName(clockDto.getClo_name());
            clock.setSort(clockDto.getClo_sort());
        }

        iClockRepository.saveAndFlush(clock);

    }


    

}
