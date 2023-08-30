package com.cdac.service;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.entity.JobSeeker;
import com.cdac.entity.UserSkillSet;
import com.cdac.entity.employer.Job;
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
		validatedUser.setProfileImagePath(jobSeeker.getProfileImagePath());
		// Additional Update Details
		jobSeekerRepo.save(validatedUser);
	}

	public JobSeeker findUser(int jobSeekerId) {
		// TODO Auto-generated method stub
		Optional<JobSeeker> seeker =  jobSeekerRepo.findById(jobSeekerId);
		if (seeker.isPresent()) {
			return seeker.get();
		}
		return null;
	}

	public void updateSummary(String resumeHeadline, JobSeeker jobSeeker) {
		jobSeeker.setResumeHeadline(resumeHeadline);
		jobSeekerRepo.save(jobSeeker);
		
	}

	public void updateResume(JobSeeker seeker) {
		jobSeekerRepo.save(seeker);
		
	}
	
	/*
	 * Reccomends Jobs based on Job Seeker Skills
	 */
	public List<Job> reccomendation(JobSeeker seeker) {
		List<UserSkillSet> skills = seeker.getUserSkillSets();
		
		return null;
	}
	
	
}

