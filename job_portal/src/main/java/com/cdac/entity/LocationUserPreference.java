package com.cdac.entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "location_user_preference")
public class LocationUserPreference implements Serializable {
	
	@EmbeddedId
	private JobSeekerLocation jobSeekerLocation;
	
	
    @ManyToOne
    @MapsId("jobSeekerId")
    @JoinColumn(name = "job_seeker_id")
    @JsonIgnore
    private JobSeeker jobSeeker;


    @ManyToOne
    @MapsId("locationId")// Potential Error 
    @JoinColumn(name = "location_id")
    private Location location;
    
    public LocationUserPreference() {
    	
    }

	public JobSeeker getJobSeeker() {
		return jobSeeker;
	}

	public void setJobSeeker(JobSeeker jobSeeker) {
		this.jobSeeker = jobSeeker;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public JobSeekerLocation getJobSeekerLocation() {
		return jobSeekerLocation;
	}

	public void setJobSeekerLocation(JobSeekerLocation jobSeekerLocation) {
		this.jobSeekerLocation = jobSeekerLocation;
	}
    
    
}
