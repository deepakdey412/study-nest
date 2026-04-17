package com.deepak.Study_Nest.dao;

import com.deepak.Study_Nest.entity.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long> {
    List<Module> findBySemester(Integer semester);
}
