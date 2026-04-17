package com.deepak.Study_Nest.dao;

import com.deepak.Study_Nest.entity.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TutorRepository extends JpaRepository<Tutor, Long> {
    Optional<Tutor> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByMobileNo(String mobileNo);
}
