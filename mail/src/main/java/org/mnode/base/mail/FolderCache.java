/**
 * This file is part of Base Modules.
 *
 * Copyright (c) 2009, Ben Fortuna [fortuna@micronode.com]
 *
 * Base Modules is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Base Modules is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Base Modules.  If not, see <http://www.gnu.org/licenses/>.
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
     * @param folder a folder instance
     * @return a cached name for the specified folder
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
     * @param folder a folder instance
     * @return the cached unread message count for the specified folder
     */
    public int getUnreadMessageCount(Folder folder) {
        Integer unreadCount = folderUnreadCount.get(folder);
        if (unreadCount == null) {
            try {
                if ((folder.getType() & Folder.HOLDS_MESSAGES) > 0) {
                    unreadCount = folder.getUnreadMessageCount();
                } else {
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
     * @param folder a folder instance
     * @return the cached value of {@link Folder#exists()}
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

    /**
     * {@inheritDoc}
     */
    public void folderCreated(FolderEvent e) {
        folderExists.remove(e.getFolder());
    }

    /**
     * {@inheritDoc}
     */
    public void folderDeleted(FolderEvent e) {
        folderExists.remove(e.getFolder());
    }

    /**
     * {@inheritDoc}
     */
    public void folderRenamed(FolderEvent e) {
        folderNames.remove(e.getFolder());
    }

    /**
     * {@inheritDoc}
     */
    public void messagesAdded(MessageCountEvent e) {
        folderUnreadCount.remove(e.getSource());
    }

    /**
     * {@inheritDoc}
     */
    public void messagesRemoved(MessageCountEvent e) {
        folderUnreadCount.remove(e.getSource());
    }

    /**
     * @param folder a folder instance
     */
    private void addListenerTo(Folder folder) {
        folder.addFolderListener(this);
        folder.addMessageCountListener(this);
    }
}
