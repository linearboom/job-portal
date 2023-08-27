package com.cdac.service;

import com.cdac.entity.ExtraAccomplishment;
import com.cdac.entity.JobSeeker;
import com.cdac.repository.ExtraAccomplishmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExtraAccomplishmentService {

	@Autowired
    private  ExtraAccomplishmentRepo extraAccomplishment;

    public List<ExtraAccomplishment> getAllExtraAccomplishments() {
        return extraAccomplishment.findAll();
    }

    public ExtraAccomplishment addAccomplishment(ExtraAccomplishment accomplishment, JobSeeker jobSeeker) {
    	accomplishment.setJobSeeker(jobSeeker);
    	return extraAccomplishment.save(accomplishment);
    }

    public void deleteAccomplishment(int id) {
    	extraAccomplishment.deleteById(id);
    }


}
