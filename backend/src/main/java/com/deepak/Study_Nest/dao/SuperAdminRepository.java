package com.deepak.Study_Nest.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deepak.Study_Nest.entity.SuperAdmin;

@Repository
public interface SuperAdminRepository extends JpaRepository<SuperAdmin, Long> {
    Optional<SuperAdmin> findByUsername(String username);
}
