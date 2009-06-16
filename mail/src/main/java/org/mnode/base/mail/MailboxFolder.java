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

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;

/**
 * @author Ben
 * 
 */
public class MailboxFolder extends AbstractMailboxAdaptor implements Comparable<MailboxFolder> {

    private static final FolderComparator FOLDER_COMPARATOR = new FolderComparator();

    private Folder folder;

    /**
     * @param mailbox the parent mailbox
     * @param folder the folder delegate
     */
    public MailboxFolder(Mailbox mailbox, Folder folder) {
        super(mailbox);
        this.folder = folder;
    }

    /**
     * {@inheritDoc}
     */
    public String getId() {
        return folder.getFullName();
    }

    /**
     * {@inheritDoc}
     */
    public String getTitle() {
        return folder.getName() + " - " + getMailbox().getName();
    }

    /**
     * {@inheritDoc}
     */
    public int getMessageCount() throws MessagingException {
        return folder.getMessageCount();
    }

    /**
     * {@inheritDoc}
     */
    public Message getMessage(int index) throws MessagingException {
        return folder.getMessage(index + 1);
    }

    /**
     * {@inheritDoc}
     */
    public Message[] getMessages() throws MessagingException {
        return folder.getMessages();
    }

    /**
     * @return the folder
     */
    public final Folder getFolder() {
        return folder;
    }

    /**
     * {@inheritDoc}
     */
    public int compareTo(MailboxFolder o) {
        return FOLDER_COMPARATOR.compare(getFolder(), o.getFolder());
    }
}
