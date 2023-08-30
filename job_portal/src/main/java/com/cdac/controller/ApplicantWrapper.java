package com.cdac.controller;

public class ApplicantWrapper {
	private int jobSeekerId;
	private int applicationId;
	private int jobId;
	private boolean showContact;
	
	
	public int getJobId() {
		return jobId;
	}
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	public int getJobSeekerId() {
		return jobSeekerId;
	}
	public void setJobSeekerId(int jobSeekerId) {
		this.jobSeekerId = jobSeekerId;
	}
	public int getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}
	public boolean isShowContact() {
		return showContact;
	}
	public void setShowContact(boolean showContact) {
		this.showContact = showContact;
	}
	
}
