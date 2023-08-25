package com.cdac.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
// Composite Primary key for Job Type
@Embeddable
public class JobSeekerType implements Serializable{
	
	@Column(name="job_seeker_id")
	private int jobSeekerId;
	
	@Column(name="job_type")
	private int jobType;

	public JobSeekerType() {
		
	}

	public JobSeekerType(int jobSeekerId, int jobType) {
		this.jobSeekerId = jobSeekerId;
		this.jobType = jobType;
	}

	public int getJobSeekerId() {
		return jobSeekerId;
	}

	public void setJobSeekerId(int jobSeekerId) {
		this.jobSeekerId = jobSeekerId;
	}

	public int getJobType() {
		return jobType;
	}

	public void setJobType(int jobType) {
		this.jobType = jobType;
	}
	
	
	
	
}
