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
@Table(name="m_location")
public class Location implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "location_id")
	private int locationID;
	
	
	@Column(name = "location_name")
	private String locationName;
	
	@Column(name = "location_approved")
	private char LocationApproved;
	
	public Location() {
		// TO do
	}

	public int getLocationID() {
		return locationID;
	}

	public void setLocationID(int locationID) {
		this.locationID = locationID;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public char getLocationApproved() {
		return LocationApproved;
	}

	public void setLocationApproved(char locationApproved) {
		LocationApproved = locationApproved;
	}
	
	
}
