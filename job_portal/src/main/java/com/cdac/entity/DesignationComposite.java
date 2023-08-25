package com.cdac.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class DesignationComposite implements Serializable {

	@Column(name="designation_id")
	private int DesignationId;
	
	@Column(name ="job_seeker_id")
	private int jobSeekerId;
	
	public DesignationComposite() {
		
	}

	public DesignationComposite( int jobSeekerId,int designationId) {
		super();
		DesignationId = designationId;
		this.jobSeekerId = jobSeekerId;
	}

	public int getDesignationId() {
		return DesignationId;
	}

	public void setDesignationId(int designationId) {
		DesignationId = designationId;
	}

	public int getJobSeekerId() {
		return jobSeekerId;
	}

	public void setJobSeekerId(int jobSeekerId) {
		this.jobSeekerId = jobSeekerId;
	}
	
	
}
