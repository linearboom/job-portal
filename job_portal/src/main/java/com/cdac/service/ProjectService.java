package com.cdac.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.entity.JobSeeker;
import com.cdac.entity.Project;
import com.cdac.entity.ProjectId;
import com.cdac.repository.ProjectRepo;

import java.util.List;

@Service
public class ProjectService {

	@Autowired
	private ProjectRepo projectRepo;

	public List<Project> getAllProjects() {
		return projectRepo.findAll();
	}

	public Project addProject(Project projects, JobSeeker jobSeeker) {
		ProjectId id = projects.getId();
		id.setJobSeekerId(jobSeeker.getJobSeekerId());
		id.setProjectTitle(projects.getId().getProjectTitle());
		projects.setJobSeeker(jobSeeker);
		return projectRepo.save(projects);
	}

	public void deleteProject(Project project) {
		projectRepo.delete(project);
	}

}
