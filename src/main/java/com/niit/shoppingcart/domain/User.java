package com.niit.shoppingcart.domain;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;
//whenever it scans all the classes under particular package,
//will create instance of this class.

@Component //to create instance;the instance is same as class name,but
// the first letter will be small letter
@Entity  //to specify it is not normal class-it is data base entity
@Table
public class User {

	@Id
	@GeneratedValue
	private String emailId;
	
	@NotNull(message="The first name cannot be left blank")
	@Column(name = "first_name")
	private String firstName;
	
	@NotNull(message="The last name cannot be left blank")
	@Column(name = "last_name")
	private String lastName;

	
	@NotNull(message="The name cannot be left blank")
	@Size(min=6,max=15,message="The password length must be between 6 and 15 characters")
	private String password;
	
	
	@NotNull @Pattern(regexp="\\d{10}",message="The mobile number must be a 10 digit number")
	private String phone;
			
	
	private String role;
	
	
	private Boolean enabled;
	
	@Column(name = "Created_Timestamp")
	private Timestamp createdTimestamp;
	
	@Column(name = "Created_By")
	private String createdBy;
	
	@Column(name = "Updated_Timestamp")
	private Timestamp updatedTimestamp;
	
	@Column(name = "Updated_By")
	private String updatedBy;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "personId", cascade = CascadeType.ALL)
	private Set<Address> userAddress = new HashSet<Address>(0);
	
	
	@Override
	public String toString() {
		
		return " "+firstName+" "+lastName+" "+emailId+" "+password+" "+role+" "+enabled;
	}

	

	public String getEmailId() {
		return emailId;
	}



	public void setEmailId(String emailId) {
		this.emailId = emailId;
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

	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Timestamp getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(Timestamp createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getUpdatedTimestamp() {
		return updatedTimestamp;
	}

	public void setUpdatedTimestamp(Timestamp updatedTimestamp) {
		this.updatedTimestamp = updatedTimestamp;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Set<Address> getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(Set<Address> userAddress) {
		this.userAddress = userAddress;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	
			
}
