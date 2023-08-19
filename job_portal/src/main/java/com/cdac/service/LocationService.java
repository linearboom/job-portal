package com.cdac.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.entity.Location;
import com.cdac.repository.LocationRepo;

@Service
public class LocationService {

	@Autowired
	private LocationRepo locationRepo;
	
	/*
	 * Adds Single Location
	 */
	public Location AddLocation(Location location) {
		return locationRepo.save(location);
	}
	
	/*
	 * Will find the location by its given name
	 * Author:
	 * 
	 * Raj
	 */
	public Location FindLocationByName (String locationName) {
		return locationRepo.findByLocationName(locationName);
	}
}
