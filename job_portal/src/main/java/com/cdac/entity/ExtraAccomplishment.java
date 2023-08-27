package com.cdac.entity;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "extra_accomplishments")
public class ExtraAccomplishment {
	
		@Id 
		private int id;

		
	    @ManyToOne
	    @JoinColumn(name = "job_seeker_id")
	    @JsonBackReference
	    private JobSeeker jobSeeker;

	    @Column(name="title")
	    private String title;
	    
	    @Column(name="description_")
	    private String description;

		public ExtraAccomplishment() {
		
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public JobSeeker getJobSeeker() {
			return jobSeeker;
		}

		public void setJobSeeker(JobSeeker jobSeeker) {
			this.jobSeeker = jobSeeker;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}
		
	    
	    

	   
}


