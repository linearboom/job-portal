package com.cdac.service.employer;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.entity.JobSeeker;
import com.cdac.entity.employer.Employer;
import com.cdac.repository.EmployerRepo;

@Service
public class EmployerService {

	@Autowired
	private EmployerRepo employerRepo;
	
	/*
	 * Find the employer by email and password
	 */
	public Employer validateUser(Employer employer) {
		return employerRepo.findByEmailAndPassword(employer.getEmail(), employer.getPassword());
	}

	public Employer newUser(Employer employer) {
		return employerRepo.save(employer);
		
	}

	public Employer findEmployer(int employerId) {
		Optional<Employer> seeker =  employerRepo.findById(employerId);
		if (seeker.isPresent()) {
			return seeker.get();
		}
		return null;
	}

	public Employer updateData(Employer employer, Employer validatedUser) {
		validatedUser.setAddress(employer.getAddress());
		validatedUser.setFirstName(employer.getFirstName());
		validatedUser.setLastName(employer.getLastName());
		validatedUser.setMobile(employer.getMobile());
		validatedUser.setAddress(employer.getAddress());
		return employerRepo.save(validatedUser);
	}
}
