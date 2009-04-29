/**
 * 
 */
package org.mnode.base.mdns.integration;

import java.util.Hashtable;

import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceListener;
import javax.jmdns.ServiceTypeListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.osgi.test.AbstractConfigurableBundleCreatorTests;

/**
 * @author fortuna
 *
 */
public class ServiceListenerTest extends AbstractConfigurableBundleCreatorTests {

	private static final Log LOG = LogFactory.getLog(ServiceListenerTest.class);
	
	public void testGetServiceBrowser() {
//		ServiceReference reference = bundleContext.getServiceReference(ServiceBrowser.class.getName());
//		ServiceBrowser serviceBrowser = (ServiceBrowser) bundleContext.getService(reference);
//		assertNotNull(serviceBrowser);
		ServiceTypeListener typeListener = new ServiceTypeListener() {
			@Override
			public void serviceTypeAdded(ServiceEvent arg0) {
				LOG.info("Service type added: " + arg0.getName());
			}
		};
		bundleContext.registerService(ServiceTypeListener.class.getName(), typeListener, null);
		
		ServiceListener listener = new ServiceListener() {
			@Override
			public void serviceAdded(ServiceEvent arg0) {
				LOG.info("Service added: " + arg0.getName());
			}
			@Override
			public void serviceRemoved(ServiceEvent arg0) {
				LOG.info("Service remove: " + arg0.getName());
			}
			@Override
			public void serviceResolved(ServiceEvent arg0) {
				LOG.info("Service resolved: " + arg0.getName());
			}
		};
		Hashtable<String, String> props = new Hashtable<String, String>();
		props.put("serviceType", "_tcp.0.0.1");
		bundleContext.registerService(ServiceListener.class.getName(), listener, props);
	}
	
	@Override
	protected String[] getTestBundlesNames() {
		return new String[] { "org.mnode.base, org.mnode.base.mdns, 0.0.1-SNAPSHOT" };
	}
	
//	protected boolean createManifestOnlyFromTestClass() {
//		return false;
//	}
}
