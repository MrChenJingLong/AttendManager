package com.hjkj.cloud.attend.domain.repository;

import com.hjkj.cloud.attend.domain.model.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Component;

@Component
public interface IHolidayRepository extends JpaRepository<Holiday,String>,JpaSpecificationExecutor<Holiday> {
}
