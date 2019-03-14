package com.hjkj.cloud.attend.application.service;

import com.hjkj.cloud.attend.domain.model.Duty;
import com.hjkj.cloud.attend.ui.dto.web.ClassesDto;
import org.springframework.data.domain.Page;

public interface IAttendService {

    Page<Duty> queryDutyInfo();

    /**
     * 添加班次信息
     * @param classesDto
     */
    void addClssesDto(ClassesDto classesDto);

}
