package com.spring.form;

import javax.validation.constraints.Pattern;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.validator.RegExpressions;

@Component
public class UserForm{
	
	@NotEmpty(message="{required.action}")
	private String action;
	
	@NotEmpty(message="{required.userid}")
    private String userid;

	@NotEmpty(message="{required.firstname}")
    private String firstname;

	@NotEmpty(message="{required.lastname}")
    private String lastname;

	@Pattern(regexp = RegExpressions.REGEXP_EMAIL, message = "{badformat.email}")
    private String email;

	@NotEmpty(message="{required.country}")
    private String country;

	@Pattern(regexp = RegExpressions.REGEXP_ZIP, message = "{badformat.zip}")
    private String zip;

	@NotEmpty(message="{required.state}")
    private String state;

	@NotEmpty(message="{required.city}")
    private String city;

	@NotEmpty(message="{required.address}")
    private String address;

	@Pattern(regexp = RegExpressions.REGEXP_PHONENUMBER, message = "{badformat.phone}")
    private String phone;
    
	@Pattern(regexp = RegExpressions.REGEXP_FAXNUMBER, message = "{badformat.fax}")
    private String fax;
	
	private Integer storeid;
        
	public UserForm() {
		super();
	}

    public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getUserid() {
        return this.userid;
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
        return this.lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZip() {
        return this.zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return this.fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }	
    
	public Integer getStoreid() {
		return storeid;
	}

	@Value("${storeid}")
	public void setStoreid(Integer storeid) {
		this.storeid = storeid;
	}
	
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}		
}