package com.cdac.entity.employer;
import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class JobPostedSkillSetId implements Serializable {

    @Column(name = "job_id")
    private int jobId;

    @Column(name = "skill_set_id")
    private int skillSetId;

    public JobPostedSkillSetId() {
        // Default constructor
    }

    public JobPostedSkillSetId(int jobId, int skillSetId) {
        this.jobId = jobId;
        this.skillSetId = skillSetId;
    }

    // Implement equals and hashCode methods
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobPostedSkillSetId that = (JobPostedSkillSetId) o;
        return jobId == that.jobId &&
               skillSetId == that.skillSetId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(jobId, skillSetId);
    }

	

    // Getters and setters
    
    public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public int getSkillSetId() {
		return skillSetId;
	}

	public void setSkillSetId(int skillSetId) {
		this.skillSetId = skillSetId;
	}
    
}

