package com.hjkj.cloud.attend.infrastructure.mysql.repositoryImpl;

import com.hjkj.cloud.attend.domain.model.AbstractEntity;
import com.hjkj.cloud.attend.domain.repository.IBatchRepository;
import com.hjkj.cloud.attend.infrastructure.constant.enums.ResultCode;
import com.hjkj.cloud.attend.infrastructure.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public abstract class AbstractBatchRepositoryImpl<U extends AbstractEntity> implements IBatchRepository {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void bitchInsert(List list) {
        if (list == null) {
            throw new ServiceException(ResultCode.FAILED.code,"list is empty!!!");
        }
        for (int i = 0;i < list.size();i++) {
            em.persist(list.get(i));
            if (i % 30 == 0) {
                em.flush();
                em.clear();
            }
        }
        logger.info("save to DB success,list is {}",list.toString());
    }

    @Override
    @Transactional
    public void bitchUpdate(List list) {
        if (list == null) {
            throw new ServiceException(ResultCode.FAILED.code,"list is empty!!!");
        }
        for (int i = 0;i < list.size();i++) {
            em.merge(list.get(i));
            if (i % 30 == 0) {
                em.flush();
                em.clear();
            }
        }
        logger.info("update data success,list is {}",list.toString());
    }
}
