/*
 * $Id: LogCacheManager.java $
 *
 * Created: [18/07/2007]
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
import java.util.Collections;
import java.util.List;
import java.util.Map;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.osgi.service.log.LogEntry;
import org.osgi.service.log.LogListener;
import org.osgi.service.log.LogReaderService;
import org.osgi.service.log.LogService;

/**
 * Manages persistent caching of OSGi log entries.
 * @author Ben
 *
 */
public class LogCacheManager implements LogListener {
	
//	private BundleContext context;
	
	private LogReaderService logReaderService;
	
	private Cache debugCache;

	private Cache infoCache;

	private Cache warnCache;

	private Cache errorCache;

	/**
	 * @param level
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CachedLog> getLogs(int level) {
		Cache cache = getCache(level);
		if (cache != null) {
			List<CachedLog> logs = new ArrayList<CachedLog>();
	        for (Object key: cache.getKeys()) {
	            Element element = cache.get(key);
	            if (element != null) {
	                logs.add((CachedLog) element.getObjectValue());
	            }
	        }
			Collections.sort(logs);
			return logs;
		}
		return Collections.EMPTY_LIST;
	}
	
	/**
	 * @param level
	 * @return
	 */
	public Cache getCache(int level) {
		switch (level) {
			case LogService.LOG_ERROR: return errorCache;
			case LogService.LOG_WARNING: return warnCache;
			case LogService.LOG_INFO: return infoCache;
			case LogService.LOG_DEBUG: return debugCache;
			default: return null;
		}
	}
	
	/**
	 * @param debugCache the debugCache to set
	 */
	public final void setDebugCache(Cache debugCache) {
		this.debugCache = debugCache;
	}

	/**
	 * @param infoCache the infoCache to set
	 */
	public final void setInfoCache(Cache infoCache) {
		this.infoCache = infoCache;
	}

	/**
	 * @param warnCache the warnCache to set
	 */
	public final void setWarnCache(Cache warnCache) {
		this.warnCache = warnCache;
	}

	/**
	 * @param errorCache the errorCache to set
	 */
	public final void setErrorCache(Cache errorCache) {
		this.errorCache = errorCache;
	}

	/**
	 * @param logService the logService to set
	 */
	public final void setLogReaderService(LogReaderService service, Map<?, ?> props) {
		this.logReaderService = service;
//		service.addLogListener(this);
		logReaderService.addLogListener(this);
	}

	/**
	 * @param logService the logService to unset
	 */
	public final void unsetLogReaderService(LogReaderService service, Map<?, ?> props) {
//		service.removeLogListener(this);
		logReaderService.removeLogListener(this);
		this.logReaderService = null;
	}
	
	/**
	 * @param entry
	 */
	public void logged(LogEntry entry) {
        Element element = new Element(new CachedLog.LogKey(entry),
				new CachedLog(entry));
		getCache(entry.getLevel()).put(element);
	}
	
	/**
	 * @param context
	 */
	protected void activate() {
//		this.context = context.getBundleContext();
		logReaderService.addLogListener(this);
	}
	
	/**
	 * @param context
	 */
	protected void deactivate() {
		logReaderService.removeLogListener(this);
	}
}
