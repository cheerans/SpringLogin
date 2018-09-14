package com.spring.form;

import javax.validation.constraints.Pattern;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;

import com.validator.RegExpressions;

public class LoginForm{
	
	@NotEmpty(message="{required.userid}")
    private String userid = null;

	@NotEmpty(message="{required.secretCode}")
	@Pattern(regexp = RegExpressions.REGEXP_PASSWORD, message = "{badformat.password}")
    private String password = null;
	
	private boolean loggedIn = false;
    
	public LoginForm() {
	} 

	public String getUserid() {
        return this.userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }  

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}		 
}