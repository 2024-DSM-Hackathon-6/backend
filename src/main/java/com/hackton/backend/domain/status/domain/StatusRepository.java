package com.hackton.backend.domain.status.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StatusRepository extends CrudRepository<StatusEntity, Long> {

    List<StatusEntity> findAllByFeedIdAndName(Long feedId, String name);

    List<StatusEntity> findAllByFeedId(Long feedId);
}
