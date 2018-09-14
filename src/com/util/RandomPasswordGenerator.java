package com.util;

import com.util.ourinterface.PasswordGenerator;

public class RandomPasswordGenerator implements PasswordGenerator {

	public String nextPassword() {
		final int PASSWORD_LENGTH = 8;	
		final String specialPwdChars = "!#$%&'()*+,-./:;<=>?@[\\]^`{|}~" + '"';
		StringBuffer sb = new StringBuffer();  
		for (int x = 0; x < PASSWORD_LENGTH - 2; x++){  
		  sb.append((char)((int)(Math.random()*26)+97));
		}
		sb.insert((int)(Math.random()*7), specialPwdChars.charAt((int)(Math.random()*31)));
		sb.insert((int)(Math.random()*7), (int)(Math.random()*10));
		return (sb.toString());
	}
	
	private final String specialRandomWordChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	public String nextRandomWord() {
		final int RANDOMWORD_LENGTH = 8;
		StringBuffer sb = new StringBuffer();  
		int halfWords = RANDOMWORD_LENGTH / 2;
		for (int x = 0; x < halfWords; x++){  
			sb.append((char)((int)(Math.random()*9)+48));
		}
		for (int x = halfWords; x < RANDOMWORD_LENGTH; x++){  
			sb.insert((int)(Math.random()*3), specialRandomWordChars.charAt((int)(Math.random()*51)));
		}
		return (sb.toString());
	}
}