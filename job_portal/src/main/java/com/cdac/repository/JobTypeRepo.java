package com.cdac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cdac.entity.JobType;

@Repository
public interface JobTypeRepo extends JpaRepository<JobType, Integer>{
	public JobType findByJobType(String jobtype);
	public JobType findByRoleName(String roleName);
}
