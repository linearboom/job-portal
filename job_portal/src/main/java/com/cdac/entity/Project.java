package com.cdac.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "projects")
public class Project {

	@EmbeddedId
	private ProjectId id;

	@ManyToOne
	@MapsId("jobSeekerId")
	@JoinColumn(name = "job_seeker_id")
	@JsonBackReference
	private JobSeeker jobSeeker;

	@Column(name = "project_description")
	private String projectDescription;

	@Column(name = "technology_stack")
	private String technologyStack;

	public Project() {

	}

	public ProjectId getId() {
		return id;
	}

	public void setId(ProjectId id) {
		this.id = id;
	}

	public JobSeeker getJobSeeker() {
		return jobSeeker;
	}

	public void setJobSeeker(JobSeeker jobSeeker) {
		this.jobSeeker = jobSeeker;
	}

	public String getProjectDescription() {
		return projectDescription;
	}

	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}

	public String getTechnologyStack() {
		return technologyStack;
	}

	public void setTechnologyStack(String technologyStack) {
		this.technologyStack = technologyStack;
	}

}
