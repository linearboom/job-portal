package com.cdac.entity;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.cdac.entity.employer.JobSeekerApplication;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;



@Entity
@Table(name = "job_seeker")
public class JobSeeker{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "job_seeker_id")
	private int jobSeekerId;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "date_of_birth", nullable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date dateOfBirth;
	
	@OneToMany(mappedBy = "jobSeeker", cascade = CascadeType.ALL)
	private java.util.List<UserSkillSet> userSkillSets;
	
	@OneToMany(mappedBy="jobSeeker", cascade = CascadeType.ALL)
	private List<LocationUserPreference> userLocations;
	
	@OneToMany(mappedBy="jobSeeker", cascade = CascadeType.ALL)
	private List<EducationDetail> education;
	
	@OneToMany(mappedBy = "jobSeeker", cascade = CascadeType.ALL)
	private List<JobTypePreference> jobTypePreference;
	
	
	@OneToMany(mappedBy = "jobSeeker", cascade = CascadeType.ALL)
	private List<DesignationPreference> jobDesignationPreference;
	
	@OneToMany(mappedBy = "jobSeeker", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<JobSeekerApplication> jobSeekerApplication;
	
	@OneToMany(mappedBy = "jobSeeker", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<WorkExperience> experience;
	
	@OneToMany(mappedBy = "jobSeeker", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Project> project;
	
	@OneToMany(mappedBy = "jobSeeker", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<ExtraAccomplishment> accomplishment;
	
	@OneToMany(mappedBy = "jobSeeker", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Certification> certificate;
	
	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "mobile")
	private String mobile;

	@OneToOne // Change
	@JoinColumn(name = "address_id")
	private Address address;

	@Column(name = "current_ctc")
	private Float currentCtc;

	@Column(name = "expected_ctc")
	private Float expectedCtc;

	@Column(name = "notice_period")
	private Integer noticePeriod;

	@Column(name = "current_designation")
	private String currentDesignation;

	@Column(name = "is_student")
	private Boolean isStudent;

	@Column(name = "is_working")
	private Boolean isWorking;

	@Column(name = "is_open_for_work")
	private Boolean isOpenForWork;

	@Column(name = "is_profile_visible")
	private Boolean isProfileVisible;

	@Column(name = "is_profile_blocked")
	private Boolean isProfileBlocked;

	@Column(name = "resume_headline")
	private String resumeHeadline;

	@Column(name = "resume_path")
	private String resumePath;

	@Column(name = "profile_image_path")
	private String profileImagePath;

	@Column(name = "field1")
	private String field1;

	@Column(name = "field2")
	private String field2;
	
	

	public List<UserSkillSet> getUserSkillSets() {
		return userSkillSets;
	}	

	public void setUserSkillSets(List<UserSkillSet> userSkillSets) {
		this.userSkillSets = userSkillSets;
	}

	public JobSeeker() {
		
	}

	public int getJobSeekerId() {
		return jobSeekerId;
	}

	public void setJobSeekerId(int jobSeekerId) {
		this.jobSeekerId = jobSeekerId;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
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

	public Boolean getIsStudent() {
		return isStudent;
	}

	public void setIsStudent(Boolean isStudent) {
		this.isStudent = isStudent;
	}

	public Boolean getIsWorking() {
		return isWorking;
	}

	public void setIsWorking(Boolean isWorking) {
		this.isWorking = isWorking;
	}

	public Boolean getIsOpenForWork() {
		return isOpenForWork;
	}

	public void setIsOpenForWork(Boolean isOpenForWork) {
		this.isOpenForWork = isOpenForWork;
	}

	public Boolean getIsProfileVisible() {
		return isProfileVisible;
	}

	public void setIsProfileVisible(Boolean isProfileVisible) {
		this.isProfileVisible = isProfileVisible;
	}

	public Boolean getIsProfileBlocked() {
		return isProfileBlocked;
	}

	public void setIsProfileBlocked(Boolean isProfileBlocked) {
		this.isProfileBlocked = isProfileBlocked;
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

	public String getProfileImagePath() {
		return profileImagePath;
	}

	public void setProfileImagePath(String profileImagePath) {
		this.profileImagePath = profileImagePath;
	}

	public String getField1() {
		return field1;
	}

	public void setField1(String field1) {
		this.field1 = field1;
	}

	public String getField2() {
		return field2;
	}

	public void setField2(String field2) {
		this.field2 = field2;
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

	public List<JobSeekerApplication> getJobSeekerApplication() {
		return jobSeekerApplication;
	}

	public void setJobSeekerApplication(List<JobSeekerApplication> jobSeekerApplication) {
		this.jobSeekerApplication = jobSeekerApplication;
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
	
	
}
