package com.cdac.controller;

import java.util.List;

import com.cdac.entity.SkillSet;
import com.cdac.entity.employer.Job;

public class JobWrapper {
	private Job job;
	private List<SkillSet> skills;
	public Job getJob() {
		return job;
	}
	public void setJob(Job job) {
		this.job = job;
	}
	public List<SkillSet> getSkills() {
		return skills;
	}
	public void setSkills(List<SkillSet> skills) {
		this.skills = skills;
	}
}
