package com.spring.form;

import javax.validation.constraints.Pattern;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import com.validator.RegExpressions;

@Controller
public class NewUserForm{	

	@NotEmpty(message="{required.action}")
	protected String action;
	
	@NotEmpty(message="{required.userid}")
    protected String userid;

	@NotEmpty(message="{required.firstname}")
    protected String firstname;

	@NotEmpty(message="{required.lastname}")
    protected String lastname;

	@Pattern(regexp = RegExpressions.REGEXP_EMAIL, message = "{badformat.email}")
    protected String email;

	@NotEmpty(message="{required.country}")
    protected String country;

	@Pattern(regexp = RegExpressions.REGEXP_ZIP, message = "{badformat.zip}")
    protected String zip;

	@NotEmpty(message="{required.state}")
    protected String state;

	@NotEmpty(message="{required.city}")
    protected String city;

	@NotEmpty(message="{required.address}")
    protected String address;

	@Pattern(regexp = RegExpressions.REGEXP_PHONENUMBER, message = "{badformat.phone}")
    protected String phone;
    
	@Pattern(regexp = RegExpressions.REGEXP_FAXNUMBER, message = "{badformat.fax}")
    protected String fax;	
	
	@NotEmpty(message="{required.password}")
	@Pattern(regexp = RegExpressions.REGEXP_PASSWORD, message = "{badformat.password}")
    private String password;
	
	@NotEmpty(message="{required.secretCode}")
    private String secretCode;
	
	@NotEmpty(message="{required.secretCodeKey}")
	private String secretCodeKey;
	
	private Integer storeid;
        
	public NewUserForm() {
		super();
	} 
    
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }    

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public Integer getStoreid() {
		return this.storeid;
	}

	public void setStoreid(Integer storeid) {
		this.storeid = storeid;
	}
	
	public String getSecretCode() {
		return secretCode;
	}

	public void setSecretCode(String secretCode) {
		this.secretCode = secretCode;
	}

	public String getSecretCodeKey() {
		return secretCodeKey;
	}

	public void setSecretCodeKey(String secretCodeKey) {
		this.secretCodeKey = secretCodeKey;
	}
	
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}			
}