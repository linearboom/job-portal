package com.cdac.entity.employer;

import java.util.Objects;

import javax.persistence.*;

import com.cdac.entity.SkillSet;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "job_posted_skillsets")
public class JobPostedSkillSet {

    @EmbeddedId
    private JobPostedSkillSetId id;

    @ManyToOne()
    @MapsId("jobId")
    @JoinColumn(name = "job_id", nullable = false)
    @JsonIgnore
    private Job job;

    @ManyToOne()
    @MapsId("skillSetId")
    @JoinColumn(name = "skill_set_id", nullable = false)
    private SkillSet skillSet;

    public JobPostedSkillSet() {
        // Default constructor
    }

    public JobPostedSkillSet(Job job, SkillSet skillSet) {
        this.job = job;
        this.skillSet = skillSet;
        this.id = new JobPostedSkillSetId(job.getJobId(), skillSet.getSkillSetId());
    }

    // Getters and setters
    
    public JobPostedSkillSetId getId() {
		return id;
	}

	public void setId(JobPostedSkillSetId id) {
		this.id = id;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public SkillSet getSkillSet() {
		return skillSet;
	}

	public void setSkillSet(SkillSet skillSet) {
		this.skillSet = skillSet;
	}
    
	
	//Hashcode and Equals
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobPostedSkillSet that = (JobPostedSkillSet) o;
        return Objects.equals(job, that.job) &&
               Objects.equals(skillSet, that.skillSet);
    }

    

	@Override
    public int hashCode() {
        return Objects.hash(job, skillSet);
    }

   
}

