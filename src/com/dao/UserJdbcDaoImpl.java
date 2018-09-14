package com.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.dao.ReflectionSaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

import com.model.OurUser;

public class UserJdbcDaoImpl extends JdbcDaoImpl {

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	ReflectionSaltSource saltSource;
	
	@Autowired
	DataSource dataSource;
	
	@Autowired 
	ShoppingCartDAOImpl DAOImpl;	
	
	@Value("${storeid}")
	private int storeid;
	

	public UserJdbcDaoImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void changePassword(String username, String password) {

		String salt = "";// query db;
		boolean enabled = true;
		OurUser myUser = new OurUser(username, password, enabled, true, true,
				true, AuthorityUtils.NO_AUTHORITIES, salt);
		String encodedPassword = passwordEncoder.encodePassword(password,
				myUser.getSalt());
		getJdbcTemplate().update(
				"UPDATE USERS SET PASSWORD = ? WHERE USERNAME = ?",
				new Object[] { encodedPassword, username });
	}

	@Override
	protected UserDetails createUserDetails(String username,
			UserDetails userFromUserQuery,
			List<GrantedAuthority> combinedAuthorities) {
		return userFromUserQuery;
	}

	@Override
	protected List<UserDetails> loadUsersByUsername(String userid) {
		List<UserDetails> users = new ArrayList<UserDetails>();
		OurUser user = DAOImpl.loadUser(userid,storeid);
		users.add(user.getSaltedUser());
		String encodedPassword = passwordEncoder.encodePassword("amar",
				user.getSalt());
		return users;		
	}

	@Override
	protected List<GrantedAuthority> loadUserAuthorities(String username) {
		return super.loadUserAuthorities(username);
	}

	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	public void setSaltSource(ReflectionSaltSource saltSource) {
		this.saltSource = saltSource;
	}

	public void setDAOImpl(ShoppingCartDAOImpl dAOImpl) {
		DAOImpl = dAOImpl;
	}
}