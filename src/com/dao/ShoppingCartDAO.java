package com.dao;

import java.util.List;

public interface ShoppingCartDAO{	
	// Load user
	public com.model.OurUser loadUser(String userid, int storeid, boolean shouldDecrypt);	
	// Load user
	public com.model.OurUser loadUser(String userid, int storeid);	
	// Save user
	public void saveUser(com.model.OurUser DBUser, int storeid) throws Exception;	
	// Save user
	public void saveWOutEncryption(com.model.OurUser DBUser) throws Exception;
	// Save user
	public void addUser(com.model.OurUser DBUser);
	// Get user list
	public List<String> getAllUserIdList() throws Exception;	
	// Get admin list
	public List<String> getAdminList() throws Exception;	
	// Get Super user list
	public List<String> getSuperUserList() throws Exception;		
	
}