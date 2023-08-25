package com.cdac.service.job;

import java.sql.Date;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.entity.JobSeeker;
import com.cdac.entity.employer.Job;
import com.cdac.entity.employer.JobSeekerApplication;
import com.cdac.repository.job.JobSeekerApplicationRepo;

@Service
public class JobSeekerApplicationService {
	
	@Autowired
	private JobSeekerApplicationRepo jobSeekerApplicationRepo;
	
	
	public JobSeekerApplication applyJob(JobSeekerApplication application, JobSeeker jobSeeker ) {
		JobSeekerApplication ApplicationExists =  jobSeekerApplicationRepo.findByJobSeekerAndJob(jobSeeker, application.getJob());
		if (ApplicationExists != null) {
			return null;
		}
		// Get the current timestamp in milliseconds
        long currentTimestampMillis = System.currentTimeMillis();

        // Convert the timestamp to a java.util.Date
        java.util.Date utilDate = new java.util.Date(currentTimestampMillis);

        // Format the java.util.Date to a string in "yyyy-MM-dd" format
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = sdf.format(utilDate);

        // Convert the formatted date string to a java.sql.Date
        Date sqlDate = Date.valueOf(formattedDate);
		application.setApplyDate(sqlDate);
		
		application.setJobSeeker(jobSeeker);
		return jobSeekerApplicationRepo.save(application);
	}
}
