package com.cdac.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "m_skillset")
public class SkillSet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "skill_set_id")
	private int skillSetId;

	@Column(name = "skill_name")
	private String skillName;

	@Column(name = "skill_approved")
	private char skillApproved;
	
	public SkillSet() {
	 // No Args Constructor
	}
	
	public SkillSet(String name, char skillApproved) {
		this.skillName =  name;
		this.skillApproved = skillApproved;
	}

	public int getSkillSetId() {
		return skillSetId;
	}

	public void setSkillSetId(int skillSetId) {
		this.skillSetId = skillSetId;
	}

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	public char getSkillApproved() {
		return skillApproved;
	}

	public void setSkillApproved(char skillApproved) {
		this.skillApproved = skillApproved;
	}
	
	
}
