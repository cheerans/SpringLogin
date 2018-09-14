package com.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.dao.ShoppingCartDAO;
import com.model.OurUser;
import com.model.SaltedUser;
import com.model.enums.UserRoleEnumType.UserRoleEnum;
import com.service.ourinterFace.GeneralSvc;
import com.spring.form.LoginForm;
import com.util.EmailHelper;
import com.util.ourinterface.SaltGenerator;

public class GeneralSvcImpl implements GeneralSvc{	
	
	@Autowired
	private com.util.encryption.intrface.EncryptionVisitor encrypt;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private SaltSource saltSource;
	
	@Resource(name="shoppingCartDao")
	private ShoppingCartDAO DAO;	
	
	@Autowired
	private SaltGenerator saltGenerator;
	
	@Value("${storeid}")
	private int storeid;
	
	public OurUser getUser(String userId){
		OurUser user = null;
		try {
			user = DAO.loadUser(userId,storeid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;		
	}
	
	public boolean saveNewUser(OurUser userDBObj){
		boolean success = false;
		try {
			String salt = saltGenerator.nextSalt();	
			userDBObj.setSalt(salt);
			userDBObj.setRoles(UserRoleEnum.ROLE_USER);
			DAO.addUser(userDBObj);
			success = true;
		} catch (Exception e) {
			success = false;
		}
		if(true == success){
			success = changePassword(userDBObj.getUserid(),userDBObj.getPassword());
		}
		return success;
	}
	
	public boolean saveUser(OurUser userDBObj){
		boolean saved = false;
		try {
			DAO.saveUser(userDBObj,storeid);
			saved = true;
		} catch (Exception e) {
			saved = false;
		}
		return saved;
	}
	
	public String encryptForUser(String userid, String password){
		OurUser user = null;
		try {
			user = DAO.loadUser(userid,storeid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		SaltedUser saltedUser = null;
		if(null != user){
			saltedUser = user.getSaltedUser();
		}
		String retVal = passwordEncoder.encodePassword(password, saltSource.getSalt(saltedUser));
		return retVal;
	}
	
	public boolean changePassword(String userid, String password){
		boolean success = false; 
		OurUser user = null;
		try {
			user = DAO.loadUser(userid,storeid,false);
			success = true;
		} catch (Exception e1) {
			success = false;
		}
		if(true == success){
			user.setPassword(encryptForUser(userid,password));
			try {
				DAO.saveWOutEncryption(user);
				success = true;
			} catch (Exception e) {
				success = false;
			}
		}
		return success;
	}
	
	public OurUser encrypt(OurUser user){		
		return encrypt.visit(user);
	}
	
	public LoginForm getLoggedInUserDetails(){
		LoginForm form = new LoginForm();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(null != auth){
			if(auth.getPrincipal().getClass().equals(SaltedUser.class)){
				SaltedUser salteduser = (SaltedUser)auth.getPrincipal();
				form.setUserid(salteduser.getUsername());
				form.setLoggedIn(true);
			}
		}
		return form;
	}
	
	public String getLoggedInUser(){
		String user = "";
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(null != auth){
			if(auth.getPrincipal().getClass().equals(SaltedUser.class)){
				SaltedUser salteduser = (SaltedUser)auth.getPrincipal();
				user = salteduser.getUsername();
			}
		}
		return user;
	}	
	
	public boolean makeUserAdmin(String userid){
		boolean success = false; 
		OurUser user = null;
		try {
			user = DAO.loadUser(userid,storeid,false);
			success = true;
		} catch (Exception e1) {
			success = false;
		}
		if(true == success){
			user.setRoles(UserRoleEnum.ROLE_ADMIN);
			try {
				DAO.saveWOutEncryption(user);
				success = true;
			} catch (Exception e) {
				success = false;
			}
		}
		return success;
	}
	
	public boolean removeUserAsAdmin(String userid){
		boolean success = false; 
		OurUser user = null;
		try {
			user = DAO.loadUser(userid,storeid,false);
			success = true;
		} catch (Exception e1) {
			success = false;
		}
		if(true == success){
			if(false == user.getRoles().equals(UserRoleEnum.ROLE_SUPERUSER)){
				user.setRoles(UserRoleEnum.ROLE_USER);
				try {
					DAO.saveWOutEncryption(user);
					success = true;
				} catch (Exception e) {
					success = false;
				}
			}
		}
		return success;		
	}
	
	public List<String> getAllUserIdList() throws Exception{
		return DAO.getAllUserIdList();
	}
		
	public List<String> getAdminList() throws Exception{
		return DAO.getAdminList();
	}
	
	// Get Super user list
	public List<String> getSuperUserList() throws Exception{
		return DAO.getSuperUserList();
	}
	
	public boolean sendEmail(String[] recipients,String message,String subject) throws Exception{
		return EmailHelper.sendEmail(recipients, message, message);
	}	
}
