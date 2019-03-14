package com.hjkj.cloud.attend.domain.repository;

import com.hjkj.cloud.attend.domain.model.BlendSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Component;

@Component
public interface IBlendScheduleRepository extends JpaRepository<BlendSchedule,String>,JpaSpecificationExecutor<BlendSchedule> {
}
