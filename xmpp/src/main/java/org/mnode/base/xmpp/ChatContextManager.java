package org.mnode.base.xmpp;

import org.jivesoftware.smack.Chat;

public interface ChatContextManager {

	ChatContext getContext(Chat chat);
}
