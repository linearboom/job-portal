package com.cdac.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.entity.JobSeeker;
import com.cdac.entity.employer.Employer;
import com.cdac.entity.employer.Job;
import com.cdac.entity.employer.JobPostedSkillSet;
import com.cdac.service.employer.EmployerService;
import com.cdac.service.job.JobPostedSkillSetService;
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
	
	

}
