package com.cdac.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.cdac.entity.JobSeeker;
import com.cdac.entity.employer.Job;

@Service
public class EmailService {
	 private final JavaMailSender javaMailSender;

	    @Autowired
	    public EmailService(JavaMailSender javaMailSender) {
	        this.javaMailSender = javaMailSender;
	    }

	    //From is configured in Application Properites. Always the same
	    public void sendEmail(String to, String subject, String text) {
	        SimpleMailMessage message = new SimpleMailMessage();
	        message.setTo(to);
	        message.setSubject(subject);
	        message.setText(text);
	        javaMailSender.send(message);
	    }
	    
	    
	    //Use this in development to avoid sending email to anyone
	    public void sendEmailDefault(String subject, String text) {
	    	 SimpleMailMessage message = new SimpleMailMessage();
		        message.setTo("raj.chaudharii1998@gmail.com");
		        message.setSubject(subject);
		        message.setText(text);
		        javaMailSender.send(message);
	    }
	    
	    public void newRegister(String type) {
	    	String message = "A New "+type+ " has registered on your website";
	    	String subject = "New "+ type+"Registration Alert";
	    	sendEmailDefault(subject, message);
	    }

		public void notifySeeker(Job job, JobSeeker seeker) {
			String subject = "A Recruiter has contacted you!";
			String message = "Your Application for the Job : "+job.getJobTitle()+
					" has been noticed"+
					"\n Job Id : " + job.getJobId()+
					"\n Application Date : "+ job.getJobPostingDate() +
					"\n The Recruiter has viewed your contact and will contact you shortly";
			sendEmailDefault(subject, message);
	
			
		}

		
}
