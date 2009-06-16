/*
 * $Id: MailboxAdaptor.java $
 *
 * Created: [13/09/2008]
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

import javax.mail.Message;
import javax.mail.MessagingException;

/**
 * @author Ben
 *
 */
public interface MailboxAdaptor {

	/**
	 * @return a unique identifier for the adaptor
	 */
	String getId();
	
	/**
	 * @return the title of the adaptor
	 */
	String getTitle();
	
	/**
	 * @return the mailbox associated with the adaptor
	 */
	Mailbox getMailbox();
	
	/**
	 * Allows for retrieval of message count without necessarily
	 * constructing all messages
	 * @return a count of messages applicable to the adaptor
	 */
	int getMessageCount() throws MessagingException;
	
	/**
	 * @param index
	 * @return
	 * @throws MessagingException
	 */
	Message getMessage(int index) throws MessagingException;
	
	/**
	 * @return messages applicable to the adaptor
	 */
	Message[] getMessages() throws MessagingException;
}
