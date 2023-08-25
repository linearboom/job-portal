package com.cdac.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.crypto.Data;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "education_detail")
public class EducationDetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "education_id")
	private int educationId;

	@ManyToOne
	@JoinColumn(name = "job_seeker_id")
	@JsonIgnore // Ignores back track
	private JobSeeker jobSeeker;

	@Column(name = "degree_name", nullable = false)
	private String degreeName;

	private String major;

	@ManyToOne
	@JoinColumn(name = "qualification_type_id", nullable = false)
	private Qualification qualification;
	
	@Column(name = "percentage")
	private Float percentage;

	@Column(name = "university_name", nullable = false)
	private String universityName;

	@Column(name = "start_year", nullable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date startYear;

	@Column(name = "end_year",  nullable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date endYear;
	
	
	public EducationDetail() {
		//TODO
	}

	public int getEducationId() {
		return educationId;
	}

	public void setEducationId(int educationId) {
		this.educationId = educationId;
	}

	public JobSeeker getJobSeeker() {
		return jobSeeker;
	}

	public void setJobSeeker(JobSeeker jobSeeker) {
		this.jobSeeker = jobSeeker;
	}

	public String getDegreeName() {
		return degreeName;
	}

	public void setDegreeName(String degreeName) {
		this.degreeName = degreeName;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public Qualification getQualification() {
		return qualification;
	}

	public void setQualification(Qualification qualification) {
		this.qualification = qualification;
	}

	public Float getPercentage() {
		return percentage;
	}

	public void setPercentage(Float percentage) {
		this.percentage = percentage;
	}

	public String getUniversityName() {
		return universityName;
	}

	public void setUniversityName(String universityName) {
		this.universityName = universityName;
	}

	public Date getStartYear() {
		return startYear;
	}

	public void setStartYear(Date startYear) {
		this.startYear = startYear;
	}

	public Date getEndYear() {
		return endYear;
	}

	public void setEndYear(Date endYear) {
		this.endYear = endYear;
	}
	
	
	
}
