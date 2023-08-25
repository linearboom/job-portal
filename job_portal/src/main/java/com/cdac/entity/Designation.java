package com.cdac.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GeneratorType;

@Entity
@Table(name="m_designation")
public class Designation implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "designation_id")
	private int designationID;
	
	
	@Column(name = "designation_name")
	private String designationName;
	
	@Column(name = "designation_approved")
	private char DesignationApproved;
	
	public Designation() {
		// TO do
	}

	

	public Designation(String designationName, char designationApproved) {
		
		this.designationName = designationName;
		DesignationApproved = designationApproved;
	}



	public int getDesignationID() {
		return designationID;
	}

	public void setDesignationID(int designationID) {
		this.designationID = designationID;
	}

	public String getDesignationName() {
		return designationName;
	}

	public void setDesignationName(String designationName) {
		this.designationName = designationName;
	}

	public char getDesignationApproved() {
		return DesignationApproved;
	}

	public void setDesignationApproved(char designationApproved) {
		DesignationApproved = designationApproved;
	}

	
	
}

