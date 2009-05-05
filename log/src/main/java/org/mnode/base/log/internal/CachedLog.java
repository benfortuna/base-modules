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
package org.mnode.base.log.internal;

import java.io.Serializable;

import org.osgi.framework.Constants;
import org.osgi.service.log.LogEntry;

/**
 * A serializable log entry.
 * @author Ben
 *
 */
public class CachedLog implements Serializable, Comparable<CachedLog> {

	public static class LogKey implements Serializable, Comparable<LogKey> {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 8762028820633595985L;

		long bundleId;
		
		long time;
		
		/**
		 * @param bundleId
		 * @param time
		 */
		protected LogKey(LogEntry entry) {
			this.bundleId = entry.getBundle().getBundleId();
			this.time = entry.getTime();
		}
		
		@Override
		public String toString() {
			return bundleId + "-" + time;
		}
		
		/* (non-Javadoc)
		 * @see java.lang.Comparable#compareTo(java.lang.Object)
		 */
		public int compareTo(LogKey arg0) {
			return (int) (time - arg0.time);
		}
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -3877806351389895872L;
	
	private String bundleName;
	
	private String serviceClassName;
	
	private int level;
	
	private String message;
	
	private Throwable exception;
	
	private long time;

	/**
	 * @param entry
	 * @param context
	 */
	public CachedLog(LogEntry entry) {
		this.bundleName = entry.getBundle().getSymbolicName();
		this.level = entry.getLevel();
		this.message = entry.getMessage();
		this.exception = entry.getException();
		this.time = entry.getTime();

		// ensure bundle is active (i.e. not stopping) to avoid deadlocks with
		// framework logging bundle..
		/*
		if (entry.getServiceReference() != null
				&& entry.getBundle().getState() == Bundle.ACTIVE) {
			
			Object service = context.getService(entry.getServiceReference());
			if (service != null) {
				this.serviceClassName = service.getClass().getName();
			}
		}
		*/
		if (entry.getServiceReference() != null) {
			serviceClassName = entry.getServiceReference().getProperty(
					Constants.OBJECTCLASS).toString();
		}
	}

	/**
	 * @return the bundleName
	 */
	public final String getBundleName() {
		return bundleName;
	}

	/**
	 * @return the serviceClass
	 */
	public final Class<?> getServiceClass() throws ClassNotFoundException {
		return Class.forName(serviceClassName);
	}

	/**
	 * @return the level
	 */
	public final int getLevel() {
		return level;
	}

	/**
	 * @return the message
	 */
	public final String getMessage() {
		return message;
	}

	/**
	 * @return the exception
	 */
	public final Throwable getException() {
		return exception;
	}

	/**
	 * @return the time
	 */
	public final long getTime() {
		return time;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(CachedLog arg0) {
		return (int) (time - arg0.time);
	}
}
