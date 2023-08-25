package com.cdac.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.entity.JobSeeker;
import com.cdac.entity.JobSeekerLocation;
import com.cdac.entity.JobSeekerType;
import com.cdac.entity.JobType;
import com.cdac.entity.JobTypePreference;
import com.cdac.entity.Location;
import com.cdac.entity.LocationUserPreference;
import com.cdac.repository.JobTypePreferenceRepo;

@Service
public class JobTypePreferenceService {

	
	@Autowired
	private JobTypePreferenceRepo jobTypePreferenceRepo;
	
	@Autowired
	private JobTypeService jobTypeService;
	
	/*
	 * Adds the JobType to the particular Job Seeker.
	 * Wont allow to add the existing JobType
	 */
	public JobTypePreference addJobTypePreference(JobType jobType, JobSeeker jobSeeker) {
		//Validation to check the JobType Preference
		int jobTypeID = jobType.getJobType();
		int jobSeekerId = jobSeeker.getJobSeekerId();
		JobTypePreference jobTypePreference = new JobTypePreference();
		jobTypePreference.setJobSeekerType(new JobSeekerType(jobTypeID, jobSeekerId));
		jobTypePreference.setJobSeeker(jobSeeker);
		jobTypePreference.setJobType(jobType);
	
		return jobTypePreferenceRepo.save(jobTypePreference);
		
	}

	/*
	 * Delete the Job Preference Type from the User
	 * 
	 */
	public void deleteJobPreference(JobTypePreference jobTypePreference, JobSeeker jobSeeker) {
		//Validation for Deletion Logic if any
		jobTypePreferenceRepo.delete(jobTypePreference);
	}

}
