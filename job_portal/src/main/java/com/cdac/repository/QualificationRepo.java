package com.cdac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cdac.entity.Qualification;

@Repository
public interface QualificationRepo extends JpaRepository<Qualification, Integer> {

}
