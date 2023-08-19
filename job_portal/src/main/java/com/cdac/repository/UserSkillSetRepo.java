package com.cdac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cdac.entity.UserSkillSet;

@Repository
public interface UserSkillSetRepo extends JpaRepository<UserSkillSet, Integer>{
	
}
