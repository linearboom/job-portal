package com.cdac.repository.job;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cdac.entity.employer.Job;

public interface JobRepo extends JpaRepository<Job, Integer> {

	List<Job> findByJobTitle(String title);
	List<Job> findByJobTitleStartingWithIgnoreCase(String title);
	
	

}
