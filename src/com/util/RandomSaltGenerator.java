package com.util;

import org.springframework.beans.factory.annotation.Autowired;
import com.util.ourinterface.SaltGenerator;


public class RandomSaltGenerator implements SaltGenerator {

	@Autowired
	private RandomPasswordGenerator passGen;
	
	public String nextSalt() {
		return passGen.nextPassword();
	}
}
