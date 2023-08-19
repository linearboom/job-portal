package com.cdac.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class JobSeekerLocation implements Serializable {

	@Column(name="job_seeker_id")
	private int JobSeekerId;
	
	
	@Column(name="location_id")
	private int LocationId;
	
	public JobSeekerLocation() {
		
	}
	
	public JobSeekerLocation(int job, int location) {
		this.JobSeekerId = job;
		this.LocationId = location;
	}

	public int getJobSeekerId() {
		return JobSeekerId;
	}

	public void setJobSeekerId(int jobSeekerId) {
		JobSeekerId = jobSeekerId;
	}

	public int getLocationId() {
		return LocationId;
	}

	public void setLocationId(int locationId) {
		LocationId = locationId;
	}
	
	
}
