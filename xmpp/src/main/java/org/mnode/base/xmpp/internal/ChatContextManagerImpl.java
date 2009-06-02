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
package org.mnode.base.xmpp.internal;

import java.util.HashMap;
import java.util.Map;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManagerListener;
import org.jivesoftware.smack.ConnectionCreationListener;
import org.jivesoftware.smack.XMPPConnection;
import org.mnode.base.xmpp.ChatContext;
import org.mnode.base.xmpp.ChatContextManager;

/**
 * A chat context manages that maintains contexts in a map.
 * 
 * @author fortuna
 *
 */
public class ChatContextManagerImpl implements ChatContextManager,
        ConnectionCreationListener {

    private Map<Chat, ChatContext> contexts;

    /**
     * 
     */
    public ChatContextManagerImpl() {
        contexts = new HashMap<Chat, ChatContext>();
        XMPPConnection.addConnectionCreationListener(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ChatContext getContext(Chat chat) {
        return contexts.get(chat);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void connectionCreated(final XMPPConnection connection) {
        connection.getChatManager().addChatListener(new ChatManagerListener() {
            @Override
            public void chatCreated(Chat chat, boolean createdLocally) {
                contexts.put(chat, new ChatContext(connection));
            }
        });
    }
}
