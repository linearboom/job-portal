package com.cdac.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.entity.Qualification;
import com.cdac.repository.QualificationRepo;

@Service
public class QualificationService {

	@Autowired
	private QualificationRepo qualificationRepo;

	public List<Qualification> findAll() {
		return qualificationRepo.findAll();
	}
}
