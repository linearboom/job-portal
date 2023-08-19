package com.cdac.repository;

import java.util.List;

import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cdac.entity.SkillSet;


@Repository
public interface SkillSetRepo extends JpaRepository<SkillSet, Integer> {
	 public List<SkillSet> findBySkillNameContainingIgnoreCase(String skillName);
	 public SkillSet findBySkillName(String skillName);
}
