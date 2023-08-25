package com.cdac.repository.job;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cdac.entity.employer.JobPostedSkillSet;

@Repository
public interface JobPostedSkillSetRepo extends JpaRepository<JobPostedSkillSet, Integer> {
	
}
