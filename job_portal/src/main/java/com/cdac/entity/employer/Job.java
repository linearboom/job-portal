package com.cdac.entity.employer;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.cdac.entity.Designation;
import com.cdac.entity.JobType;
import com.cdac.entity.Location;
import com.cdac.entity.Qualification;
import com.cdac.entity.SkillSet;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "job_posted")
public class Job {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "job_id")
	private int jobId;
	//Keep it as no cascade 
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "employer_id", nullable = false)
	@JsonIgnore
	private Employer employer;

	@Column(name = "isactive", nullable = false)
	private boolean isActive;

	@Column(name = "job_title", nullable = false, length = 50)
	private String jobTitle;

	@Column(name = "job_description", nullable = false, length = 1000)
	private String jobDescription;

	@Column(name = "post_avail", nullable = false, columnDefinition = "int default 1")
	private int postAvail;

	@ManyToOne
	@JoinColumn(name = "job_type")
	private JobType jobType;
	
	
	//Change : Breaking
	@OneToMany(mappedBy = "job",cascade = CascadeType.ALL)
	private List<JobPostedSkillSet> skills;

	
	@ManyToOne
	@JoinColumn(name = "designation_id")
	private Designation designation;

//	@ManyToOne
//	@JoinColumn(name = "industry_id")
//	private Industry industry;
	
	@Column(name = "industry_id")	
	private int industryId;

	@Column(name = "wfh_preference")
	private int wfhPreference;

	@OneToOne
	@JoinColumn(name = "location_id")
	private Location location;

	@Column(name = "job_posting_date", nullable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date jobPostingDate;

	@Column(name = "last_apply_date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date lastApplyDate;
	

	@Column(name = "min_salary")
	private Double minSalary;

	@Column(name = "max_salary")
	private Double maxSalary;

	@Column(name = "min_experience")
	private Integer minExperience;

	@Column(name = "max_experience")
	private Integer maxExperience;

	@ManyToOne
	@JoinColumn(name = "qualification_type")
	private Qualification qualificationType;

	@ManyToOne
	@JoinColumn(name = "qualification_type_2")
	private Qualification qualificationType2;

	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public Employer getEmployer() {
		return employer;
	}

	public void setEmployer(Employer employer) {
		this.employer = employer;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	// Change
	public List<JobPostedSkillSet> getSkills() {
		return skills;
	}

	public void setSkills(List<JobPostedSkillSet> skills) {
		this.skills = skills;
	}

	

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public int getPostAvail() {
		return postAvail;
	}

	public void setPostAvail(int postAvail) {
		this.postAvail = postAvail;
	}

	public JobType getJobType() {
		return jobType;
	}

	public void setJobType(JobType jobType) {
		this.jobType = jobType;
	}

	public Designation getDesignation() {
		return designation;
	}

	public void setDesignation(Designation designation) {
		this.designation = designation;
	}

	public int getIndustryId() {
		return industryId;
	}

	public void setIndustryId(int industryId) {
		this.industryId = industryId;
	}

	public int getWfhPreference() {
		return wfhPreference;
	}

	public void setWfhPreference(int wfhPreference) {
		this.wfhPreference = wfhPreference;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Date getJobPostingDate() {
		return jobPostingDate;
	}

	public void setJobPostingDate(Date jobPostingDate) {
		this.jobPostingDate = jobPostingDate;
	}

	public Date getLastApplyDate() {
		return lastApplyDate;
	}

	public void setLastApplyDate(Date lastApplyDate) {
		this.lastApplyDate = lastApplyDate;
	}

	public Double getMinSalary() {
		return minSalary;
	}

	public void setMinSalary(Double minSalary) {
		this.minSalary = minSalary;
	}

	public Double getMaxSalary() {
		return maxSalary;
	}

	public void setMaxSalary(Double maxSalary) {
		this.maxSalary = maxSalary;
	}

	public Integer getMinExperience() {
		return minExperience;
	}

	public void setMinExperience(Integer minExperience) {
		this.minExperience = minExperience;
	}

	public Integer getMaxExperience() {
		return maxExperience;
	}

	public void setMaxExperience(Integer maxExperience) {
		this.maxExperience = maxExperience;
	}

	public Qualification getQualificationType() {
		return qualificationType;
	}

	public void setQualificationType(Qualification qualificationType) {
		this.qualificationType = qualificationType;
	}

	public Qualification getQualificationType2() {
		return qualificationType2;
	}

	public void setQualificationType2(Qualification qualificationType2) {
		this.qualificationType2 = qualificationType2;
	}
	
	
	
	
}
