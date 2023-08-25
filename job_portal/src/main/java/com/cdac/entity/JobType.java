package com.cdac.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="m_job_type")
public class JobType implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="job_type")
	private int jobType;
	
	
	@Column(name="role_name")
	private String roleName;


	public JobType() {
		//To Do
	}


	public int getJobType() {
		return jobType;
	}


	public void setJobType(int jobType) {
		this.jobType = jobType;
	}


	public String getRoleName() {
		return roleName;
	}


	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	
	
	
	
}
