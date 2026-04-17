package com.deepak.Study_Nest.dao;

import com.deepak.Study_Nest.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByModuleId(Long moduleId);
}
