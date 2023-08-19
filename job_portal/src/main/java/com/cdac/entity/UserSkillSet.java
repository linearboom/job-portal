package com.cdac.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "user_skillset")
public class UserSkillSet {

	public void setSkillLevel(Integer skillLevel) {
		this.skillLevel = skillLevel;
	}


	//Composite Primary Key
	@EmbeddedId
	private JobSeekerSkillId jobSeekerSkillId;
	
	@Column(name = "skill_level", columnDefinition = "INT DEFAULT -1", nullable = false)
	private Integer skillLevel = -1; // Change this later when skill level is implemented
	
	@ManyToOne
	@MapsId("skillSetId")
	@JoinColumn(name = "skill_set_id")
	private SkillSet skill;
	
	
	@ManyToOne
	@MapsId("jobSeekerId")
	@JoinColumn(name="job_seeker_id")
	@JsonIgnore
	private JobSeeker jobSeeker;
	
	public UserSkillSet() {
		//No Contructor Args
	}


	public JobSeekerSkillId getJobSeekerSkillId() {
		return jobSeekerSkillId;
	}


	public void setJobSeekerSkillId(JobSeekerSkillId jobSeekerSkillId) {
		this.jobSeekerSkillId = jobSeekerSkillId;
	}


	public int getSkillLevel() {
		return skillLevel;
	}


	public void setSkillLevel(int skillLevel) {
		this.skillLevel = skillLevel;
	}


	public SkillSet getSkill() {
		return skill;
	}


	public void setSkill(SkillSet skill) {
		this.skill = skill;
	}


	public JobSeeker getJobSeeker() {
		return jobSeeker;
	}


	public void setJobSeeker(JobSeeker jobSeeker) {
		this.jobSeeker = jobSeeker;
	}
	

	
}
