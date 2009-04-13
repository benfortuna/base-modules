/**
 * 
 */
package org.mnode.base.bluetooth;

import java.util.ArrayList;
import java.util.List;

import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.DeviceClass;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.RemoteDevice;

import net.java.dev.marge.inquiry.DeviceDiscoverer;
import net.java.dev.marge.inquiry.InquiryListener;

/**
 * @author fortuna
 *
 */
public class DeviceBrowser implements InquiryListener {
	
	private List<RemoteDevice> devices;

	public DeviceBrowser() throws BluetoothStateException {
		devices = new ArrayList<RemoteDevice>();
		DeviceDiscoverer.getInstance().startInquiry(DiscoveryAgent.GIAC, this);
	}
	
	public void deviceDiscovered(RemoteDevice device, DeviceClass deviceClass) {
		devices.add(device);
	}
	
	public void inquiryCompleted(RemoteDevice[] arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void inquiryError() {
		// TODO Auto-generated method stub
		
	}
}
