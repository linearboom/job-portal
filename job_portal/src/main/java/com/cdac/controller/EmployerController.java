package com.cdac.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.dto.JobApplicationDTO;
import com.cdac.dto.JobSeekerDTO;
import com.cdac.entity.JobSeeker;
import com.cdac.entity.employer.Employer;
import com.cdac.entity.employer.Job;
import com.cdac.entity.employer.JobPostedSkillSet;
import com.cdac.entity.employer.JobSeekerApplication;
import com.cdac.service.employer.EmployerService;
import com.cdac.service.job.JobPostedSkillSetService;
import com.cdac.service.job.JobSeekerApplicationService;
import com.cdac.service.job.JobService;

@RestController
@RequestMapping("/employer")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class EmployerController {
	
	@Autowired
	private EmployerService employerService;
	
	@Autowired
	private JobService jobService;
	
	@Autowired
	private JobPostedSkillSetService jobPostedSkillSetService;
	
	@Autowired
	private JobSeekerApplicationService jobSeekerApplicationService;
	
	@PostMapping("login")
	public ResponseEntity<Employer> login(@RequestBody Employer employer, HttpSession session){
		Employer validatedUser = employerService.validateUser(employer);
		if (validatedUser != null) {
			session.setAttribute("user",validatedUser);
			return new ResponseEntity<>(validatedUser, HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>(validatedUser, HttpStatus.OK); // Check what will be the status for this
		}
	}
	
	@PostMapping("update")
	public ResponseEntity<Employer> update(@RequestBody Employer employer, HttpSession session){
		Employer validatedUser =  (Employer) session.getAttribute("user");
		if (validatedUser != null) {
			Employer updated =  employerService.updateData(employer, validatedUser);
			return new ResponseEntity<>(validatedUser, HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>(validatedUser, HttpStatus.OK); // Check what will be the status for this
		}
	}
	
	@PostMapping("refresh")
	public ResponseEntity<Employer> validateUser(String name, HttpSession session) {
		Employer seeker = (Employer) session.getAttribute("user");
		if (seeker != null) {
			return new ResponseEntity<Employer>(employerService.findEmployer(seeker.getEmployerId()), HttpStatus.OK);
		}
		return new ResponseEntity<Employer>(seeker, HttpStatus.OK);
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody Employer employer){
		employerService.newUser(employer);
		return new ResponseEntity<>("Succesful", HttpStatus.OK);
	}
	
	@PostMapping("postNewJob")
	public ResponseEntity<Job> postNewJob(@RequestBody JobWrapper job, HttpSession session ){
		Employer employer = (Employer) session.getAttribute("user");
		if (employer != null) {
			Job newJob = jobService.postJob(job.getJob(), employer, job.getSkills());
			Job trial = new Job();
			trial.setJobId(newJob.getJobId());
			jobPostedSkillSetService.addJobSkills(job.getSkills(), trial);
			return new ResponseEntity<>(newJob, HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
	
	@PostMapping("viewApplicants")
	public ResponseEntity<List<JobApplicationDTO>> viewApplicants(@RequestBody Job job, HttpSession session ){
		Employer employer = (Employer) session.getAttribute("user");
		if (employer != null) {
			int jobId =  job.getJobId();
			System.out.println(job.getJobTitle());
			List<JobApplicationDTO>applicants = jobSeekerApplicationService.listApplicants(jobId, employer);
			return new ResponseEntity<>(applicants, HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
	
	@PostMapping("viewApplicant")
	public ResponseEntity<JobSeekerDTO> viewApplicant(@RequestBody ApplicantWrapper details, HttpSession session ){
		Employer employer = (Employer) session.getAttribute("user");
		if (employer != null) {
			int jobSeekerId = details.getJobSeekerId();
			int applicationId = details.getApplicationId();
			int jobId = details.getJobId();
			System.out.println(applicationId);
			JobSeekerDTO seeker = jobSeekerApplicationService.getApplicantDetails(jobSeekerId, applicationId, jobId, employer.getEmployerId());
			return new ResponseEntity<>(seeker, HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
	

	
	

}
