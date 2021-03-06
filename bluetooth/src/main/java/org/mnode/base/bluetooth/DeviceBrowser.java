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
package org.mnode.base.bluetooth;

import java.util.ArrayList;
import java.util.Collections;
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

	public DeviceBrowser() {
		devices = new ArrayList<RemoteDevice>();
	}
	
	/**
	 * @return the devices
	 */
	public List<RemoteDevice> getDevices() {
		return Collections.unmodifiableList(devices);
	}

	/**
	 * Start the bluetooth service.
	 * @throws BluetoothStateException
	 */
	public void start() throws BluetoothStateException {
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
