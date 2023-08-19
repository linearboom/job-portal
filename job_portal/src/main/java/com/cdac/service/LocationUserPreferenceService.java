package com.cdac.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.entity.JobSeeker;
import com.cdac.entity.JobSeekerLocation;
import com.cdac.entity.Location;
import com.cdac.entity.LocationUserPreference;
import com.cdac.repository.LocationRepo;
import com.cdac.repository.LocationUserPreferenceRepo;

@Service
public class LocationUserPreferenceService {

	@Autowired
	private LocationUserPreferenceRepo locationUserPreferenceRepo;
	
	@Autowired
	private LocationService locationService;

	/*
	 * This method will Check the Location in the m_location table
	 * If it exists it will just map it into location_user_preference_table
	 * If it does not then it will add the location and then it will map it
	 * 
	 * Author :
	 * Raj
	 */
	public void addPreference(Location location, JobSeeker jobSeeker) {
		Location locationDB = locationService.FindLocationByName(location.getLocationName());
		if (locationDB != null) {
			LocationUserPreference locationUserPreference =  new LocationUserPreference();
			int job_seeker_id = jobSeeker.getJobSeekerId();
			int location_id = locationDB.getLocationID();
			locationUserPreference.setJobSeeker(jobSeeker);
			locationUserPreference.setLocation(locationDB);
			locationUserPreference.setJobSeekerLocation(new JobSeekerLocation(job_seeker_id, location_id));
			locationUserPreferenceRepo.save(locationUserPreference);
		}else {
			Location locationNew = new Location();
			LocationUserPreference locationUserPreference =  new LocationUserPreference();
			locationNew.setLocationName(location.getLocationName());
			locationNew.setLocationApproved('0');
			locationDB = locationService.AddLocation(locationNew);
			int job_seeker_id = jobSeeker.getJobSeekerId();
			int location_id = locationDB.getLocationID();
			locationUserPreference.setJobSeeker(jobSeeker);
			locationUserPreference.setLocation(locationDB);
			locationUserPreference.setJobSeekerLocation(new JobSeekerLocation(job_seeker_id, location_id));
			locationUserPreferenceRepo.save(locationUserPreference);
			
		}
	}
	
	
}
