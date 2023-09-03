package com.cdac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.entity.ContactUs;
import com.cdac.service.ContactUsService;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class GuestController {
	
	@Autowired
	private ContactUsService contactUsService;
	
	@PostMapping("/contactus")
	public String contactUs(@RequestBody ContactUs contact) {
		ContactUs updated =  contactUsService.saveMessage(contact);
		return "Your Query has been recorded! QueryNumber :  "+updated.getId();
	}
}
