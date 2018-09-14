package com.util;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class EmailHelper {
	
	public static boolean sendEmail(String[] recipients,String msg,String subject){	
		boolean success = false;		
		SimpleEmail email = new SimpleEmail();
		email.setHostName("smtp.live.com");
		email.setAuthentication("cheeran@hotmail.com", "C_lpoulose1");
		email.setDebug(true);
		email.setSmtpPort(587);
		
		try {
			for (int i = 0; i < recipients.length; i++){
			    email.addTo(recipients[i]);
			}			
			email.setFrom("cheeran@hotmail.com", "Santhosh Cheeran");		
			email.setSubject(subject);
			email.setSSL(true);
			email.setMsg(msg);
			email.send();
			success = true;
		} catch (EmailException e){
			e.printStackTrace();
		}
		return success;
	}
}