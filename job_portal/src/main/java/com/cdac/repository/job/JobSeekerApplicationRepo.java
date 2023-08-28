package com.cdac.repository.job;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import com.cdac.dto.JobApplicationDTO;
import com.cdac.entity.JobSeeker;
import com.cdac.entity.employer.Job;
import com.cdac.entity.employer.JobSeekerApplication;

@Repository
public interface JobSeekerApplicationRepo extends JpaRepository<JobSeekerApplication, Integer> {
	List<JobSeekerApplication> findByJobSeeker(JobSeeker jobSeeker);
	JobSeekerApplication findByJobSeekerAndJob(JobSeeker jobSeeker, Job job);
	
	@Query("SELECT a FROM JobSeekerApplication a JOIN FETCH a.jobSeeker js JOIN FETCH a.job j WHERE j.jobId = :jobId")
	public List<JobSeekerApplication> findApplicants(@Param("jobId")int jobId);
	
	@Query("SELECT a.jobSeeker FROM JobSeekerApplication a JOIN a.jobSeeker js JOIN a.job j " +
		       "WHERE j.jobId = :jobId AND js.jobSeekerId = :jobSeekerId AND a.applicationId = :applicationId")
		JobSeeker getApplicantDetails(@Param("jobSeekerId") int jobSeekerId,
		                              @Param("applicationId") int applicationId,
		                              @Param("jobId") int jobId);


}
