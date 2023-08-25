package com.cdac.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "qualification_type")
public class Qualification {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="qualification_type_id")
	private int qualificationId;
	
	@Column(name="qualification_name", nullable = false)
	private String qualificarionName;
	
	public Qualification() {
		
	}

	public Qualification(int qualificationId, String qualificarionName) {
		this.qualificationId = qualificationId;
		this.qualificarionName = qualificarionName;
	}

	public int getQualificationId() {
		return qualificationId;
	}

	public void setQualificationId(int qualificationId) {
		this.qualificationId = qualificationId;
	}

	public String getQualificarionName() {
		return qualificarionName;
	}

	public void setQualificarionName(String qualificarionName) {
		this.qualificarionName = qualificarionName;
	}
	
	
}
