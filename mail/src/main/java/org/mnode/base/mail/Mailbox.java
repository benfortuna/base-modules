/**
 * 
 */
package org.mnode.base.mail;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Folder;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;

/**
 * @author fortuna
 *
 */
public class Mailbox {

	private static final FolderComparator FOLDER_SORTER = new FolderComparator();
	
	private String name;
	
	private Store localStore;
	
	private MailboxFolder[] folders;
	
	/**
	 * @param protocol
	 * @param host
	 * @throws NoSuchProviderException
	 */
	public Mailbox(String name, Properties props, Authenticator auth) throws NoSuchProviderException {
		this.name = name;
        Session session = Session.getInstance(props, auth);
        localStore = session.getStore();
	}

	/**
	 * @return the name
	 */
	public final String getName() {
		return name;
	}
	
	/**
	 * @param name
	 * @param type
	 * @throws MessagingException
	 */
	public void addFolder(String name, int type) throws MessagingException {
		Folder folder = localStore.getFolder(name);
		folder.create(type);
		folders = null;
	}
	
	/**
	 * @return
	 * @throws MessagingException 
	 */
	public MailboxFolder[] getFolders() throws MessagingException {
		if (folders == null) {
			if (!localStore.isConnected()) {
				localStore.connect();
			}
			List<MailboxFolder> folderList = new ArrayList<MailboxFolder>();
			for (Folder folder : localStore.getDefaultFolder().list()) {
				folderList.add(new MailboxFolder(this, folder));
			}
			Collections.sort(folderList);
			folders = folderList.toArray(new MailboxFolder[folderList.size()]);
			
//			if (folders.length == 0) {
//				folders = getDefaultFolders();
//			}
		}
		return folders;
	}
}
