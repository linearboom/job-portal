package com.cdac.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.entity.EducationDetail;
import com.cdac.entity.JobSeeker;
import com.cdac.repository.EducationDetailRepo;

@Service
public class EducationDetailService {

	@Autowired
	private EducationDetailRepo educationDetailRepo;
	
	public EducationDetail addEducation(EducationDetail educationDetail, JobSeeker jobSeeker) {
		//Validation for the Qualification and the entire code here.
		educationDetail.setJobSeeker(jobSeeker);
		return educationDetailRepo.save(educationDetail);
		
	}

	public void deleteEducation(EducationDetail education, JobSeeker jobSeeker) {
		// TODO Auto-generated method stub
		educationDetailRepo.delete(education);
		
	}
}
