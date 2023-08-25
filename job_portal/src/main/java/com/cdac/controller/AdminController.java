package com.cdac.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.entity.SkillSet;
import com.cdac.service.SkillSetService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	
	@Autowired
	private SkillSetService skillSetService;

	@PostMapping("/addSkill")
	public ResponseEntity<String> addSkill(@RequestBody SkillSet skill, HttpSession session){
		// Check Session Management and Admin Login Logic
		skill.setSkillApproved('1');
		skillSetService.addSkill(skill);
		return new ResponseEntity<String>("Successfully Added", HttpStatus.OK);
	}
}
