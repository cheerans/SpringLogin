package com.util.encryption.intrface;

import com.model.OurUser;

public interface EncryptionVisitor {
	public OurUser visit(OurUser inObj);
	public String visit(String inObj);
}
