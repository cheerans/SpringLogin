package com.service.ourinterFace;

import javax.servlet.http.HttpServletRequest;

import com.model.OurUser;

public interface LoginSvc {
	public boolean compareLogin(OurUser loggedinUser,OurUser inObj);
	public boolean login(String username, String password,HttpServletRequest request);
	public void logout();
	public boolean isLoggedIn(HttpServletRequest request);
}