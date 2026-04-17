package com.deepak.Study_Nest.dao;

import com.deepak.Study_Nest.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByRollNo(String rollNo);
    boolean existsByPrn(String prn);
}
