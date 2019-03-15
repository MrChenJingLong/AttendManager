package com.hjkj.cloud.attend.domain.repository;

import com.hjkj.cloud.attend.domain.model.Classes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Component;

@Component
public interface IClassesRepository extends JpaRepository<Classes,String>,JpaSpecificationExecutor<Classes> {


}
