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
package org.mnode.base.repository.integration;

import java.io.File;

import javax.jcr.Repository;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jcrom.Jcrom;
import org.mnode.base.repository.RepositoryDescriptor;
import org.osgi.framework.ServiceReference;
import org.springframework.osgi.test.AbstractConfigurableBundleCreatorTests;

/**
 * @author fortuna
 *
 */
public class ServiceIntegrationTest extends AbstractConfigurableBundleCreatorTests {

	private static final Log LOG = LogFactory.getLog(ServiceIntegrationTest.class);
	
	@Override
	protected void onSetUp() throws Exception {
		super.onSetUp();
		
		bundleContext.registerService(RepositoryDescriptor.class.getName(), 
				new RepositoryDescriptor("testRepo", new File(System.getProperty("java.io.tmpdir"), "ServiceIntegrationTestRepo")), null);
	}
	
	/**
	 * @throws NamingException 
	 * 
	 */
	public void testAccountManagerService() throws NamingException {
//		LOG.info(getManifest());
//		LOG.info(getManifestLocation());
//		Repository repository = (Repository) new InitialContext().lookup("testRepo");
		
		ServiceReference reference = bundleContext.getServiceReference(Jcrom.class.getName());
		Jcrom jcrom = (Jcrom) bundleContext.getService(reference);
		assertNotNull(jcrom);

//		reference = bundleContext.getServiceReference(Repository.class.getName());
//		Repository repository = (Repository) bundleContext.getService(reference);
		Repository repository = (Repository) new InitialContext().lookup("testRepo");
		assertNotNull(repository);
	}
	
	@Override
	protected String[] getTestBundlesNames() {
		 return new String[] {"org.apache.felix, org.osgi.compendium, 1.2.0",
				 "org.apache.felix, javax.servlet, 1.0.0",
				 "commons-collections, commons-collections, 3.2.1",
                 "commons-io, commons-io, 1.4",
//				 "org.slf4j, com.springsource.slf4j.log4j, 1.5.0",
                 "javax.jcr, jcr, 2.0",
				 "org.apache.jackrabbit, jackrabbit-api, 2.0.0",
				 "org.apache.jackrabbit, jackrabbit-jcr-commons, 2.0.0",
                 "org.apache.jackrabbit, jackrabbit-spi, 2.0.0",
                 "org.apache.jackrabbit, jackrabbit-spi-commons, 2.0.0",
                 "org.apache.derby, derby, 10.5.3.0_1",
//				 "org.apache.sling, org.apache.sling.jcr.api, 2.0.2-incubator",
//				 "org.apache.sling, org.apache.sling.jcr.base, 2.0.2-incubator",
//				 "org.apache.sling, org.apache.sling.jcr.jackrabbit.server, 2.0.2-incubator",
				 "org.mnode.base, base-repository, 0.0.1-SNAPSHOT"};
	}
	
//	@Override
//	protected String getPlatformName() {
//		return Platforms.FELIX;
//	}
}
