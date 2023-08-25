package com.cdac.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.entity.JobType;
import com.cdac.repository.JobTypeRepo;

@Service
public class JobTypeService {

	@Autowired
	private JobTypeRepo jobTypeRepo;
	/*
	 * Adds Single jobtype
	 */
	public JobType AddJobType(JobType jobType) {
		return jobTypeRepo.save(jobType);
	}
	
	public JobType FindJobType(String jobType) {
		return jobTypeRepo.findByJobType(jobType);
	}
	
	public JobType FindRoleName(String roleName) {
		return jobTypeRepo.findByRoleName(roleName);
	}

	public List<JobType> findAll() {
		// TODO Auto-generated method stub
		return jobTypeRepo.findAll();
	}
}
