package com.cdac.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cdac.dto.JobApplicationDTO;
import com.cdac.dto.JobSeekerDTO;
import com.cdac.entity.JobSeeker;
import com.cdac.entity.employer.Employer;
import com.cdac.entity.employer.Job;
import com.cdac.entity.employer.JobPostedSkillSet;
import com.cdac.entity.employer.JobSeekerApplication;
import com.cdac.service.EmailService;
import com.cdac.service.employer.EmployerService;
import com.cdac.service.job.JobPostedSkillSetService;
import com.cdac.service.job.JobSeekerApplicationService;
import com.cdac.service.job.JobService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/employer")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class EmployerController {

	@Autowired
	private EmployerService employerService;

	@Autowired
	private JobService jobService;

	@Autowired
	private JobPostedSkillSetService jobPostedSkillSetService;

	@Autowired
	private JobSeekerApplicationService jobSeekerApplicationService;
	
	
	@Autowired
	private EmailService emailService;

	@PostMapping("login")
	public ResponseEntity<Employer> login(@RequestBody Employer employer, HttpSession session) {
		Employer validatedUser = employerService.validateUser(employer);
		if (validatedUser != null) {
			session.setAttribute("user", validatedUser);
			return new ResponseEntity<>(validatedUser, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(validatedUser, HttpStatus.OK); // Check what will be the status for this
		}
	}
	
	// Ideally login should be in a diffe cont

	@PostMapping("update")
	public ResponseEntity<Employer> update(@RequestBody Employer employer, HttpSession session) {
		Employer validatedUser = (Employer) session.getAttribute("user");
		if (validatedUser != null) {
			Employer updated = employerService.updateData(employer, validatedUser);
			return new ResponseEntity<>(validatedUser, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(validatedUser, HttpStatus.OK); // Check what will be the status for this
		}
	}

	@PostMapping("refresh")
	public ResponseEntity<Employer> validateUser(String name, HttpSession session) {
		Employer seeker = (Employer) session.getAttribute("user");
		if (seeker != null) {
			return new ResponseEntity<Employer>(employerService.findEmployer(seeker.getEmployerId()), HttpStatus.OK);
		}
		return new ResponseEntity<Employer>(seeker, HttpStatus.OK);
	}

	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody Employer employer) {
		employerService.newUser(employer);
		emailService.newRegister("Recruiter");
		return new ResponseEntity<>("Succesful", HttpStatus.OK);
	}

	@PostMapping("postNewJob")
	public ResponseEntity<Job> postNewJob(@RequestBody JobWrapper job, HttpSession session) {
		Employer employer = (Employer) session.getAttribute("user");
		if (employer != null) {
			Job newJob = jobService.postJob(job.getJob(), employer, job.getSkills());
			Job trial = new Job();
			trial.setJobId(newJob.getJobId());
			jobPostedSkillSetService.addJobSkills(job.getSkills(), trial);
			newJob = jobService.findJob(newJob.getJobId());
			return new ResponseEntity<>(newJob, HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@PostMapping("deleteJob")
	public ResponseEntity<String> deleteJob(@RequestBody Job job, HttpSession session) {
		Employer employer = (Employer) session.getAttribute("user");
		if (employer != null) {
			jobService.delete(job);
			return new ResponseEntity<>("Job Sucessfully Deleted", HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@PostMapping("viewApplicants")
	public ResponseEntity<List<JobApplicationDTO>> viewApplicants(@RequestBody Job job, HttpSession session) {
		Employer employer = (Employer) session.getAttribute("user");
		if (employer != null) {
			int jobId = job.getJobId();
			System.out.println(job.getJobTitle());
			List<JobApplicationDTO> applicants = jobSeekerApplicationService.listApplicants(jobId, employer);
			return new ResponseEntity<>(applicants, HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@PostMapping("viewApplicant")
	public ResponseEntity<JobSeekerDTO> viewApplicant(@RequestBody ApplicantWrapper details, HttpSession session) {
		Employer employer = (Employer) session.getAttribute("user");
		if (employer != null) {
			int jobSeekerId = details.getJobSeekerId();
			int applicationId = details.getApplicationId();
			int jobId = details.getJobId();
			System.out.println(applicationId);
			JobSeekerDTO seeker = jobSeekerApplicationService.getApplicantDetails(details, jobSeekerId, applicationId,
					jobId, employer.getEmployerId());
			return new ResponseEntity<>(seeker, HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/updateCompanyProfileImage", consumes = {
			org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE })
	public String updateProfile(@RequestParam("file") MultipartFile file, @RequestParam("employer") String employer,
			HttpSession session) {
		JobSeeker seeker = (JobSeeker) session.getAttribute("user");
		if (seeker != null) {
			try {
				Employer updatedEmployer = new ObjectMapper().readValue(employer, Employer.class);

				String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
				String directoryPath = "C:\\Users\\Dell\\Desktop\\CDAC\\Training\\FINAL PROJECT\\Database\\Image Data";
				createDirectoryIfNotExists(directoryPath); // Create directory if it doesn't exist
				String filePath = directoryPath + "/" + fileName;
				file.transferTo(new File(filePath));
				String encodedPath = URLEncoder.encode(filePath, "UTF-8");
				//employer.setProfileImagePath(encodedPath);
				//employerService.updateProfile(updatedEmployer, seeker);

				return "Profile Updated";
			} catch (IOException e) {
				e.printStackTrace();
				return "Error Updating the  data";
			} catch (Exception e) {
				e.printStackTrace();
				return "Internal Server Error Contact Admin";
			}
		}
		return "Session Expired";
	}

	private void createDirectoryIfNotExists(String directoryPath) throws IOException {
		Path path = Paths.get(directoryPath);
		if (!Files.exists(path)) {
			Files.createDirectories(path);
		}
	}

	@GetMapping("/getProfileImage")
	public ResponseEntity<Resource> getProfileImage(@RequestParam String imagePath) throws IOException {
		// Read the image file as a Resource
		String decodedFilePath = URLDecoder.decode(imagePath, "UTF-8");
		System.out.println(decodedFilePath);
		Resource imageResource = new FileSystemResource(decodedFilePath);

		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG) // Adjust the content type based on your image type
				.body(imageResource);
	}



}
