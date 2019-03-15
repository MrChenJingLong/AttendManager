package com.hjkj.cloud.attend.domain.repository;

import com.hjkj.cloud.attend.domain.model.ClassClock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Auther: CHEN
 * @Date: 2019/3/15 16:44
 * @Description:
 */
public interface IClassClockRepository  extends JpaRepository<ClassClock,String>, JpaSpecificationExecutor<ClassClock> {
}
