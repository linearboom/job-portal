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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;

import com.cdac.entity.Certification;
import com.cdac.entity.Designation;
import com.cdac.entity.EducationDetail;
import com.cdac.entity.ExtraAccomplishment;
import com.cdac.entity.JobSeeker;
import com.cdac.entity.JobType;
import com.cdac.entity.JobTypePreference;
import com.cdac.entity.Location;

import com.cdac.entity.Project;
import com.cdac.entity.Qualification;
import com.cdac.entity.SkillSet;
import com.cdac.entity.UserSkillSet;
import com.cdac.entity.WorkExperience;
import com.cdac.entity.employer.Job;
import com.cdac.entity.employer.JobSeekerApplication;
import com.cdac.repository.SkillSetRepo;
import com.cdac.service.CertificationService;
import com.cdac.service.DesignationPreferenceService;
import com.cdac.service.DesignationService;
import com.cdac.service.EducationDetailService;
import com.cdac.service.ExtraAccomplishmentService;
import com.cdac.service.JobSeekerService;
import com.cdac.service.JobTypePreferenceService;
import com.cdac.service.JobTypeService;
import com.cdac.service.LocationUserPreferenceService;
import com.cdac.service.ProjectService;
import com.cdac.service.QualificationService;
import com.cdac.service.SkillSetService;
import com.cdac.service.UserSkillSetService;
import com.cdac.service.WorkExperienceService;
import com.cdac.service.job.JobSeekerApplicationService;
import com.cdac.service.job.JobService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/job_seeker")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class JobSeekerController {

	@Autowired
	private JobSeekerService jobSeekerService;

	@Autowired
	private SkillSetService skillSetService;

	@Autowired
	private UserSkillSetService userSkillSetService;

	@Autowired
	private LocationUserPreferenceService locationUserPreferenceService;

	@Autowired
	private QualificationService qualificationService;

	@Autowired
	private EducationDetailService educationDetailService;

	@Autowired
	private JobTypePreferenceService jobTypePreferenceService;

	@Autowired
	private JobTypeService jobTypeService;

	@Autowired
	private DesignationPreferenceService designationPreferenceService;

	@Autowired
	private DesignationService designationService;

	@Autowired
	private JobService jobService;

	@Autowired
	private JobSeekerApplicationService jobSeeeApplicationService;

	@Autowired
	private WorkExperienceService workExperienceService;

	@Autowired
	private ProjectService projectService;

	@Autowired
	private ExtraAccomplishmentService extraAccomplishmentService;

	@Autowired
	private CertificationService certificationService;

	@GetMapping("/hello")
	public String hello() {
		return "Hello";
	}

	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody JobSeeker jobSeeker) {
		jobSeekerService.newUser(jobSeeker);
		return new ResponseEntity<>("Succesful", HttpStatus.OK);
	}

	@PostMapping("login")
	public ResponseEntity<JobSeeker> login(@RequestBody JobSeeker jobSeeker, HttpSession session) {
		JobSeeker validatedUser = jobSeekerService.login(jobSeeker);
		if (validatedUser != null) {
			session.setAttribute("user", validatedUser);
			return new ResponseEntity<>(validatedUser, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(validatedUser, HttpStatus.OK); // Check what will be the status for this
		}
	}

	@PostMapping("refresh")
	public ResponseEntity<JobSeeker> validateUser(String name, HttpSession session) {
		JobSeeker seeker = (JobSeeker) session.getAttribute("user");
		if (seeker != null) {
			return new ResponseEntity<JobSeeker>(jobSeekerService.findUser(seeker.getJobSeekerId()), HttpStatus.OK);
		}
		return new ResponseEntity<JobSeeker>(seeker, HttpStatus.OK);
	}

	/*
	 * Update the profile of the given job seeker
	 */
	@PostMapping("/updatePersonalProfile")
	public ResponseEntity<String> updatePersonalProfile(@RequestBody JobSeeker jobSeeker, HttpSession session) {
		JobSeeker validatedUser = (JobSeeker) session.getAttribute("user");
		if (validatedUser != null) {
			jobSeekerService.updateProfile(jobSeeker, validatedUser);
			return new ResponseEntity<>("Profile Updated", HttpStatus.OK);
		}
		return new ResponseEntity<>("Session Expired", HttpStatus.OK);
	}

	/*
	 * Will Accept a List of Skills.
	 * 
	 */
	@PostMapping("addSkills")
	public ResponseEntity<String> addUserSkill(@RequestBody SkillSetWrapper skills, HttpSession session) {
		JobSeeker jobSeeker = (JobSeeker) session.getAttribute("user");
		if (jobSeeker != null) {
			userSkillSetService.addUserSkills(skills.getSkills(), jobSeeker);
			return new ResponseEntity<>(jobSeeker.getFirstName(), HttpStatus.OK);
		}

		return new ResponseEntity<>("Session Expired", HttpStatus.OK);
	}

	/*
	 * Deletes a given Skill for the Session User
	 * 
	 */
	@PostMapping("deleteSkill")
	public ResponseEntity<String> deleteUserSkill(@RequestBody UserSkillSet skill, HttpSession session) {
		JobSeeker jobSeeker = (JobSeeker) session.getAttribute("user");
		if (jobSeeker != null) {
			userSkillSetService.deleteUserSkill(skill, jobSeeker);
			return new ResponseEntity<>("Deleted Skill", HttpStatus.OK);
		}

		return new ResponseEntity<>("Session Expired", HttpStatus.OK);
	}

	/*
	 * Will Return a List of skills based on the match
	 */
	@GetMapping("suggestSkills")
	public ResponseEntity<List<SkillSet>> suggestSkills(String name, HttpSession session) {
		return new ResponseEntity<>(skillSetService.listMatchSkill(name), HttpStatus.OK);
	}

	/*
	 * Will add a single location to the user preference
	 */
	@PostMapping("addLocation")
	public ResponseEntity<String> addLocationSingle(@RequestBody Location location, HttpSession session) {
		JobSeeker jobSeeker = (JobSeeker) session.getAttribute("user"); // Session Validation
		if (jobSeeker != null) {
			locationUserPreferenceService.addPreference(location, jobSeeker);
			return new ResponseEntity<>(jobSeeker.getFirstName(), HttpStatus.OK);
		}
		return new ResponseEntity<>("Session Expired", HttpStatus.OK);
	}

	/*
	 * Will Return a list of all the available Qualification Types Might not need
	 * these mapping
	 */
	@GetMapping("listQualificationTypes")
	public ResponseEntity<List<Qualification>> listQualifications() {
		return new ResponseEntity<List<Qualification>>(qualificationService.findAll(), HttpStatus.OK);
	}

	/*
	 * Will add education details to the job seeker
	 */
	@PostMapping("addEducation")
	public ResponseEntity<String> addEducation(@RequestBody EducationDetail education, HttpSession session) {
		JobSeeker jobSeeker = (JobSeeker) session.getAttribute("user"); // Session Validation
		if (jobSeeker != null) {
			educationDetailService.addEducation(education, jobSeeker);
			return new ResponseEntity<String>("Education Updated", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Session Expired", HttpStatus.OK);
		}

	}

	/*
	 * Will Delete the Education Details from that particular Job Seeker
	 */
	@PostMapping("/deleteEducation")
	public ResponseEntity<String> deleteEducation(@RequestBody EducationDetail education, HttpSession session) {
		JobSeeker jobSeeker = (JobSeeker) session.getAttribute("user"); // Session Validation
		if (jobSeeker != null) {
			educationDetailService.deleteEducation(education, jobSeeker);
			return new ResponseEntity<String>("1", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Session Expired", HttpStatus.OK);
		}
	}

	/*
	 * Lists all the Job Types : FT, PT, IS
	 * 
	 */
	@GetMapping("getJobPreferenceTypes")
	public ResponseEntity<List<JobType>> getJobPreferenceType() {
		return new ResponseEntity<List<JobType>>(jobTypeService.findAll(), HttpStatus.OK);
	}

	/*
	 * Adds the Job Preference FT, PT or Internship
	 */
	@PostMapping("addUserJobType")
	public ResponseEntity<String> addJobPreferenceType(@RequestBody JobType jobType, HttpSession session) {
		JobSeeker jobSeeker = (JobSeeker) session.getAttribute("user"); // Session Validation
		if (jobSeeker != null) {
			jobTypePreferenceService.addJobTypePreference(jobType, jobSeeker);
			return new ResponseEntity<>("1", HttpStatus.OK);
		}
		return new ResponseEntity<>("Session Expired", HttpStatus.OK);
	}

	/*
	 * Deletes the Job Preference FT, PT or Internship
	 */
	@PostMapping("deleteUserJobType")
	public ResponseEntity<String> deleteJobPreferenceType(@RequestBody JobTypePreference jobType, HttpSession session) {
		JobSeeker jobSeeker = (JobSeeker) session.getAttribute("user"); // Session Validation
		if (jobSeeker != null) {
			jobTypePreferenceService.deleteJobPreference(jobType, jobSeeker);
			return new ResponseEntity<>("1", HttpStatus.OK);
		}
		return new ResponseEntity<>("Session Expired", HttpStatus.OK);
	}

	@GetMapping("searchDesignation")
	public List<Designation> searchDesignations(String designation) {
		return designationService.searchByName(designation);
	}

	/*
	 * Adds the Job Designation Preference
	 * 
	 */
	@PostMapping("addDesignationPreference")
	public ResponseEntity<String> addDesignation(@RequestBody Designation designation, HttpSession session) {
		JobSeeker jobSeeker = (JobSeeker) session.getAttribute("user"); // Session Validation
		if (jobSeeker != null) {
			designationPreferenceService.addDesignationUser(designation, jobSeeker);
			return new ResponseEntity<>("1", HttpStatus.OK);
		}
		return new ResponseEntity<>("Session Expired", HttpStatus.OK);
	}

	@PostMapping("addWorkExp")
	public ResponseEntity<String> addWorkExp(@RequestBody WorkExperience experience, HttpSession session) {
		JobSeeker jobSeeker = (JobSeeker) session.getAttribute("user"); // Session Validation
		if (jobSeeker != null) {
			workExperienceService.addWorkExperience(experience, jobSeeker);
			return new ResponseEntity<>("Work Experience Added", HttpStatus.OK);
		}
		return new ResponseEntity<>("Session Expired", HttpStatus.OK);
	}

	/*
	 * Deletes Work Experience by Id
	 * 
	 */
	@PostMapping("deleteWorkExp")
	public ResponseEntity<String> deleteWorkExp(@RequestBody WorkExperience experience, HttpSession session) {
		JobSeeker jobSeeker = (JobSeeker) session.getAttribute("user"); // Session Validation
		if (jobSeeker != null) {
			workExperienceService.deleteWorkExperience(experience.getExperienceId());
			return new ResponseEntity<>("Work Experience Deleted", HttpStatus.OK);
		}
		return new ResponseEntity<>("Session Expired", HttpStatus.OK);
	}

	@PostMapping("addProject")
	public ResponseEntity<String> addProject(@RequestBody Project project, HttpSession session) {
		JobSeeker jobSeeker = (JobSeeker) session.getAttribute("user"); // Session Validation
		if (jobSeeker != null) {
			projectService.addProject(project, jobSeeker);
			return new ResponseEntity<>("Project Added", HttpStatus.OK);
		}
		return new ResponseEntity<>("Session Expired", HttpStatus.OK);
	}

	@PostMapping("deleteProject")
	public ResponseEntity<String> deleteProject(@RequestBody Project project, HttpSession session) {
		JobSeeker jobSeeker = (JobSeeker) session.getAttribute("user"); // Session Validation
		if (jobSeeker != null) {
			projectService.deleteProject(project);
			return new ResponseEntity<>("Project Deleted", HttpStatus.OK);
		}
		return new ResponseEntity<>("Session Expired", HttpStatus.OK);
	}

	@PostMapping("addAccomplishment")
	public ResponseEntity<String> addAccomplishment(@RequestBody ExtraAccomplishment accomplishment,
			HttpSession session) {
		JobSeeker jobSeeker = (JobSeeker) session.getAttribute("user"); // Session Validation
		if (jobSeeker != null) {
			extraAccomplishmentService.addAccomplishment(accomplishment, jobSeeker);
			return new ResponseEntity<>("Project Added", HttpStatus.OK);
		}
		return new ResponseEntity<>("Session Expired", HttpStatus.OK);
	}

	@PostMapping("deleteAccomplishment")
	public ResponseEntity<String> deleteAccomplishment(@RequestBody ExtraAccomplishment accomplishment,
			HttpSession session) {
		JobSeeker jobSeeker = (JobSeeker) session.getAttribute("user"); // Session Validation
		if (jobSeeker != null) {
			extraAccomplishmentService.deleteAccomplishment(accomplishment.getId());
			return new ResponseEntity<>("Project Added", HttpStatus.OK);
		}
		return new ResponseEntity<>("Session Expired", HttpStatus.OK);
	}

	@PostMapping("addCertification")
	public ResponseEntity<String> addCertification(@RequestBody Certification certification, HttpSession session) {
		JobSeeker jobSeeker = (JobSeeker) session.getAttribute("user"); // Session Validation
		if (jobSeeker != null) {
			certificationService.addCertificate(certification, jobSeeker);
			return new ResponseEntity<>("Project Added", HttpStatus.OK);
		}
		return new ResponseEntity<>("Session Expired", HttpStatus.OK);
	}

	@GetMapping("listAllJobs")
	public ResponseEntity<List<Job>> listAllJobs() {
		return new ResponseEntity<List<Job>>(jobService.listAll(), HttpStatus.OK);
	}

	@GetMapping("findByTitleNameJobs")
	public ResponseEntity<List<Job>> findJobByRoleName(String titleName) {
		return new ResponseEntity<List<Job>>(jobService.searchByTitle(titleName), HttpStatus.OK);
	}

	@GetMapping("containingTitleNameJobs")
	public ResponseEntity<List<Job>> findContainingTitleName(String titleName) {
		return new ResponseEntity<List<Job>>(jobService.findContainingTitle(titleName), HttpStatus.OK);
	}

	@PostMapping("applyJob")
	public ResponseEntity<String> applyJob(@RequestBody JobSeekerApplication application, HttpSession session) {
		JobSeeker jobSeeker = (JobSeeker) session.getAttribute("user");
		if (jobSeeker != null) {
			JobSeekerApplication applicationStatus = jobSeeeApplicationService.applyJob(application, jobSeeker);
			if (applicationStatus != null) {
				return new ResponseEntity<>("Job Created", HttpStatus.CREATED);
			}
			return new ResponseEntity<>("Already Applied to Job", HttpStatus.OK);
		}
		return new ResponseEntity<>("Session Expired", HttpStatus.OK);
	}

	@PostMapping("deleteApplication")
	public ResponseEntity<String> deleteJobApplication(@RequestBody JobSeekerApplication application,
			HttpSession session) {
		JobSeeker jobSeeker = (JobSeeker) session.getAttribute("user");
		if (jobSeeker != null) {
			boolean flag = jobSeeeApplicationService.deleteJobApplication(application, jobSeeker);
			if (flag)
				return new ResponseEntity<>("Job Deleted!", HttpStatus.OK);
			return new ResponseEntity<>("Job Does Not exist!", HttpStatus.OK);

		}
		return new ResponseEntity<>("Session Expired", HttpStatus.OK);
	}

	@RequestMapping(value = "/updatePersonalProfileImage", consumes = {
			org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE })
	public String updateProfile(@RequestParam("file") MultipartFile file, @RequestParam("jobSeeker") String jobSeeker,
			HttpSession session) {
		JobSeeker seeker = (JobSeeker) session.getAttribute("user");
		if (seeker != null) {
			try {
				JobSeeker updatedSeeker = new ObjectMapper().readValue(jobSeeker, JobSeeker.class);

				String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
				String directoryPath = "C:\\Users\\Dell\\Desktop\\CDAC\\Training\\FINAL PROJECT\\Database\\Image Data";
				createDirectoryIfNotExists(directoryPath); // Create directory if it doesn't exist
				String filePath = directoryPath + "/" + fileName;
				file.transferTo(new File(filePath));
				String encodedPath = URLEncoder.encode(filePath, "UTF-8");
				updatedSeeker.setProfileImagePath(encodedPath);
				jobSeekerService.updateProfile(updatedSeeker, seeker);

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
