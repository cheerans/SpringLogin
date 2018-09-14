package com.spring.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;

import com.validator.RegExpressions;

public class ChangePasswordForm{

	@NotEmpty(message="{required.userid}")
	@NotNull(message="{required.userid}")
	private String userid;

	@NotEmpty(message="{required.password}")
	@Pattern(regexp = RegExpressions.REGEXP_PASSWORD, message = "{badformat.password}")
    private String password;
	
	@NotEmpty(message="{required.password}")
	@Pattern(regexp = RegExpressions.REGEXP_PASSWORD, message = "{badformat.password}")
	private String confirmPassword;
	
	@NotEmpty(message="{required.password}")
	@Pattern(regexp = RegExpressions.REGEXP_PASSWORD, message = "{badformat.password}")	
	private String oldPassword;
	
	@NotEmpty(message="{required.secretCode}")
    private String secretCode;
	
	@NotEmpty(message="{required.secretCodeKey}")
	private String secretCodeKey;
    
	public ChangePasswordForm() {
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

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
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