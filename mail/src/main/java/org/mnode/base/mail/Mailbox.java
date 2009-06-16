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
     * @param name a mailbox name
     * @param props session properties
     * @param auth session authenticator
     * @throws NoSuchProviderException if the provider for the mailbox isn't found
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
     * @param name a folder name
     * @param type a folder type
     * @throws MessagingException where an error occurs retrieving the folder
     * @see Folder#HOLDS_FOLDERS
     * @see Folder#HOLDS_MESSAGES
     */
    public void addFolder(String name, int type) throws MessagingException {
        Folder folder = localStore.getFolder(name);
        folder.create(type);
        folders = null;
    }

    /**
     * @return an array of folders in the mailbox
     * @throws MessagingException where an error occurs retrieving the folders
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

            // if (folders.length == 0) {
            // folders = getDefaultFolders();
            // }
        }
        return folders;
    }
}
