package com.hjkj.cloud.attend.domain.repository;

import java.util.List;

public interface IBatchRepository<T> {

    void bitchInsert(List<T> list);

    void bitchUpdate(List<T> list);

}
