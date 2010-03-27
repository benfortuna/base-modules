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

import org.jivesoftware.smack.ChatManager;
import org.jivesoftware.smack.XMPPConnection;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;


/**
 * @author fortuna
 *
 */
public class ChatContextManagerImplTest {

	private ChatContextManagerImpl manager;
	
	private Mockery context = new Mockery();
	
	@Before
	public void runBeforeEachTest() {
		manager = new ChatContextManagerImpl();
	}
	
	@After
	public void runAfterEachTest() {
		context.assertIsSatisfied();
	}
	
	@Test
	@Ignore
	public void testGetContext() {
		final XMPPConnection connection = context.mock(XMPPConnection.class);
		final ChatManager chatManager = context.mock(ChatManager.class);
		
		context.checking(new Expectations() {
			{
				one(connection).getChatManager();
					will(returnValue(chatManager));
			}
		});
		manager.connectionCreated(connection);
	}
}
