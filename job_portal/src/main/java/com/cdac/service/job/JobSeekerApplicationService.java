package com.cdac.service.job;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.controller.ApplicantWrapper;
import com.cdac.dto.JobApplicationDTO;
import com.cdac.dto.JobSeekerDTO;
import com.cdac.entity.JobSeeker;
import com.cdac.entity.employer.Employer;
import com.cdac.entity.employer.Job;
import com.cdac.entity.employer.JobSeekerApplication;
import com.cdac.repository.job.JobSeekerApplicationRepo;
import com.cdac.service.EmailService;

@Service
public class JobSeekerApplicationService {

	@Autowired
	private JobSeekerApplicationRepo jobSeekerApplicationRepo;
	
	@Autowired
	private EmailService emailService;
	
	
	

	public JobSeekerApplication applyJob(JobSeekerApplication application, JobSeeker jobSeeker) {
		JobSeekerApplication ApplicationExists = jobSeekerApplicationRepo.findByJobSeekerAndJob(jobSeeker,
				application.getJob());
		if (ApplicationExists != null) {
			return null;
		}
		// Get the current timestamp in milliseconds
		long currentTimestampMillis = System.currentTimeMillis();

		// Convert the timestamp to a java.util.Date
		java.util.Date utilDate = new java.util.Date(currentTimestampMillis);

		// Format the java.util.Date to a string in "yyyy-MM-dd" format
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String formattedDate = sdf.format(utilDate);

		// Convert the formatted date string to a java.sql.Date
		Date sqlDate = Date.valueOf(formattedDate);
		application.setApplyDate(sqlDate);

		application.setJobSeeker(jobSeeker);
		return jobSeekerApplicationRepo.save(application);
	}

	public boolean deleteJobApplication(JobSeekerApplication application, JobSeeker jobSeeker) {
		Optional<JobSeekerApplication> ApplicationExists = jobSeekerApplicationRepo
				.findById(application.getApplicationId());
		if (ApplicationExists.isEmpty()) {
			return false;
		}
		jobSeekerApplicationRepo.delete(application);
		return true;
	}

	public List<JobApplicationDTO> listApplicants(int jobId, Employer employer) {
		// TODO Auto-generated method stub
		List<JobApplicationDTO> applicantionsDTO = new ArrayList<JobApplicationDTO>();
		List<JobSeekerApplication> applications = jobSeekerApplicationRepo.findApplicants(jobId);
		for (JobSeekerApplication application : applications) {
			JobApplicationDTO applicationDTO = new JobApplicationDTO();
			applicationDTO.setApplicationId(application.getApplicationId());
			applicationDTO.setApplyDate(application.getApplyDate());
			applicationDTO.setIsContacted(application.getIsContacted());
			applicationDTO.setIsShortlist(application.getIsShortlist());
			applicationDTO.setJobId(application.getJob().getJobId());

			JobSeekerDTO seeker = new JobSeekerDTO();
			seeker.setFirstName(application.getJobSeeker().getFirstName());
			seeker.setJobSeekerId(application.getJobSeeker().getJobSeekerId());
			seeker.setDateOfBirth(application.getJobSeeker().getDateOfBirth());

			applicationDTO.setJobSeekerDTO(seeker);
			applicantionsDTO.add(applicationDTO);
		}
		return applicantionsDTO;
	}

	public JobSeekerDTO getApplicantDetails(ApplicantWrapper details, int jobSeekerId, int applicationId, int jobId, int employerId) {
		Optional<JobSeekerApplication> application = jobSeekerApplicationRepo.findById(applicationId);
		if (application.isPresent()) {
			System.out.println("Debug");
			if (application.get().getJob().getEmployer().getEmployerId() == employerId) {
				System.out.println("Debug");
				JobSeeker seeker = jobSeekerApplicationRepo.getApplicantDetails(jobSeekerId, applicationId, jobId);
				JobSeekerDTO jobSeeker =  new JobSeekerDTO();
				jobSeeker.setDateOfBirth(seeker.getDateOfBirth());
		        jobSeeker.setFirstName(seeker.getFirstName());
		        jobSeeker.setCurrentCtc(seeker.getCurrentCtc());
		        jobSeeker.setExpectedCtc(seeker.getExpectedCtc());
		        jobSeeker.setNoticePeriod(seeker.getNoticePeriod());
		        jobSeeker.setCurrentDesignation(seeker.getCurrentDesignation());
		        jobSeeker.setProfileImagePath(seeker.getProfileImagePath());
		        
		        jobSeeker.setUserSkillSets(seeker.getUserSkillSets());
		        jobSeeker.setUserLocations(seeker.getUserLocations());
		        jobSeeker.setEducation(seeker.getEducation());
		        jobSeeker.setJobTypePreference(seeker.getJobTypePreference());
		        jobSeeker.setJobDesignationPreference(seeker.getJobDesignationPreference());
		        jobSeeker.setExperience(seeker.getExperience());
		        jobSeeker.setProject(seeker.getProject());
		        jobSeeker.setAccomplishment(seeker.getAccomplishment());
		        jobSeeker.setCertificate(seeker.getCertificate());
		        jobSeeker.setResumeHeadline(seeker.getResumeHeadline());
		        jobSeeker.setResumePath(seeker.getResumePath());
		        jobSeeker.setProfileImagePath(seeker.getProfileImagePath());
		        
		        if (details.isShowContact() == true || application.get().getIsContacted() == '1') {
		        	jobSeeker.setEmail(seeker.getEmail());
			        jobSeeker.setMobile(seeker.getMobile());
			        JobSeekerApplication persist =  application.get();
			        if (persist.getIsContacted() != '1') {
			        persist.setIsContacted('1');
			        System.out.println("Changing Application Status to 1");
			        jobSeekerApplicationRepo.save(persist);
			        Job job = application.get().getJob();
			        emailService.notifySeeker(job, seeker);
			        }
			       
//			        if (application.get().getIsContacted() == ' ') {
//			        	application.get().setIsContacted('1');
//			        	jobSeekerApplicationRepo.save(application.get());
//			        }
		        }
		       
		        return jobSeeker;
			}
		}
		return null;
	}
}
