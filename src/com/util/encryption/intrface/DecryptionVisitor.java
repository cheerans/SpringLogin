package com.util.encryption.intrface;

import com.model.OurUser;

public interface DecryptionVisitor {
	public OurUser visit(OurUser inObj);
}
