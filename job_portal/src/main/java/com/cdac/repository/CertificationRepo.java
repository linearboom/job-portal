package com.cdac.repository;

import com.cdac.entity.Certification;
import com.cdac.entity.CertificationEmbed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificationRepo extends JpaRepository<Certification, CertificationEmbed> {

 }
