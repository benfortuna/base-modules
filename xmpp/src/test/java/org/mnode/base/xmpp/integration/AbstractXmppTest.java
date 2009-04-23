package org.mnode.base.xmpp.integration;

import org.springframework.osgi.test.AbstractConfigurableBundleCreatorTests;

public abstract class AbstractXmppTest extends AbstractConfigurableBundleCreatorTests {
	
	@Override
	protected final String[] getTestBundlesNames() {
		return new String[] { "org.mnode.base, xmpp, 0.0.1-SNAPSHOT",
			"net.sourceforge.cglib, com.springsource.net.sf.cglib, 2.1.3"};
	}

}
