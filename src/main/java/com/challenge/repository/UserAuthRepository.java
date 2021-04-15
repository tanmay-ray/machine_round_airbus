package com.challenge.repository;

import com.challenge.repository.entity.UserAuthEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAuthRepository extends CrudRepository<UserAuthEntity, Long> {
    Optional<UserAuthEntity> findByEmail(String email);
}
