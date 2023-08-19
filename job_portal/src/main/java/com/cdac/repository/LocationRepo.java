package com.cdac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cdac.entity.Location;


@Repository
public interface LocationRepo extends JpaRepository<Location, Integer> {
	public Location findByLocationName(String name);
}
