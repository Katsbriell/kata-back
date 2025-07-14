package com.app.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.backend.model.UsersEntity;

@Repository
public interface UsersRepository extends JpaRepository<UsersEntity, Long> {
    boolean existsByEmail(String email);
}
