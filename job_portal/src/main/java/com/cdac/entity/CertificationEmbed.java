package com.cdac.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CertificationEmbed implements Serializable {

    @Column(name = "job_seeker_id")
    private int jobSeekerId;

    @Column(name = "certification_name")
    private String certificationName;

    public CertificationEmbed() {
		
	}
    
    

	public CertificationEmbed(int jobSeekerId, String certificationName) {
		this.jobSeekerId = jobSeekerId;
		this.certificationName = certificationName;
	}

    
	public int getJobSeekerId() {
		return jobSeekerId;
	}

	public void setJobSeekerId(int jobSeekerId) {
		this.jobSeekerId = jobSeekerId;
	}
	

	
	public String getCertificationName() {
		return certificationName;
	}

	public void setCertificationName(String certificationName) {
		this.certificationName = certificationName;
	}

    
}
