package org.mnode.base.mdns.internal;

import java.io.IOException;
import java.util.Map;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceListener;
import javax.jmdns.ServiceTypeListener;

public class ServiceListenerRegistry {

	JmDNS jmdns;
	
	public ServiceListenerRegistry(JmDNS jmdns) {
		this.jmdns = jmdns;
	}
	
	public void addServiceTypeListener(ServiceTypeListener listener, Map<String, String> props) throws IOException {
		jmdns.addServiceTypeListener(listener);
	}
	
	public void removeServiceTypeListener(ServiceTypeListener listener, Map<String, String> props) {
		jmdns.removeServiceTypeListener(listener);
	}
	
	public void addServiceListener(ServiceListener listener, Map<String, String> props) {
		jmdns.addServiceListener(props.get("serviceType"), listener);
	}
	
	public void removeServiceListener(ServiceListener listener, Map<String, String> props) {
		jmdns.removeServiceListener(props.get("serviceType"), listener);
	}
}
