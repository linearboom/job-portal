package com.cdac.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.cdac.entity.SkillSet;
import com.cdac.repository.SkillSetRepo;

@Service
public class SkillSetService {
	
	@Autowired
	private SkillSetRepo skillSetRepo;
	
	/*
	 * Adds Skill which is not present in the Database
	 * Author:
	 * Raj
	 */
	public SkillSet addSkill(SkillSet skill) {
		return skillSetRepo.save(skill);
	}
	
	public List<SkillSet> listMatchSkill(String name){
		return skillSetRepo.findBySkillNameContainingIgnoreCase(name);
	}
	
	public SkillSet findSkillByName(String name) {
		return skillSetRepo.findBySkillName(name);
	}

	
}
