package com.cdac.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.entity.ContactUs;
import com.cdac.repository.ContactUsRepo;


@Service
public class ContactUsService {

	@Autowired
    private  ContactUsRepo contactUs;

   public ContactUs saveMessage(ContactUs contact) {
	  return contactUs.save(contact);
   }
}
