package com.cdac.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.entity.Designation;
import com.cdac.entity.DesignationComposite;
import com.cdac.entity.DesignationPreference;
import com.cdac.entity.JobSeeker;
import com.cdac.repository.DesignationPreferenceRepo;

@Service
public class DesignationPreferenceService {

	@Autowired
	private DesignationPreferenceRepo designationPreferenceRepo;
	
	@Autowired
	private DesignationService designationService;

	public DesignationPreference addDesignationUser(Designation designation, JobSeeker jobSeeker) {
		 Designation designationDB = designationService.findByName(designation.getDesignationName());
		 DesignationPreference preference = new DesignationPreference();
		 int jobSeekerId = jobSeeker.getJobSeekerId();
		 int designationId;
		 preference.setJobSeeker(jobSeeker);
		 if (designationDB == null) {
			 designationDB = designationService.addDesignation(new Designation(designation.getDesignationName(), '0'));
		 }
		 designationId = designationDB.getDesignationID();
		 preference.setDesignation(designationDB);
		 preference.setJobSeeker(jobSeeker);
		 preference.setDesignationComposite(new DesignationComposite(jobSeekerId, designationId));
		 return designationPreferenceRepo.save(preference);
	}
	
	
}
