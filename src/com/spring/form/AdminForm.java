package com.spring.form;

import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

public class AdminForm{
	
    private String newAdminUserid = null;
    
    private List<String> userList = null;

    private List<String> adminList = null;
    
    private List<String> superuserList = null;
    
    private String deleteUserid = null;
    
	public AdminForm() {
	}
	
	public String getNewAdminUserid() {
		return newAdminUserid;
	}

	public void setNewAdminUserid(String newAdminUserid) {
		this.newAdminUserid = newAdminUserid;
	}

	public List<String> getUserList() {
		return userList;
	}

	public void setUserList(List<String> userList) {
		this.userList = userList;
	}

	public List<String> getAdminList() {
		return adminList;
	}

	public void setAdminList(List<String> adminList) {
		this.adminList = adminList;
	} 		
	
	public List<String> getSuperuserList() {
		return superuserList;
	}

	public void setSuperuserList(List<String> superuserList) {
		this.superuserList = superuserList;
	}

	public String getDeleteUserid() {
		return deleteUserid;
	}

	public void setDeleteUserid(String deleteUserid) {
		this.deleteUserid = deleteUserid;
	}    
	
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}		
}