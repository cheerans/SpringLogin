package com.validator;

public class RegExpressions {
	
	// 1-(123)-123-1234 | 123 123 1234 | 1-800-ALPHNUM
	public static final String REGEXP_PHONENUMBER = "^([0-9]( |-)?)?(\\(?[0-9]{3}\\)?|[0-9]{3})( |-)?([0-9]{3}( |-)?[0-9]{4}|[a-zA-Z0-9]{7})$";
	
	// 1-(123)-123-1234 | 123 123 1234 | 1-800-ALPHNUM
	public static final String REGEXP_FAXNUMBER = "^([0-9]( |-)?)?(\\(?[0-9]{3}\\)?|[0-9]{3})( |-)?([0-9]{3}( |-)?[0-9]{4}|[a-zA-Z0-9]{7})$";
	
	// bob@somewhere.com | bob.jones@[1.1.1.1] | bob@a.b.c.d.info
	public static final String REGEXP_EMAIL = "^([\\w\\-\\.]+)@((\\[([0-9]{1,3}\\.){3}[0-9]{1,3}\\])|(([\\w\\-]+\\.)+)([a-zA-Z]{2,4}))$";
	
	// 32914
	public static final String REGEXP_ZIP = "^\\d{5}\\p{Punct}?\\s?(?:\\d{4})?$";
	
	// 01.1.02 | 11-30-2001 | 2/29/2000
	public static final String REGEXP_DATE = "^(?:(?:(?:0?[13578]|1[02])(\\/|-|\\.)31)\\1|(?:(?:0?[13-9]|1[0-2])(\\/|-|\\.)(?:29|30)\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:0?2(\\/|-|\\.)29\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:(?:0?[1-9])|(?:1[0-2]))(\\/|-|\\.)(?:0?[1-9]|1\\d|2[0-8])\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";
	
	// http://regxlib.com/Default.aspx | http://electronics.cnet.com/electronics/0-6342366-8-8994967-1.html
	public static final String REGEXP_URL = "(http|ftp|https):\\/\\/[\\w\\-_]+(\\.[\\w\\-_]+)+([\\w\\-\\.,@?^=%&amp;:/~\\+#]*[\\w\\-\\@?^=%&amp;/~\\+#])?";
	
	// (?=.{8,}) - contains at least 8 characters,  (?=.*[\d]) - contains at least one digit,  (?=.*[\W]) - contains at least one special character
	public static final String REGEXP_PASSWORD = "^.*(?=.{8,})(?=.*[\\d])(?=.*[\\W]).*$";
}
