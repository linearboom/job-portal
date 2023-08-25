package com.cdac.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.entity.Designation;
import com.cdac.repository.DesignationRepo;

@Service
public class DesignationService {

	@Autowired
	private DesignationRepo designationRepo;
	
	/*
	 * Adds a designation
	 * The calling method should take care of the designation_approved
	 */
	public Designation addDesignation(Designation designation) {
		return designationRepo.save(designation);
	}

	public Designation findByName(String designationName) {
		return designationRepo.findByDesignationName(designationName);
	}

	
	public List<Designation> searchByName(String designation) {
		return designationRepo.findByDesignationNameStartingWithIgnoreCase(designation);
		
	}
}
