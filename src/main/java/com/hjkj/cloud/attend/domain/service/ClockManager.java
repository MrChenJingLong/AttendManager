package com.hjkj.cloud.attend.domain.service;

import com.hjkj.cloud.attend.application.convert.DtoTransform;
import com.hjkj.cloud.attend.domain.model.Classes;
import com.hjkj.cloud.attend.domain.repository.IClassesRepository;
import com.hjkj.cloud.attend.ui.dto.web.ClassesDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ClockManager {

    @Autowired
    IClassesRepository iClassesRepository;

    /**
     * 添加保存班次信息
     * @param classesDto
     */
    public void addClasses(ClassesDto classesDto){
        Classes classes = DtoTransform.copyOfClassesDto(classesDto);
        if (StringUtils.isEmpty(classes.getId())) {
            classes.setId("72a836a3");
        }
        Classes claRet = iClassesRepository.saveAndFlush(classes);
    }


    

}
