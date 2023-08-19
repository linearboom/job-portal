package com.cdac.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.entity.JobSeeker;
import com.cdac.entity.SkillSet;
import com.cdac.entity.UserSkillSet;
import com.cdac.repository.SkillSetRepo;
import com.cdac.service.JobSeekerService;
import com.cdac.service.SkillSetService;
import com.cdac.service.UserSkillSetService;

@RestController
@RequestMapping("/job_seeker")
public class JobSeekerController {
	
	@Autowired
	private JobSeekerService jobSeekerService;
	
	@Autowired
	private SkillSetService skillSetService;
	
	@Autowired
	private UserSkillSetService userSkillSetService;

	
	@GetMapping("/hello")
	public String hello() {
		return "Hello";
	}
	
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody JobSeeker jobSeeker){
		jobSeekerService.newUser(jobSeeker);
		return new ResponseEntity<>("Succesful", HttpStatus.OK);
	}
	
	@PostMapping("login")
	public ResponseEntity<JobSeeker> login(@RequestBody JobSeeker jobSeeker, HttpSession session){
		JobSeeker validatedUser = jobSeekerService.login(jobSeeker);
		if (validatedUser != null) {
			session.setAttribute("user",validatedUser);
			return new ResponseEntity<>(validatedUser, HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>(validatedUser, HttpStatus.OK); // Check what will be the status for this
		}
	}
	
	/*
	 * Will Accept a List of Skills.
	 * 
	 */
	@PostMapping("addSkills")
	public ResponseEntity<String> addUserSkill(@RequestBody SkillSetWrapper skills, HttpSession session){
		JobSeeker jobSeeker = (JobSeeker) session.getAttribute("user");
		if (jobSeeker != null) {
			userSkillSetService.addUserSkills(skills.getSkills(), jobSeeker);
			return new ResponseEntity<>(jobSeeker.getFirstName(), HttpStatus.OK);
		}
		
		return new ResponseEntity<>("Session Expired", HttpStatus.OK);
	}
	
	/*
	 * Deletes a given Skill for the Session User
	 * 
	 */
	@PostMapping("deleteSkill")
	public ResponseEntity<String> deleteUserSkill(@RequestBody UserSkillSet skill, HttpSession session){
		JobSeeker jobSeeker = (JobSeeker) session.getAttribute("user");
		if (jobSeeker != null) {
			userSkillSetService.deleteUserSkill(skill, jobSeeker);
			return new ResponseEntity<>(jobSeeker.getFirstName(), HttpStatus.OK);
		}
		
		return new ResponseEntity<>(jobSeeker.getFirstName(), HttpStatus.OK);
	}
	
	/*
	 * Will Return a List of skills based on the match
	 */
	@GetMapping("suggestSkills")
	public ResponseEntity<List<SkillSet>> suggestSkills (String name, HttpSession session){
		return new ResponseEntity<>(skillSetService.listMatchSkill(name), HttpStatus.OK);
	}
	
}
