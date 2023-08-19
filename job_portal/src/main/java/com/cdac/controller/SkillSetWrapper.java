package com.cdac.controller;

import java.util.List;

import com.cdac.entity.SkillSet;

public class SkillSetWrapper {
	private List<SkillSet> skills;
	
	public SkillSetWrapper() {
		// No aRgs
	}

	public List<SkillSet> getSkills() {
		return skills;
	}

	public void setSkills(List<SkillSet> skills) {
		this.skills = skills;
	}
	
	
}
