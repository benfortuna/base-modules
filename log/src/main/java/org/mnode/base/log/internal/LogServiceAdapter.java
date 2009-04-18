/*
 * $Id: LogServiceAdapter.java $
 *
 * Created: [13/07/2007]
 *
 * Copyright (c) 2007, Ben Fortuna
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */

package org.mnode.base.log.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.osgi.service.log.LogService;

/**
 * Support for integrating common logging frameworks with the OSGi LogService. This
 * implementation will cache log entries until a log service instance becomes available.
 * @author Ben
 *
 */
public class LogServiceAdapter {

//	private static final String LOG_SERVICE_FILTER = "(" + Constants.OBJECTCLASS
//		+ "=" + LogService.class.getName() + ")";
	
	/**
	 * Initialise the {@link LogService} reference in {@link OsgiLog}.
	 * @author Ben Fortuna
	 */
	/*
	public class LogServiceListener implements ServiceListener {

	    private BundleContext context;
	    
	    /**
	     * @param context
	     *
	    public LogServiceListener(BundleContext context) {
	        this.context = context;
	    }
	    
		/* (non-Javadoc)
		 * @see org.osgi.framework.ServiceListener#serviceChanged(org.osgi.framework.ServiceEvent)
		 *
		public void serviceChanged(ServiceEvent e) {
			if (e.getType() == ServiceEvent.REGISTERED) {
	            LogService service = (LogService) context.getService(
	                    e.getServiceReference());
				LogServiceAdapter.this.logService = service;
			}
			else if (e.getType() == ServiceEvent.UNREGISTERING) {
				LogServiceAdapter.this.logService = null;
			}
		}
	}
	*/
	
	/**
	 * Supports caching of logs until a log service is available.
	 *
	 */
	private class CachedLog {
		
		private int level;
		
		private String message;
		
		private Throwable exception;

		/**
		 * @param level
		 * @param message
		 */
		public CachedLog(int level, String message) {
			this(level, message, null);
		}

		/**
		 * @param level
		 * @param message
		 * @param exception
		 */
		public CachedLog(int level, String message, Throwable exception) {
			this.level = level;
			this.message = message;
			this.exception = exception;
		}
	}

	private List<CachedLog> cache;
	
	private LogService logService;
	
//	private LogServiceListener logServiceListener;
	
	private static LogServiceAdapter instance;

	/**
	 * Default constructor.
	 */
	public LogServiceAdapter() {
		cache = new ArrayList<CachedLog>();
		LogServiceAdapter.instance = this;
	}
	
	/**
	 * @param context
	 */
	/*
	public LogServiceAdapter(BundleContext context) throws InvalidSyntaxException {
        logServiceListener = new LogServiceListener(context);
		
		context.addServiceListener(logServiceListener, LOG_SERVICE_FILTER);
		
		// check for log service availability..
		ServiceReference reference = context.getServiceReference(
				LogService.class.getName());
		
		if (reference != null) {
			this.logService = (LogService) context.getService(reference);
		}
	}
	*/
	
	/**
	 * @param logService the logService to set
	 */
	public final void setLogService(LogService logService, Map<?, ?> props) {
		this.logService = logService;
		
		// clear cache..
		for (CachedLog log : cache) {
			if (log.exception != null) {
				logService.log(log.level, log.message, log.exception);
			}
			else {
				logService.log(log.level, log.message);
			}
		}
		cache.clear();
	}

	/**
	 * @param logService the logService to unset
	 */
	public final void unsetLogService(LogService logService, Map<?, ?> props) {
		this.logService = null;
	}
	
	/**
	 * @param level
	 * @param message
	 */
	public void log(String logId, int level, Object message) {
		if (logService != null) {
			logService.log(level, logId + " -> " + message.toString());
		}
		else {
			cache.add(new CachedLog(level, logId + " -> " + message.toString()));
		}
	}

	/**
	 * @param level
	 * @param message
	 * @param e
	 */
	public void log(String logId, int level, Object message, Throwable e) {
		if (logService != null) {
			logService.log(level, logId + " -> " + message.toString(), e);
		}
		else {
			cache.add(new CachedLog(level, logId + " -> " + message.toString(), e));
		}
	}
	
	/**
	 * @param context
	 */
	/*
	public void shutdown(BundleContext context) {
		context.removeServiceListener(logServiceListener);
	}
	*/

	/**
	 * @return the instance
	 */
	public static final LogServiceAdapter getInstance() {
		return instance;
	}

	/**
	 * @param instance the instance to set
	 */
	/*
	public static final void setInstance(LogServiceAdapter instance) {
		LogServiceAdapter.instance = instance;
	}
	*/
}
