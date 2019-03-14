package com.hjkj.cloud.attend.application.service;

import com.hjkj.cloud.attend.ui.dto.terminal.DutyDto;
import com.hjkj.cloud.attend.ui.dto.web.QueryDuty;
import org.springframework.data.domain.Page;

public interface AttendService {

    Page<DutyDto> queryDutyInfo(QueryDuty queryDuty);

}
