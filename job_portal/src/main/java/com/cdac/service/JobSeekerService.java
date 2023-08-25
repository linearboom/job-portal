package com.cdac.service;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.entity.JobSeeker;
import com.cdac.repository.JobSeekerRepo;

@Service
public class JobSeekerService {
	
	@Autowired
	private JobSeekerRepo jobSeekerRepo;

	public JobSeeker newUser(JobSeeker jobSeeker) {
		// Perform Validations
		return jobSeekerRepo.save(jobSeeker);
	}

	public JobSeeker login(JobSeeker jobSeeker) {
		return jobSeekerRepo.findByEmailAndPassword(jobSeeker.getEmail(), jobSeeker.getPassword());
		
	}

	public void updateProfile(JobSeeker jobSeeker, JobSeeker validatedUser) {
		validatedUser.setFirstName(jobSeeker.getFirstName());
		validatedUser.setLastName(jobSeeker.getLastName());
		validatedUser.setMobile(jobSeeker.getMobile());
		// Additional Update Details
		jobSeekerRepo.save(validatedUser);
	}
}

