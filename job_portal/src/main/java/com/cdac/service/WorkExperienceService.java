package com.cdac.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.entity.JobSeeker;
import com.cdac.entity.WorkExperience;
import com.cdac.repository.WorkExperienceRepo;

import java.util.List;

@Service
public class WorkExperienceService {

	 @Autowired
    private WorkExperienceRepo workExperience;

   
    

    public List<WorkExperience> getAllWorkExperiences() {
        return workExperience.findAll();
    }

    public WorkExperience getWorkExperienceById(int id) {
        return workExperience.findById(id).orElse(null);
    }

    public WorkExperience addWorkExperience(WorkExperience experience, JobSeeker jobSeeker) {
    	experience.setJobSeeker(jobSeeker);
    	
    	
    	return workExperience.save(experience);
    }

    public void deleteWorkExperience(int id) {
    	workExperience.deleteById(id);
    }

    
}

