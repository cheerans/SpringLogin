package com.util.encryption;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Value;

import com.model.OurUser;
import com.util.encryption.intrface.DecryptionVisitor;


public class DecryptionVisitorImpl implements DecryptionVisitor {

	private StandardPBEStringEncryptor decryptor;
	
	@Value("${encryption.algorithm}")
	private String algorithm;
	
	public OurUser visit(OurUser inObj){
		String salt = inObj.getSalt();				
		decryptor = new StandardPBEStringEncryptor();
		decryptor.setPassword(salt);
		decryptor.setAlgorithm(algorithm);	
		if(true == inObj.isEncrypted()){
			try{
				inObj.setFirstname(decryptor.decrypt(inObj.getFirstname()));
				inObj.setLastname(decryptor.decrypt(inObj.getLastname()));
				inObj.setEmail(decryptor.decrypt(inObj.getEmail()));
				inObj.setCountry(decryptor.decrypt(inObj.getCountry()));
				inObj.setZip(decryptor.decrypt(inObj.getZip()));
				inObj.setState(decryptor.decrypt(inObj.getState()));
				inObj.setCity(decryptor.decrypt(inObj.getCity()));
				inObj.setAddress(decryptor.decrypt(inObj.getAddress()));
				inObj.setPhone(decryptor.decrypt(inObj.getPhone()));
				inObj.setFax(decryptor.decrypt(inObj.getFax()));
				inObj.setEncrypted(false);
			}catch(Exception e){
				
			}
		}
		return inObj;
	}
}
