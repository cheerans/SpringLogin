package com.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.model.enums.UserRoleEnumType;
import com.model.enums.UserRoleEnumType.UserRoleEnum;

@Entity
@Table(name="user")
public class OurUser{
	
	@Transient
	final static List<GrantedAuthority> grantedAuthorityUser =  new ArrayList<GrantedAuthority>();
	static{

		grantedAuthorityUser.add(new SimpleGrantedAuthority(UserRoleEnum.ROLE_USER.name()));
	}
	
	@Transient
	final static List<GrantedAuthority> grantedAuthorityAdmin =  new ArrayList<GrantedAuthority>();
	static{

		grantedAuthorityAdmin.add(new SimpleGrantedAuthority(UserRoleEnum.ROLE_USER.name()));
		grantedAuthorityAdmin.add(new SimpleGrantedAuthority(UserRoleEnum.ROLE_ADMIN.name()));
	}	
	
	@Transient
	final static List<GrantedAuthority> grantedAuthoritySuperUser =  new ArrayList<GrantedAuthority>();
	static{

		grantedAuthoritySuperUser.add(new SimpleGrantedAuthority(UserRoleEnum.ROLE_USER.name()));
		grantedAuthoritySuperUser.add(new SimpleGrantedAuthority(UserRoleEnum.ROLE_ADMIN.name()));
		grantedAuthoritySuperUser.add(new SimpleGrantedAuthority(UserRoleEnum.ROLE_SUPERUSER.name()));
	}

	@Transient
	private static final long serialVersionUID = -8658673848918717753L;

	@Id
    @GeneratedValue
    @Column(name="id")
    private int id;

    @Column(name="userid")
    private String userid;
    
    @Column(name="storeid")
    private int storeid;

    @Column(name="password")
    private String password;

    @Column(name="firstname")
    private String firstname;

    @Column(name="lastname")
    private String lastname;

    @Column(name="email")
    private String email;

    @Column(name="country")
    private String country;

    @Column(name="zip")
    private String zip;

    @Column(name="state")
    private String state;

    @Column(name="city")
    private String city;

    @Column(name="address")
    private String address;

    @Column(name="phone")
    private String phone;
    
    @Column(name="fax")
    private String fax;
        
    @Transient
    private String newpassword;
    
    @Column(name="salt")
    private String salt;
    
    @Type(type = "com.model.enums.UserRoleEnumType")
    @Column(nullable = false, length=UserRoleEnumType.COLUMN_LENGTH)
    private UserRoleEnum roles;
    
    @Transient
    private boolean isEncrypted = false;
    
	public OurUser(String username, String password, boolean enabled,
			boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked, List<GrantedAuthority> authorities,
			String salt) {

		this.salt = "";//create random salt or query db
	} 
    
    /** default constructor */
    public OurUser() {
    }
    
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }       

	public String getUserid() {
        return this.userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
    
    public int getStoreid() {
		return storeid;
	}

	public void setStoreid(int storeid) {
		this.storeid = storeid;
	}

	public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }    

	public String getFirstname() {
        return this.firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZip() {
        return this.zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return this.fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	} 
	
	public UserRoleEnum getRoles() {
		return roles;
	}

	public void setRoles(UserRoleEnum roles) {

		this.roles = roles;
	}

	public boolean isEncrypted() {
		return isEncrypted;
	}

	public void setEncrypted(boolean isEncrypted) {
		this.isEncrypted = isEncrypted;
	}
		 
	public SaltedUser getSaltedUser(){	
		List<GrantedAuthority> authorities = null;
		if(this.roles == UserRoleEnum.ROLE_SUPERUSER){
			authorities = grantedAuthoritySuperUser;
		}else if(this.roles == UserRoleEnum.ROLE_ADMIN){
			authorities = grantedAuthorityAdmin;
		}else if(this.roles == UserRoleEnum.ROLE_USER){
			authorities = grantedAuthorityUser;
		}
		return new SaltedUser(this.userid, this.password, true, true,	true, true, authorities,this.salt);
	}
	 
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}		 
}