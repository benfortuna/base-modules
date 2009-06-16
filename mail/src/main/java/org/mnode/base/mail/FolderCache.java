/*
 * $Id: FolderCache.java $
 *
 * Created: [30/08/2008]
 *
 * Copyright (c) 2008, Ben Fortuna
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */

package org.mnode.base.mail;

import java.util.HashMap;
import java.util.Map;

import javax.mail.Folder;
import javax.mail.MessagingException;
import javax.mail.event.FolderEvent;
import javax.mail.event.FolderListener;
import javax.mail.event.MessageCountEvent;
import javax.mail.event.MessageCountListener;

/**
 * @author Ben
 *
 */
public class FolderCache implements FolderListener, MessageCountListener {
	
	private Map<Folder, String> folderNames;
	
	private Map<Folder, Integer> folderUnreadCount;
	
	private Map<Folder, Boolean> folderExists;

	/**
	 * 
	 */
	public FolderCache() {
        folderNames = new HashMap<Folder, String>();
        folderUnreadCount = new HashMap<Folder, Integer>();
        folderExists = new HashMap<Folder, Boolean>();
	}
	
	/**
	 * @param folder
	 * @return
	 */
	public String getName(Folder folder) {
		String folderName = folderNames.get(folder);
		if (folderName == null) {
			folderName = folder.getName();
			folderNames.put(folder, folderName);
			addListenerTo(folder);
		}
		return folderName;
	}
	
	/**
	 * @param folder
	 * @return
	 */
	public int getUnreadMessageCount(Folder folder) {
		Integer unreadCount = folderUnreadCount.get(folder);
		if (unreadCount == null) {
			try {
				if ((folder.getType() & Folder.HOLDS_MESSAGES) > 0) {
					unreadCount = folder.getUnreadMessageCount();
				}
				else {
					unreadCount = 0;
				}
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				// assume it's zero..
				unreadCount = 0;
			}
			folderUnreadCount.put(folder, unreadCount);
			addListenerTo(folder);
		}
		return unreadCount;
	}
	
	/**
	 * @param folder
	 * @return
	 */
	public boolean isExisting(Folder folder) {
		Boolean exists = folderExists.get(folder);
		if (exists == null) {
			try {
				exists = folder.exists();
			} catch (MessagingException e) {
				e.printStackTrace();
				// assume it doesnt..
				exists = false;
			}
			folderExists.put(folder, exists);
			addListenerTo(folder);
		}
		return exists;
	}
	
	public void folderCreated(FolderEvent e) {
		folderExists.remove(e.getFolder());
	}
	
	public void folderDeleted(FolderEvent e) {
		folderExists.remove(e.getFolder());
	}
	
	public void folderRenamed(FolderEvent e) {
		folderNames.remove(e.getFolder());
	}

	public void messagesAdded(MessageCountEvent e) {
		folderUnreadCount.remove(e.getSource());
	}
	
	public void messagesRemoved(MessageCountEvent e) {
		folderUnreadCount.remove(e.getSource());
	}
	
	/**
	 * @param folder
	 */
	private void addListenerTo(Folder folder) {
		folder.addFolderListener(this);
		folder.addMessageCountListener(this);
	}
}
