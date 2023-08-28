package com.cdac.dto;

import java.sql.Date;
import java.util.List;

import com.cdac.entity.Certification;
import com.cdac.entity.DesignationPreference;
import com.cdac.entity.EducationDetail;
import com.cdac.entity.ExtraAccomplishment;
import com.cdac.entity.JobTypePreference;
import com.cdac.entity.LocationUserPreference;
import com.cdac.entity.Project;
import com.cdac.entity.UserSkillSet;
import com.cdac.entity.WorkExperience;

// To send only applicant Data
public class JobSeekerDTO {
	private int jobSeekerId;
	private Date dateOfBirth;
	private String firstName;
	private Float currentCtc;
	private Float expectedCtc;
	private Integer noticePeriod;
	private String currentDesignation;
	private String profileImagePath;
	private String email;
	private String mobile;
	private List<UserSkillSet> userSkillSets;
	private List<LocationUserPreference> userLocations;
	private List<EducationDetail> education;
	private List<JobTypePreference> jobTypePreference;
	private List<DesignationPreference> jobDesignationPreference;
	private List<WorkExperience> experience;
	private List<Project> project;
	private List<ExtraAccomplishment> accomplishment;
	private List<Certification> certificate;
	private String resumeHeadline;
	private String resumePath;

	
	
	public JobSeekerDTO(int jobSeekerId, Date dateOfBirth, String firstName, Float currentCtc, Float expectedCtc,
			Integer noticePeriod, String currentDesignation, String profileImagePath) {
		
		this.jobSeekerId = jobSeekerId;
		this.dateOfBirth = dateOfBirth;
		this.firstName = firstName;
		this.currentCtc = currentCtc;
		this.expectedCtc = expectedCtc;
		this.noticePeriod = noticePeriod;
		this.currentDesignation = currentDesignation;
		this.profileImagePath = profileImagePath;
	}
	public JobSeekerDTO() {
		// TODO Auto-generated constructor stub
	}
	public int getJobSeekerId() {
		return jobSeekerId;
	}
	public void setJobSeekerId(int jobSeekerId) {
		this.jobSeekerId = jobSeekerId;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public Float getCurrentCtc() {
		return currentCtc;
	}
	public void setCurrentCtc(Float currentCtc) {
		this.currentCtc = currentCtc;
	}
	public Float getExpectedCtc() {
		return expectedCtc;
	}
	public void setExpectedCtc(Float expectedCtc) {
		this.expectedCtc = expectedCtc;
	}
	public Integer getNoticePeriod() {
		return noticePeriod;
	}
	public void setNoticePeriod(Integer noticePeriod) {
		this.noticePeriod = noticePeriod;
	}
	public String getCurrentDesignation() {
		return currentDesignation;
	}
	public void setCurrentDesignation(String currentDesignation) {
		this.currentDesignation = currentDesignation;
	}
	public String getProfileImagePath() {
		return profileImagePath;
	}
	public void setProfileImagePath(String profileImagePath) {
		this.profileImagePath = profileImagePath;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<UserSkillSet> getUserSkillSets() {
		return userSkillSets;
	}
	public void setUserSkillSets(List<UserSkillSet> userSkillSets) {
		this.userSkillSets = userSkillSets;
	}
	public List<LocationUserPreference> getUserLocations() {
		return userLocations;
	}
	public void setUserLocations(List<LocationUserPreference> userLocations) {
		this.userLocations = userLocations;
	}
	public List<EducationDetail> getEducation() {
		return education;
	}
	public void setEducation(List<EducationDetail> education) {
		this.education = education;
	}
	public List<JobTypePreference> getJobTypePreference() {
		return jobTypePreference;
	}
	public void setJobTypePreference(List<JobTypePreference> jobTypePreference) {
		this.jobTypePreference = jobTypePreference;
	}
	public List<DesignationPreference> getJobDesignationPreference() {
		return jobDesignationPreference;
	}
	public void setJobDesignationPreference(List<DesignationPreference> jobDesignationPreference) {
		this.jobDesignationPreference = jobDesignationPreference;
	}
	public List<WorkExperience> getExperience() {
		return experience;
	}
	public void setExperience(List<WorkExperience> experience) {
		this.experience = experience;
	}
	public List<Project> getProject() {
		return project;
	}
	public void setProject(List<Project> project) {
		this.project = project;
	}
	public List<ExtraAccomplishment> getAccomplishment() {
		return accomplishment;
	}
	public void setAccomplishment(List<ExtraAccomplishment> accomplishment) {
		this.accomplishment = accomplishment;
	}
	public List<Certification> getCertificate() {
		return certificate;
	}
	public void setCertificate(List<Certification> certificate) {
		this.certificate = certificate;
	}
	public String getResumeHeadline() {
		return resumeHeadline;
	}
	public void setResumeHeadline(String resumeHeadline) {
		this.resumeHeadline = resumeHeadline;
	}
	public String getResumePath() {
		return resumePath;
	}
	public void setResumePath(String resumePath) {
		this.resumePath = resumePath;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	
}
