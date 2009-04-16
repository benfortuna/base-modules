package org.mnode.base.account;

public interface AccountListener {

	void accountCreated(Object account);
	
	void accountDeleted(Object account);
	
	void accountEnabled(Object account);
	
	void accountDisabled(Object account);
	
	void accountUpdated(Object account);
}
