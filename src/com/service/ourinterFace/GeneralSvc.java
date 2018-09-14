package com.service.ourinterFace;

import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.model.OurUser;
import com.model.SaltedUser;
import com.spring.form.LoginForm;

public interface GeneralSvc {
	
	public OurUser getUser(String userId);
	public boolean saveNewUser(OurUser userDBObj);
	public boolean saveUser(OurUser userDBObj);
	public OurUser encrypt(OurUser user);
	public boolean changePassword(String userid, String password);
	public String encryptForUser(String userid, String word);
	public LoginForm getLoggedInUserDetails();
	public String getLoggedInUser();
	public boolean makeUserAdmin(String userid);
	public boolean removeUserAsAdmin(String userid);
	public List<String> getAllUserIdList() throws Exception;
	public List<String> getAdminList() throws Exception ;
	public List<String> getSuperUserList() throws Exception ;
	public boolean sendEmail(String[] recipients,String message,String subject) throws Exception;
}