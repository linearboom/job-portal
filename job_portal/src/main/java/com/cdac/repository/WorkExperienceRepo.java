package com.cdac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cdac.entity.WorkExperience;

@Repository
public interface WorkExperienceRepo extends JpaRepository<WorkExperience, Integer> {
    
}
