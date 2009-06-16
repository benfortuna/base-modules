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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.internet.MimeMessage;
import javax.mail.search.HeaderTerm;
import javax.mail.search.MessageIDTerm;
import javax.mail.search.OrTerm;
import javax.mail.search.SearchTerm;

/**
 * Utility methods for working with message content.
 * @author Ben Fortuna
 */
public final class MessageUtils {

	/**
	 * Constructor made private to enforce static nature.
	 */
	private MessageUtils() {
	}
    
    /**
     * Returns the default part for the specified message
     * 
     * @param message
     * @return Part the default part
     */
    public static Part getDefaultPart(Message message) throws MessagingException, IOException {

        Part part = message;
        
        // ensure part is not an embedded multipart..
        while (part.isMimeType("multipart/*")) {
            Multipart multi = (Multipart) part.getContent();
            
            int partIndex = 0;
            for (int i = 0; i<multi.getCount(); i++) {
                // display html by default..
                if (multi.getBodyPart(i).isMimeType("text/html")) {
                    partIndex = i;
                }
            }
            part = multi.getBodyPart(partIndex);
        }
                        
        // if we found an embedded message, display!
        if (part.isMimeType("message/*")) {
            return getDefaultPart((Message)part);
        }
        return part;
    }

    /**
     * @param message
     * @return
     */
    public static boolean hasAttachments(Message message) {
        try {
            if (message.getFileName() != null) {
                return true;
            }
            else if (message.isMimeType("multipart/*")) {
                Multipart parts = (Multipart) message.getContent();
                for (int i = 0; i < parts.getCount(); i++) {
                    Part part = parts.getBodyPart(i);
                    if (!part.isMimeType("text/*")) {
                        return true;
                    }
                }
            }
        }
        catch (MessagingException me) {
            me.printStackTrace();
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return false;
    }
    
    /**
     * @param message
     * @return
     */
    public static String getSubject(Message message) {
        try {
            return message.getSubject();
        } catch (MessagingException e) {
        }
        return "<No Subject>";
    }
    
    /**
     * @param message
     * @return
     */
    public static String getSender(Message message) {
        try {
            if (message.getFrom().length > 0) {
                return message.getFrom()[0].toString();
            }
        } catch (MessagingException e) {
        }
        return "<Unknown>";
    }
    
    /**
     * @param message
     * @return
     */
    public static String getDate(Message message) {
        try {
            if (message.getReceivedDate() != null) {
                return message.getReceivedDate().toString();
            }
            return message.getSentDate().toString();
        } catch (MessagingException e) {
        }
        return "";
    }
    
	/**
	 * Converts a given string to an HTML string. This method is useful
	 * for displaying plain text in a browser - by converting to HTML,
	 * word-wrapping is introduced, and hypertext links are activated.
	 *
	 */
	public static String toHtml(String content) {

		StringBuffer buffer = new StringBuffer();

		// initialise html..
		buffer.append("<html><body>");

		StringTokenizer tokenizer = new StringTokenizer(content,"\n\t\r <>",true);
		String token;

		while (tokenizer.hasMoreTokens()) {

			token = tokenizer.nextToken();

			// if hyperlink, activate..
			if (token.indexOf("://") >= 0) {
				buffer.append("<a href=\"");
				buffer.append(token);
				buffer.append("\" target=\"_blank\">");
				buffer.append(token);
				buffer.append("</a>");
			}
			// if mailing address, activate..
			else if (token.indexOf("@") >= 0) {
				//buffer.append("<a href=\"mail?action=compose&to=");

				if (token.startsWith("mailto:")) {

					buffer.append("<a href=\"");
				}
				else {

					buffer.append("<a href=\"mailto:");
				}

				buffer.append(token);
				//buffer.append("\" target=\"_blank\">");
				buffer.append("\">");
				buffer.append(token);
				buffer.append("</a>");
			}
			// convert newline to line break..
			else if ("\n".equals(token)) {
				buffer.append("<br>");
			}
			// convert special characters..
			else if ("<".equals(token)) {
				buffer.append("&lt;");
			}
			else if (">".equals(token)) {
				buffer.append("&gt;");
			}
			else {
				buffer.append(token);
			}
		}

		// finalise html..
		buffer.append("</body></html>");

		return buffer.toString();
	}

	/**
	 * @param message
	 * @return
	 * @throws MessagingException
	 */
	public static MailboxMessage[] findRelatedMessages(MailboxMessage message) throws MessagingException {
	    return findRelatedMessages(message, true);
	}
	
	/**
     * @param message
     * @param includeReplies
     * @return
     * @throws MessagingException
     */
    public static MailboxMessage[] findRelatedMessages(MailboxMessage message, boolean includeReplies) throws MessagingException {
        List<MailboxMessage> relatedMessages = new ArrayList<MailboxMessage>();
        
        if (message.getMessage() instanceof MimeMessage) {
            MimeMessage mimeMessage = (MimeMessage) message.getMessage();
            SearchTerm inReplyToTerm = null;
            SearchTerm messageIdTerm = null;
            if (mimeMessage.getMessageID() != null && includeReplies) {
                inReplyToTerm = new HeaderTerm("In-reply-to", mimeMessage.getMessageID());
            }
            if (mimeMessage.getHeader("In-reply-to") != null) {
                List<SearchTerm> messageIdTerms = new ArrayList<SearchTerm>();
                for (String mid : mimeMessage.getHeader("In-reply-to")) {
                    messageIdTerms.add(new MessageIDTerm(mid));
                }
                if (messageIdTerms.size() == 1) {
                    messageIdTerm = messageIdTerms.get(0);
                }
                else {
                    messageIdTerm = new OrTerm(messageIdTerms.toArray(new SearchTerm[messageIdTerms.size()]));
                }
            }
            SearchTerm findRelatedTerm = null;
            if (messageIdTerm == null) {
                findRelatedTerm = inReplyToTerm;
            }
            else if (inReplyToTerm == null) {
                findRelatedTerm = messageIdTerm;
            }
            else {
                findRelatedTerm = new OrTerm(inReplyToTerm, messageIdTerm);
            }
            
            if (findRelatedTerm != null) {
                Message[] matches = mimeMessage.getFolder().search(findRelatedTerm);
                for (Message m : matches) {
                    MailboxMessage related = new MailboxMessage(message.getMailbox(), m);
                    relatedMessages.add(related);
                    relatedMessages.addAll(Arrays.asList(findRelatedMessages(related, false)));
                }
            }
        }
	    
	    return relatedMessages.toArray(new MailboxMessage[relatedMessages.size()]);
	}
}
