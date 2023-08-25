package com.cdac.repository.job;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cdac.entity.JobSeeker;
import com.cdac.entity.employer.Job;
import com.cdac.entity.employer.JobSeekerApplication;

@Repository
public interface JobSeekerApplicationRepo extends JpaRepository<JobSeekerApplication, Integer> {
	List<JobSeekerApplication> findByJobSeeker(JobSeeker jobSeeker);
	JobSeekerApplication findByJobSeekerAndJob(JobSeeker jobSeeker, Job job);
}
