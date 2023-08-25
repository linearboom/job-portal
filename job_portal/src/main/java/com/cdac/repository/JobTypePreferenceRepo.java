package com.cdac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cdac.entity.JobTypePreference;


@Repository
public interface JobTypePreferenceRepo extends JpaRepository<JobTypePreference, Integer> {

}
