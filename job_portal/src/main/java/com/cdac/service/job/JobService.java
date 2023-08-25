package com.cdac.service.job;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.entity.Designation;
import com.cdac.entity.Location;
import com.cdac.entity.SkillSet;
import com.cdac.entity.employer.Employer;
import com.cdac.entity.employer.Job;
import com.cdac.entity.employer.JobPostedSkillSet;
import com.cdac.repository.EmployerRepo;
import com.cdac.repository.job.JobRepo;
import com.cdac.service.DesignationService;
import com.cdac.service.LocationService;

@Service
public class JobService {
	
	@Autowired
	private JobRepo jobRepo;
	
	@Autowired
	
	private EmployerRepo employerRepo;
	
	@Autowired
	private DesignationService designationService;
	
	@Autowired 
	private JobPostedSkillSetService jobPostedSkillSetService;
	
	@Autowired
	private LocationService locationService;
	
	/*
	 * Posts a new job uses the employer from session management
	 */
	@Transactional
	public Job postJob(Job job, Employer employer, List<SkillSet> skills) {
		
		job.setEmployer(employer);
		job.setIsActive(true);
		// Get the current timestamp in milliseconds
        long currentTimestampMillis = System.currentTimeMillis();

        // Convert the timestamp to a java.util.Date
        java.util.Date utilDate = new java.util.Date(currentTimestampMillis);

        // Format the java.util.Date to a string in "yyyy-MM-dd" format
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = sdf.format(utilDate);

        // Convert the formatted date string to a java.sql.Date
        Date sqlDate = Date.valueOf(formattedDate);
		job.setJobPostingDate(sqlDate);
		
		// Addign Designation
		Designation designation = job.getDesignation();
		String designationName = designation.getDesignationName();
		designation = designationService.findByName(designationName);
		if (designation != null) {
			job.setDesignation(designation);
		}else {
			designation = new Designation();
			designation.setDesignationApproved('0');
			designation.setDesignationName(designationName);
			designationService.addDesignation(designation);
			job.setDesignation(designation);
		}
		
		//Adding Location
		Location location = job.getLocation();
		String locationName =  location.getLocationName();
		location = locationService.FindLocationByName(locationName);
		if(location == null) {
			location = new Location();
			location.setLocationName(locationName);
			location.setLocationApproved('0');
			locationService.AddLocation(location);
		}
		job.setLocation(location);
		
		// Set the skills properly and add and save them
		System.out.println("Debug Before");
		
		//jobPostedSkillSetService.addJobSkills(skills, job);
		System.out.println("Debug After");
		Job savedJob = jobRepo.save(job);
		return savedJob;
		
	}
	
	
	/*
	 * List of All Jobs
	 * For Now used for testing
	 */
	public List<Job> listAll(){
		return jobRepo.findAll();
	}
	
	

	/*
	 * Returns a list  of All Jobs Matching the starting String
	 * Used for initial search parameter
	 */
	public List<Job> searchByTitle(String title){
		return jobRepo.findByJobTitle(title);
	}


	public List<Job> findContainingTitle(String titleName) {
		return jobRepo.findByJobTitleStartingWithIgnoreCase(titleName);
	}


	
	


}
