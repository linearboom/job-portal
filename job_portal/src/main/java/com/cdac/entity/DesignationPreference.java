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
@Table(name = "designation_preference")
public class DesignationPreference implements Serializable {
	
	@EmbeddedId
	private DesignationComposite designationComposite;
	
	
    @ManyToOne
    @MapsId("jobSeekerId")
    @JoinColumn(name = "job_seeker_id")
    @JsonIgnore
    private JobSeeker jobSeeker;


    @ManyToOne
    @MapsId("designationId")// Potential Error 
    @JoinColumn(name = "designation_id")
    private Designation designation;
    
    public DesignationPreference() {
    	
    }

	public JobSeeker getJobSeeker() {
		return jobSeeker;
	}

	public void setJobSeeker(JobSeeker jobSeeker) {
		this.jobSeeker = jobSeeker;
	}


	public Designation getDesignation() {
		return designation;
	}

	public void setDesignation(Designation designation) {
		this.designation = designation;
	}

	public DesignationComposite getDesignationComposite() {
		return designationComposite;
	}

	public void setDesignationComposite(DesignationComposite designationComposite) {
		this.designationComposite = designationComposite;
	}
	
	

	
    
    
}
