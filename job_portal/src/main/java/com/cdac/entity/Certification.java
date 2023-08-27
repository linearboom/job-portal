package com.cdac.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.io.Serializable;

@Entity
@Table(name = "certifications")
public class Certification implements Serializable {

    @EmbeddedId
    private CertificationEmbed id;

    @ManyToOne
    @MapsId("jobSeekerId")
    @JoinColumn(name = "job_seeker_id")
    @JsonBackReference
    private JobSeeker jobSeeker;

    @Column(name = "certification_description")
    private String certificationDescription;

    @Column(name = "url")
    private String url;

	public Certification() {
		
	}

	public CertificationEmbed getId() {
		return id;
	}

	public void setId(CertificationEmbed id) {
		this.id = id;
	}

	public JobSeeker getJobSeeker() {
		return jobSeeker;
	}

	public void setJobSeeker(JobSeeker jobSeeker) {
		this.jobSeeker = jobSeeker;
	}

	public String getCertificationDescription() {
		return certificationDescription;
	}

	public void setCertificationDescription(String certificationDescription) {
		this.certificationDescription = certificationDescription;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	
    
}
