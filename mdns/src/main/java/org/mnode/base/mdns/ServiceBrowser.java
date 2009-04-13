/**
 * 
 */
package org.mnode.base.mdns;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;
import javax.jmdns.ServiceTypeListener;

/**
 * @author fortuna
 *
 */
public class ServiceBrowser implements ServiceListener, ServiceTypeListener {

	private JmDNS jmdns;
	
	private List<ServiceInfo> services;
	
	public ServiceBrowser() throws IOException {
		services = new ArrayList<ServiceInfo>();
		this.jmdns = JmDNS.create();
		jmdns.addServiceTypeListener(this);
	}
	
	/**
	 * @return the list of currently available services
	 */
	public List<ServiceInfo> getServices() {
		return Collections.unmodifiableList(services);
	}

	@Override
	public void serviceTypeAdded(ServiceEvent e) {
		jmdns.addServiceListener(e.getType(), this);
	}
	
	/* (non-Javadoc)
	 * @see javax.jmdns.ServiceListener#serviceAdded(javax.jmdns.ServiceEvent)
	 */
	public void serviceAdded(ServiceEvent e) {
		jmdns.requestServiceInfo(e.getType(), e.getName());
	}

	/* (non-Javadoc)
	 * @see javax.jmdns.ServiceListener#serviceRemoved(javax.jmdns.ServiceEvent)
	 */
	public void serviceRemoved(ServiceEvent e) {
		services.remove(e.getInfo());
	}

	/* (non-Javadoc)
	 * @see javax.jmdns.ServiceListener#serviceResolved(javax.jmdns.ServiceEvent)
	 */
	public void serviceResolved(ServiceEvent e) {
		services.add(e.getInfo());
	}

}
