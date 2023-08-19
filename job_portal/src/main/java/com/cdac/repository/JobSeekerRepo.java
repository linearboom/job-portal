package com.cdac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cdac.entity.JobSeeker;


@Repository
public interface JobSeekerRepo extends JpaRepository<JobSeeker, Integer>{
	public JobSeeker findByEmailAndPassword(String email, String password);
	public JobSeeker findByEmail(String email);
}
