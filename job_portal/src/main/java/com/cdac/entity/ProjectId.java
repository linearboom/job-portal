package com.cdac.entity;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ProjectId implements Serializable {

    @Column(name = "job_seeker_id")
    private int jobSeekerId;

    @Column(name = "project_title")
    private String projectTitle;
    
	public ProjectId() {
		
	}

	public int getJobSeekerId() {
		return jobSeekerId;
	}

	public void setJobSeekerId(int jobSeekerId) {
		this.jobSeekerId = jobSeekerId;
	}
	
	public String getProjectTitle() {
		return projectTitle;
	}

	public void setProjectTitle(String projectTitle) {
		this.projectTitle = projectTitle;
	}
	

    
}

