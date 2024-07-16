package com.hackton.backend.domain.user.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

    boolean existsByAccountId(String accountId);

    Optional<UserEntity> findByAccountId(String accountId);

    Optional<UserEntity> findByIdentifier(String identifier);
}
