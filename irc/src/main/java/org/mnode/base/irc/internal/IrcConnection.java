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

package org.mnode.base.irc.internal;

import java.util.ArrayList;
import java.util.List;

import org.jibble.pircbot.DccChat;
import org.jibble.pircbot.DccFileTransfer;
import org.jibble.pircbot.PircBot;
import org.mnode.base.irc.ChatHandler;
import org.mnode.base.irc.FileTransferHandler;
import org.mnode.base.irc.MessageHandler;

/**
 * @author fortuna
 *
 */
public class IrcConnection extends PircBot {

    private final List<MessageHandler> messageHandlers;
    
    private final List<ChatHandler> chatHandlers;
    
    private final List<FileTransferHandler> fileTransferHandlers;
    
    /**
     * 
     */
    public IrcConnection() {
        messageHandlers = new ArrayList<MessageHandler>();
        chatHandlers = new ArrayList<ChatHandler>();
        fileTransferHandlers = new ArrayList<FileTransferHandler>();
    }
    
    /**
     * @param handler
     */
    public void addMessageHandler(MessageHandler handler) {
        messageHandlers.add(handler);
    }
    
    /**
     * @param handler
     */
    public void removeMessageHandler(MessageHandler handler) {
        messageHandlers.remove(handler);
    }
    
    /**
     * @param handler
     */
    public void addChatHandler(ChatHandler handler) {
        chatHandlers.add(handler);
    }
    
    /**
     * @param handler
     */
    public void removeChatHandler(ChatHandler handler) {
        chatHandlers.remove(handler);
    }
    
    /**
     * @param handler
     */
    public void addFileTransferHandler(FileTransferHandler handler) {
        fileTransferHandlers.add(handler);
    }
    
    /**
     * @param handler
     */
    public void removeFileTransferHandler(FileTransferHandler handler) {
        fileTransferHandlers.remove(handler);
    }
    
    @Override
    protected void onMessage(String channel, String sender, String login, String hostname, String message) {
        for (MessageHandler handler : messageHandlers) {
            handler.handleMessage(channel, sender, login, hostname, message);
        }
    }
    
    @Override
    protected void onIncomingChatRequest(DccChat chat) {
        for (ChatHandler handler : chatHandlers) {
            handler.handleIncomingChatRequest(chat);
        }
    }
    
    @Override
    protected void onIncomingFileTransfer(DccFileTransfer transfer) {
        for (FileTransferHandler handler : fileTransferHandlers) {
            handler.handleIncomingFileTransfer(transfer);
        }
    }
}
