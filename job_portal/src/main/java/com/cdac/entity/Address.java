package com.cdac.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "address")
public class Address {
	
	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "address_id")
	    private int addressId;

	    @Column(name = "user_id")
	    private Integer userId;

	    @Column(name = "add_type")
	    private char addType;

	    @Column(name = "line1", nullable = false)
	    private String line1;

	    @Column(name = "line2")
	    private String line2;

	    @Column(name = "line3")
	    private String line3;

	    @Column(name = "pincode")
	    private String pincode;

	    @Column(name = "state")
	    private String state;

	    @Column(name = "country")
	    private String country;

	    
	    
		public int getAddressId() {
			return addressId;
		}

		public void setAddressId(int addressId) {
			this.addressId = addressId;
		}

		public Integer getUserId() {
			return userId;
		}

		public void setUserId(Integer userId) {
			this.userId = userId;
		}

		public char getAddType() {
			return addType;
		}

		public void setAddType(char addType) {
			this.addType = addType;
		}

		public String getLine1() {
			return line1;
		}

		public void setLine1(String line1) {
			this.line1 = line1;
		}

		public String getLine2() {
			return line2;
		}

		public void setLine2(String line2) {
			this.line2 = line2;
		}

		public String getLine3() {
			return line3;
		}

		public void setLine3(String line3) {
			this.line3 = line3;
		}

		public String getPincode() {
			return pincode;
		}

		public void setPincode(String pincode) {
			this.pincode = pincode;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		public String getCountry() {
			return country;
		}

		public void setCountry(String country) {
			this.country = country;
		}
	    
	    
	
}
