package com.cdac.entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

//job_seeker_id	int	NO	PRI		
//job_type	int	NO	PRI		

@Entity
@Table(name="job_type_preference")
public class JobTypePreference implements Serializable {
	
	@EmbeddedId
	private JobSeekerType jobSeekerType;

	@ManyToOne
	@MapsId("jobSeekerId")
	@JoinColumn(name = "job_seeker_id")
	@JsonIgnore
	private JobSeeker jobSeeker;
	
	@ManyToOne
	@MapsId("jobType")
	@JoinColumn(name = "job_type")
	private JobType jobType;
	
	public JobTypePreference() {
		// To Do
	}

	public JobSeeker getJobSeeker() {
		return jobSeeker;
	}

	public void setJobSeeker(JobSeeker jobSeeker) {
		this.jobSeeker = jobSeeker;
	}

	public JobType getJobType() {
		return jobType;
	}

	public void setJobType(JobType jobType) {
		this.jobType = jobType;
	}

	public JobSeekerType getJobSeekerType() {
		return jobSeekerType;
	}

	public void setJobSeekerType(JobSeekerType jobSeekerType) {
		this.jobSeekerType = jobSeekerType;
	}
	
	
}
