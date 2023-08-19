package com.cdac.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class JobSeekerSkillId implements Serializable {

	@Column(name = "job_seeker_id")
	private int jobSeekerId;

	@Column(name = "skill_set_id")
	private int skillSetId;

	public JobSeekerSkillId() {

	}

	public JobSeekerSkillId(int jobId, int skillId) {
		this.jobSeekerId = jobId;
		this.skillSetId = skillId;
	}

	public int getJobSeekerId() {
		return jobSeekerId;
	}

	public void setJobSeekerId(int jobSeekerId) {
		this.jobSeekerId = jobSeekerId;
	}

	public int getSkillSetId() {
		return skillSetId;
	}

	public void setSkillSetId(int skillSetId) {
		this.skillSetId = skillSetId;
	}

}
