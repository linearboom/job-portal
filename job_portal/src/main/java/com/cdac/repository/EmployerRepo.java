package com.cdac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cdac.entity.employer.Employer;


@Repository
public interface EmployerRepo extends JpaRepository<Employer, Integer> {
	public Employer findByEmailAndPassword(String email, String password);
}
