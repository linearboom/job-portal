package com.cdac.entity.employer;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.cdac.entity.Address;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="employer")
public class Employer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employer_id")
    private int employerId;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "fname", nullable = false)
    private String firstName;

    @Column(name = "lname")
    private String lastName;

    @Column(name = "mobile", length = 15)
    private String mobile;

    @Column(name = "organization_name", nullable = false)
    private String organizationName;
    
    @OneToMany(mappedBy = "employer", cascade = CascadeType.ALL)
    private List<Job> jobs;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name = "email_otp", length = 6)
    private String emailOtp;

    @Column(name = "email_verified")
    private char emailVerified;

    @Column(name = "mobile_otp", length = 6)
    private String mobileOtp;

    @Column(name = "mobile_verified")
    private char mobileVerified;

	public Employer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getEmployerId() {
		return employerId;
	}

	public void setEmployerId(int employerId) {
		this.employerId = employerId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getEmailOtp() {
		return emailOtp;
	}

	public void setEmailOtp(String emailOtp) {
		this.emailOtp = emailOtp;
	}

	public char getEmailVerified() {
		return emailVerified;
	}

	public void setEmailVerified(char emailVerified) {
		this.emailVerified = emailVerified;
	}

	public String getMobileOtp() {
		return mobileOtp;
	}

	public void setMobileOtp(String mobileOtp) {
		this.mobileOtp = mobileOtp;
	}

	public char getMobileVerified() {
		return mobileVerified;
	}

	public void setMobileVerified(char mobileVerified) {
		this.mobileVerified = mobileVerified;
	}

	public List<Job> getJobs() {
		return jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}
    
	  
}
