package com.util.encryption;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.model.OurUser;
import com.util.encryption.intrface.EncryptionVisitor;
import com.util.ourinterface.SaltGenerator;

public class EncryptionVisitorImpl implements EncryptionVisitor {
	
	@Autowired
	private SaltGenerator saltGenerator;
	
	@Value("${encryption.algorithm}")
	private String algorithm;
		
	private StandardPBEStringEncryptor encryptor;	
	
	public OurUser visit(OurUser inObj){
		
		if(false == inObj.isEncrypted()){
							
			encryptor = new StandardPBEStringEncryptor();
			encryptor.setPassword(inObj.getSalt());
			encryptor.setAlgorithm(algorithm);			
			try{
				inObj.setFirstname(encryptor.encrypt(inObj.getFirstname()));
				inObj.setLastname(encryptor.encrypt(inObj.getLastname()));
				inObj.setEmail(encryptor.encrypt(inObj.getEmail()));
				inObj.setCountry(encryptor.encrypt(inObj.getCountry()));
				inObj.setZip(encryptor.encrypt(inObj.getZip()));
				inObj.setState(encryptor.encrypt(inObj.getState()));
				inObj.setCity(encryptor.encrypt(inObj.getCity()));
				inObj.setAddress(encryptor.encrypt(inObj.getAddress()));
				inObj.setPhone(encryptor.encrypt(inObj.getPhone()));
				inObj.setFax(encryptor.encrypt(inObj.getFax()));
				inObj.setEncrypted(true);
			}catch(Exception e){
				
			}
		}
		return inObj;
	}
	
	public String visit(String inObj){
		String retVal;
		String salt = saltGenerator.nextSalt();		
		encryptor = new StandardPBEStringEncryptor();
		encryptor.setPassword(salt);
		encryptor.setAlgorithm(algorithm);			
		retVal = encryptor.encrypt(inObj);	
		return retVal;
	}
}
