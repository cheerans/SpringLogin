package com.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import com.model.OurUser;
import com.model.SaltedUser;
import com.service.ourinterFace.LoginSvc;

public class LoginSvcImpl implements LoginSvc{	
	
	@Autowired
	// specific for Spring Security
	private AuthenticationManager authenticationManager; 
	
	@Autowired
	private com.util.encryption.intrface.EncryptionVisitor encrypt;

	public boolean compareLogin(OurUser loggedinUser,OurUser inObj){
		boolean retEqual = false;
		OurUser newLogin = inObj;
		if(loggedinUser.getUserid().equals(inObj.getUserid()))	{
			retEqual = true;
		}
		return retEqual;
	}
	
	public boolean login(String username, String password,HttpServletRequest request) {
		boolean loggedIn = false;
	    try {	        
	    	UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
	        // generate session if one doesn't exist
	        request.getSession();
	        token.setDetails(new WebAuthenticationDetails(request));
	    	
	        Authentication authenticate = authenticationManager.authenticate(token);
	        if (authenticate.isAuthenticated()) {
	            SecurityContextHolder.getContext().setAuthentication(authenticate);
	            HttpSession session = request.getSession(true);
	            session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
	            loggedIn = true;
	        }
	    }
	    catch (AuthenticationException e) { 
	    	 SecurityContextHolder.getContext().setAuthentication(null);
	    }catch(Exception e){
	    	SecurityContextHolder.getContext().setAuthentication(null);
	    }
	    return loggedIn;
	}
	
	public void logout(){
		SecurityContextHolder.getContext().setAuthentication(null);
	}
	
	public boolean isLoggedIn(HttpServletRequest request){
		boolean bLoggedIn = false;
		if(null != SecurityContextHolder.getContext().getAuthentication()){
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if ((null != principal) && (principal instanceof SaltedUser)) {
				bLoggedIn = true;
			} 
		}
		return bLoggedIn;
	}
}