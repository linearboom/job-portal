package com.cdac.service.job;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.entity.JobSeekerSkillId;
import com.cdac.entity.SkillSet;
import com.cdac.entity.UserSkillSet;
import com.cdac.entity.employer.Employer;
import com.cdac.entity.employer.Job;
import com.cdac.entity.employer.JobPostedSkillSet;
import com.cdac.entity.employer.JobPostedSkillSetId;
import com.cdac.repository.job.JobPostedSkillSetRepo;
import com.cdac.service.SkillSetService;

@Service
public class JobPostedSkillSetService {
	
	@Autowired
	private JobPostedSkillSetRepo jobPostedSkillSetRepo;
	
	@Autowired
	private SkillSetService skillSetService;
	
	@Transactional
	public int addJobSkills(List<SkillSet> skills, Job job) {
		SkillSet skillDB;
		List<JobPostedSkillSet> newSkills = new ArrayList<JobPostedSkillSet>();
		job.setSkills(newSkills);
		int count = 0 ;
		for (SkillSet skill :  skills) {
			JobPostedSkillSet jobSkill = new JobPostedSkillSet();
			// System.out.println("Debug Loop");
			if ((skillDB = skillSetService.findSkillByName(skill.getSkillName())) == null) {
				// If the Skill not exists in the Database Just add it to that DB and then map it
				skillDB = skillSetService.addSkill(new SkillSet(skill.getSkillName(), '0'));
				count += 1;
			}
			
			int JobId = job.getJobId();
			int SkillSetId = skillDB.getSkillSetId();
			jobSkill.setSkillSet(skillDB);
			jobSkill.setJob(job);
			jobSkill.setId(new JobPostedSkillSetId(JobId, SkillSetId));
			newSkills.add(jobSkill);
			
			
			System.out.println("Debug JobPostedSkillSet");
			//Employer employer = new Employer();
			//employer.setEmployerId(job.getEmployer().getEmployerId());
			//job.setEmployer(employer);
			//System.out.println(job.getEmployer().getEmployerId());
			jobPostedSkillSetRepo.save(jobSkill);
			
		}
	
		return count;
	}

}
