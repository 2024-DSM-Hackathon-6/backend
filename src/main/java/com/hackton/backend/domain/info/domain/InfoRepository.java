package com.hackton.backend.domain.info.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InfoRepository extends CrudRepository<InfoEntity, Long>, CustomInfoRepository {

    List<InfoEntity> findAllByCategoryName(String categoryName);
}
