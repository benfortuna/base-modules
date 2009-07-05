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
package org.mnode.base.xmpp.integration;

import java.io.IOException;
import java.util.Properties;

import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.mnode.base.config.PropertiesConfiguration;
import org.mnode.base.config.UnsupportedValueConversionException;
import org.mnode.base.xmpp.XmppPropertyName;
import org.springframework.osgi.test.AbstractConfigurableBundleCreatorTests;
import org.springframework.osgi.test.platform.Platforms;

public abstract class AbstractXmppIntegrationTest extends AbstractConfigurableBundleCreatorTests {
    
    protected XMPPConnection newConnection() throws XMPPException, IOException, UnsupportedValueConversionException {
        Properties props = new Properties();
        props.load(getClass().getResourceAsStream("/connection.properties"));
        
        PropertiesConfiguration config = new PropertiesConfiguration(props);
        String serviceName = config.get(XmppPropertyName.ServiceName);
        String username = config.get(XmppPropertyName.Username);
        String password = config.get(XmppPropertyName.Password);
        
        XMPPConnection connection = new XMPPConnection(serviceName);
        connection.connect();
        connection.login(username, password);
        return connection;
    }
    
    @Override
    protected final String[] getTestBundlesNames() {
        return new String[] { "org.mnode.base, base-xmpp, 0.0.1-SNAPSHOT",
                "org.mnode.base, base-commons, 0.0.1-SNAPSHOT",
                "net.sourceforge.cglib, com.springsource.net.sf.cglib, 2.1.3",
                "commons-lang, commons-lang, 2.4", 
                "org.slf4j, com.springsource.slf4j.api, 1.5.0", 
                "org.slf4j, com.springsource.slf4j.log4j, 1.5.0", };
    }

    @Override
    protected String getPlatformName() {
        return Platforms.FELIX;
    }

}
