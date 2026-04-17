package com.deepak.Study_Nest.dao;

import com.deepak.Study_Nest.entity.StudentResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentResultRepository extends JpaRepository<StudentResult, Long> {
    List<StudentResult> findByStudentId(Long studentId);
    Optional<StudentResult> findByStudentIdAndModuleId(Long studentId, Long moduleId);
}
