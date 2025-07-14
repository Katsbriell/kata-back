package com.app.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.backend.model.OnbEntity;

@Repository
public interface OnbRepository extends JpaRepository<OnbEntity, Long> {
}
