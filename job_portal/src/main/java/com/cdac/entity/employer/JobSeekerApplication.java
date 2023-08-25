package com.cdac.entity.employer;

import javax.persistence.*;

import com.cdac.entity.JobSeeker;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

// Table for Job Seeker Application History

@Entity
@Table(name = "job_seeker_application")
public class JobSeekerApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_id")
    private int applicationId;

    @ManyToOne
    @JoinColumn(name = "job_seeker_id")
    @JsonBackReference
    private JobSeeker jobSeeker;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    @Column(name = "apply_date", nullable = false)
    private Date applyDate;

    @Column(name = "isshortlist")
    private char isShortlist;

    @Column(name = "iscontacted")
    private char isContacted;

    public JobSeekerApplication() {
    	
    }

	public int getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}

	public JobSeeker getJobSeeker() {
		return jobSeeker;
	}

	public void setJobSeeker(JobSeeker jobSeeker) {
		this.jobSeeker = jobSeeker;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}

	public char getIsShortlist() {
		return isShortlist;
	}

	public void setIsShortlist(char isShortlist) {
		this.isShortlist = isShortlist;
	}

	public char getIsContacted() {
		return isContacted;
	}

	public void setIsContacted(char isContacted) {
		this.isContacted = isContacted;
	}
    
	
	
    
    
}
