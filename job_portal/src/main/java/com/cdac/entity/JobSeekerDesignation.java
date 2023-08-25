package com.cdac.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class JobSeekerDesignation implements Serializable {

	@Column(name="job_seeker_id")
	private int JobSeekerId;
	
	
	@Column(name="designation_id")
	private int DesignationId;
	
	public JobSeekerDesignation() {
		
	}
	
	public JobSeekerDesignation(int job, int designation) {
		this.JobSeekerId = job;
		this.DesignationId = designation;
	}

	public int getJobSeekerId() {
		return JobSeekerId;
	}

	public void setJobSeekerId(int jobSeekerId) {
		JobSeekerId = jobSeekerId;
	}

	public int getDesignationId() {
		return DesignationId;
	}

	public void setDesignationId(int designationId) {
		DesignationId = designationId;
	}

	
	
	
}
