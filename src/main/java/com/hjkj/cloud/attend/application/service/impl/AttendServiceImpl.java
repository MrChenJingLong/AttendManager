package com.hjkj.cloud.attend.application.service.impl;

import com.hjkj.cloud.attend.application.convert.DomainAssemble;
import com.hjkj.cloud.attend.application.convert.DtoTransform;
import com.hjkj.cloud.attend.application.service.IAttendService;
import com.hjkj.cloud.attend.domain.model.Duty;
import com.hjkj.cloud.attend.domain.service.ClockManager;
import com.hjkj.cloud.attend.domain.service.DutyManager;
import com.hjkj.cloud.attend.ui.dto.terminal.DutyDto;
import com.hjkj.cloud.attend.ui.dto.web.ClassesDto;
import com.hjkj.cloud.attend.ui.dto.web.ClockDto;
import com.hjkj.cloud.attend.ui.dto.web.QueryDuty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class AttendServiceImpl implements IAttendService {


    @Autowired
    private ClockManager clockManager;

    @Autowired
    private DutyManager dutyManager;

    @Override
    public Page<DutyDto> queryDutyInfo(QueryDuty queryDuty) {
        Page<Duty> dutyPage = dutyManager.findDutyCriteria(queryDuty.getPage(), queryDuty.getSize(),
                DtoTransform.copyOfQueryDuty(queryDuty));
        return DomainAssemble.copyOfDutyPage(dutyPage);
    }

    @Override
    public void addClssesDto(ClassesDto classesDto) {
        clockManager.addClasses(classesDto);
    }

    @Override
    public void addClockDto(ClockDto clockDto) {
        clockManager.addOrUpdateClock(clockDto);
    }
}
