package com.cdac.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cdac.entity.Designation;

public interface DesignationRepo extends JpaRepository<Designation, Integer> {
	public Designation findByDesignationName(String  designationName);
	public List<Designation> findByDesignationNameStartingWithIgnoreCase(String designation);
}
