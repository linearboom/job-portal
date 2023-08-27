package com.cdac.service;

import com.cdac.entity.Certification;
import com.cdac.entity.CertificationEmbed;
import com.cdac.entity.JobSeeker;
import com.cdac.repository.CertificationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CertificationService {

	 @Autowired
    private  CertificationRepo certificationRepo;

    public Certification addCertificate(Certification certificate, JobSeeker jobSeeker) {
    	certificate.setJobSeeker(jobSeeker);
    	certificate.getId().setJobSeekerId(jobSeeker.getJobSeekerId());
        return certificationRepo.save(certificate);
    }
    
}
