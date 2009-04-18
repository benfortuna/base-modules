/*
 * $Id: OsgiLogFactory.java Exp $
 *
 * Created: [06/09/2006]
 *
 * Copyright (c) 2006, Ben Fortuna
 * All rights reserved.
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
package org.mnode.base.log.internal.jcl;

import java.util.HashMap;
import java.util.Map;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogConfigurationException;
import org.apache.commons.logging.LogFactory;

/**
 * A commons-logging LogFactory implementation for creating OSGi-compatible logs.
 * @author Ben Fortuna
 */
public class OsgiLogFactory extends LogFactory {

	private static final String KEY_LOG_LEVEL = "net.modularity.osgi.log.LogLevel";
	
	private Map<String, Object> attributes;
	
	private Map<String, Log> logs;
	
	/**
	 * Default constructor.
	 */
	public OsgiLogFactory() {
		attributes = new HashMap<String, Object>();
		logs = new HashMap<String, Log>();
	}
	
	/* (non-Javadoc)
	 * @see org.apache.commons.logging.LogFactory#getAttribute(java.lang.String)
	 */
	@Override
	public Object getAttribute(String name) {
		return attributes.get(name);
	}

	/* (non-Javadoc)
	 * @see org.apache.commons.logging.LogFactory#getAttributeNames()
	 */
	@Override
	public String[] getAttributeNames() {
		return attributes.keySet().toArray(
				new String[attributes.size()]);
	}

	/* (non-Javadoc)
	 * @see org.apache.commons.logging.LogFactory#getInstance(java.lang.Class)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Log getInstance(Class clazz) throws LogConfigurationException {
		Log log = logs.get(clazz);
		if (log == null) {
			log = newLog(clazz.getName());
			logs.put(clazz.getName(), log);
		}
		return log;
	}

	/* (non-Javadoc)
	 * @see org.apache.commons.logging.LogFactory#getInstance(java.lang.String)
	 */
	@Override
	public Log getInstance(String name) throws LogConfigurationException {
		Log log = logs.get(name);
		if (log == null) {
			log = newLog(name);
			logs.put(name, log);
		}
		return log;
	}

	/**
	 * Returns a new log instance.
	 * @return
	 */
	private Log newLog(String id) {
		if (getAttribute(KEY_LOG_LEVEL) != null) {
			try {
				return new OsgiLog(id, Integer.parseInt(
						(String) getAttribute(KEY_LOG_LEVEL)));
			}
			catch (NumberFormatException nfe) {
				// fall back on default behaviour..
			}
		}
		return new OsgiLog(id);
	}
	
	/* (non-Javadoc)
	 * @see org.apache.commons.logging.LogFactory#release()
	 */
	@Override
	public void release() {
		logs.clear();
	}

	/* (non-Javadoc)
	 * @see org.apache.commons.logging.LogFactory#removeAttribute(java.lang.String)
	 */
	@Override
	public void removeAttribute(String name) {
		attributes.remove(name);
	}

	/* (non-Javadoc)
	 * @see org.apache.commons.logging.LogFactory#setAttribute(java.lang.String, java.lang.Object)
	 */
	@Override
	public void setAttribute(String name, Object value) {
		attributes.put(name, value);
	}
}
