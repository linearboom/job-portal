package com.cdac.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.entity.JobSeeker;
import com.cdac.entity.JobSeekerSkillId;
import com.cdac.entity.SkillSet;
import com.cdac.entity.UserSkillSet;
import com.cdac.repository.UserSkillSetRepo;

@Service
public class UserSkillSetService {
	
	@Autowired
	private UserSkillSetRepo userSkillSetRepo;
	
	@Autowired
	private SkillSetService skillSetService;
	
	/*
	 * Method to add User Skills to the table.
	 * Will check if the skill exists in the m_skillset table.
	 * If it does not exist. It will add the skill to the table and then add it to the userskills table
	 * If the skill is already mapped it will not add.
	 * 
	 * Author :
	 * Raj
	 */
	public int addUserSkills(List<SkillSet> skills, JobSeeker jobSeeker) {
		int count = 0;
		SkillSet skillDB;
		System.out.println("Debug");
		for (SkillSet skill :  skills) {
			UserSkillSet skillNew = new UserSkillSet();
			System.out.println("Debug Loop");
			if ((skillDB = skillSetService.findSkillByName(skill.getSkillName())) != null) {
				 //Add the existing and mapped skill set to the user table
				int job_seeker_id = jobSeeker.getJobSeekerId();
				int skill_set_id = skillDB.getSkillSetId();
				skillNew.setJobSeekerSkillId(new JobSeekerSkillId(job_seeker_id, skill_set_id));
//				System.out.println(skillNew.getJobSeekerSkillId().getJobSeekerId());
//				System.out.println(skillNew.getJobSeekerSkillId().getSkillSetId());
				skillNew.setJobSeeker(jobSeeker);
				jobSeeker.getUserSkillSets().add(skillNew);
				skillNew.setSkill(skillDB);
				System.out.println("Debug if");
				userSkillSetRepo.save(skillNew);
				
			}else {
				// Add a new Skill set to the skill set table
				// Then map this new skill set to the user_skillset table
				skillDB =  skillSetService.addSkill(new SkillSet(skill.getSkillName(),'0'));
				int job_seeker_id = jobSeeker.getJobSeekerId();
				int skill_set_id = skillDB.getSkillSetId();
				skillNew.setJobSeekerSkillId(new JobSeekerSkillId(job_seeker_id, skill_set_id)); 
				skillNew.setJobSeeker(jobSeeker);
				jobSeeker.getUserSkillSets().add(skillNew);
				skillNew.setSkill(skillDB);
				userSkillSetRepo.save(skillNew);
				count +=1;
				System.out.println("Debug else");
			}
		}
		return count;
	}

	/*
	 * Deletes the user skill from that user.
	 */
	public void deleteUserSkill(UserSkillSet skill, JobSeeker jobSeeker) {
		if (skill == null || jobSeeker == null)
			throw new NullPointerException("Null Skill value or JobSeeker Value passed");
		userSkillSetRepo.delete(skill);
		
	}
	
}
