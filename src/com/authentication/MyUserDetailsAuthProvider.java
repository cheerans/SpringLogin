package com.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

import com.dao.ShoppingCartDAOImpl;
import com.model.OurUser;
import com.model.SaltedUser;


public class MyUserDetailsAuthProvider extends AbstractUserDetailsAuthenticationProvider{

	@Autowired 
	private ShoppingCartDAOImpl DAO;	
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private SaltSource saltSource;
	
	@Value("${storeid}")
	private int storeid;
	
	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken token) throws AuthenticationException {
		SaltedUser thisUser = null;
		try {
			OurUser user = DAO.loadUser(username,storeid);
			if(null != user){
				thisUser = user.getSaltedUser();
			}
			if( (null == thisUser) ||
				(null == thisUser.getPassword()) ||
				(thisUser.getPassword().isEmpty())){
				throw new BadCredentialsException("");
			}
			return thisUser;
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new BadCredentialsException("Login Error due to bad credentials provided for user - " + thisUser.getUsername() );
	}

	@Override
	protected void additionalAuthenticationChecks(UserDetails user,
			UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		
        if (authentication.getCredentials() == null) {
        	throw new BadCredentialsException("Login Error due to bad credentials provided for user - " + user.getUsername() );
        }else {
        	SaltedUser saltedUser = (SaltedUser)user;
			if(	false == passwordEncoder.isPasswordValid(	saltedUser.getPassword(),
															authentication.getCredentials().toString(),
															saltSource.getSalt(saltedUser))){
				throw new BadCredentialsException("Login Error due to bad credentials provided for user - " + user.getUsername() );
			}
		}
	}
}