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

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Ben
 * 
 */
public class MailboxMessage extends AbstractMailboxAdaptor {

    private static final Log LOG = LogFactory.getLog(MailboxMessage.class);

    private Message message;

    /**
     * @param mailbox the parent mailbox
     * @param message a message delegate
     */
    public MailboxMessage(Mailbox mailbox, Message message) {
        super(mailbox);
        this.message = message;
    }

    /**
     * {@inheritDoc}
     */
    public String getId() {
        try {
            if (message instanceof MimeMessage) {
                return ((MimeMessage) message).getMessageID();
            }
            return message.getSubject();
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public String getTitle() {
        StringBuilder b = new StringBuilder();
        try {
            String subject = message.getSubject();
            if (StringUtils.isNotEmpty(subject)) {
                b.append(subject);
            } else {
                b.append("<No subject>");
            }
            b.append(" - ");

            String sender = getSender();
            if (sender != null) {
                b.append(sender);
            } else {
                b.append("<Unknown sender>");
            }
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return b.toString();
    }

    /**
     * {@inheritDoc}
     */
    public int getMessageCount() throws MessagingException {
        return 1;
    }

    /**
     * {@inheritDoc}
     */
    public Message getMessage(int index) throws MessagingException {
        if (index > 0) {
            throw new IndexOutOfBoundsException("Index [" + index + "] out of bounds");
        }
        if (index < 0) {
            return null;
        }
        return message;
    }

    /**
     * {@inheritDoc}
     */
    public Message[] getMessages() throws MessagingException {
        return new Message[] { message };
    }

    /**
     * @return the message
     */
    public final Message getMessage() {
        return message;
    }

    /**
     * @return the sender of the message
     */
    public String getSender() {
        try {
            Address[] senders = message.getFrom();
            if (senders != null && senders.length > 0) {
                InternetAddress from = (InternetAddress) senders[0];
                if (from.getPersonal() != null) {
                    return from.getPersonal();
                }
                return from.getAddress();
            }
        } catch (MessagingException me) {
            LOG.error("Error retrieving message sender", me);
        }
        return null;
    }
}
