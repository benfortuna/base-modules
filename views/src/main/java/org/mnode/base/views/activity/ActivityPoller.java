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
package org.mnode.base.views.activity;

import java.awt.MouseInfo;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ben
 *
 */
public class ActivityPoller extends Thread {

	private long lastMouseMovement;
	
	private List<ActivityListener> listeners;
	
	private ActivityEvent event;
	
	private long idleThreshold;
	
	private boolean idle;
	
	/**
	 * 
	 */
	public ActivityPoller(long idleThreshold) {
		this.idleThreshold = idleThreshold;
		listeners = new ArrayList<ActivityListener>();
		lastMouseMovement = System.currentTimeMillis();
		event = new ActivityEvent(this);
	}
	
	/**
	 * @param l
	 */
	public void addActivityListener(ActivityListener l) {
		listeners.add(l);
	}
	
	/**
	 * @param l
	 */
	public void removeActivityListener(ActivityListener l) {
		listeners.remove(l);
	}
	
	@Override
	public void run() {
		Point lastMousePosition = MouseInfo.getPointerInfo().getLocation();
		while (true) {
			try {
				Thread.sleep(1000);
			}
			catch (InterruptedException ie) {
				break;
			}
			Point currentMousePosition = MouseInfo.getPointerInfo().getLocation();
			if (!currentMousePosition.equals(lastMousePosition)) {
				lastMouseMovement = System.currentTimeMillis();
				lastMousePosition = currentMousePosition;
				if (idle) {
					idle = false;
					fireMouseActive();
				}
			}
			else if (!idle && (System.currentTimeMillis() - lastMouseMovement > idleThreshold)) {
				idle = true;
				fireMouseIdle();
			}
		}
	}
	
	/**
	 * 
	 */
	private void fireMouseIdle() {
		for (ActivityListener l : listeners) {
			l.mouseIdle(event);
		}
	}
	
	/**
	 * 
	 */
	private void fireMouseActive() {
		for (ActivityListener l : listeners) {
			l.mouseActive(event);
		}
	}
}
