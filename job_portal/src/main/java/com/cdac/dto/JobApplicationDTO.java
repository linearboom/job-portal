package com.cdac.dto;

import java.util.Date;

// To send data to job application DTO
public class JobApplicationDTO {
	private int applicationId;
	private Date applyDate;
	private char isShortlist;
	private char isContacted;
	private int jobId;
	private JobSeekerDTO jobSeekerDTO;
	public int getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
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
	public int getJobId() {
		return jobId;
	}
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	public JobSeekerDTO getJobSeekerDTO() {
		return jobSeekerDTO;
	}
	public void setJobSeekerDTO(JobSeekerDTO jobSeekerDTO) {
		this.jobSeekerDTO = jobSeekerDTO;
	}
	
	
	
}
