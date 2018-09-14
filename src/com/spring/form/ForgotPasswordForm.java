package com.spring.form;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;

public class ForgotPasswordForm{

	@NotEmpty(message="{required.userid}")
	@NotNull(message="{required.userid}")
	private String userid;

    private String password;
    
	@NotEmpty(message="{required.secretCode}")
    private String secretCode;
	
	@NotEmpty(message="{required.secretCodeKey}")
	private String secretCodeKey;
    
	public ForgotPasswordForm() {
	} 	

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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