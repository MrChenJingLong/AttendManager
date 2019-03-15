package com.hjkj.cloud.attend.application.service;

import com.hjkj.cloud.attend.domain.model.Duty;
import com.hjkj.cloud.attend.ui.dto.web.ClassesDto;
import com.hjkj.cloud.attend.ui.dto.web.ClockDto;
import com.hjkj.cloud.attend.ui.dto.web.QueryDuty;
import org.springframework.data.domain.Page;

public interface IAttendService {



    Page<Duty> queryDutyInfo(QueryDuty queryDuty);

    /**
     * 添加班次信息
     * @param classesDto
     */
    void addClssesDto(ClassesDto classesDto);

    /**
     * 新建时段信息
     * @param clockDto
     */
    void addClockDto(ClockDto clockDto);


}
