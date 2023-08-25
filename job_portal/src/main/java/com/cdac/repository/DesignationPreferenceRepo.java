package com.cdac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cdac.entity.DesignationPreference;

@Repository
public interface DesignationPreferenceRepo extends JpaRepository<DesignationPreference, Integer>{

}