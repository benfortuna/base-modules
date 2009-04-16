package org.mnode.base.account.internal;

import java.util.List;

import org.mnode.base.account.AccountListener;


public class AccountManager {

	private List<AccountListener> listeners;
	
	public void addListener(AccountListener listener) {
		listeners.add(listener);
	}
	
	public void removeListener(AccountListener listener) {
		listeners.remove(listener);
	}
	
	public void addAccount(Object account) {
		// persist account..
		
		fireAccountCreated(account);
	}
	
	private void fireAccountCreated(Object account) {
		for (AccountListener l : listeners) {
			l.accountCreated(account);
		}
	}
}
