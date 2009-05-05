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
package org.mnode.base.log.internal.jcl;

import org.apache.commons.logging.Log;
import org.mnode.base.log.internal.util.Logs;
import org.osgi.service.log.LogService;

/**
 * A commons-logging Log implementation that logs to an available OSGi LogService.
 * @author Ben Fortuna
 */
public class OsgiLog implements Log {
	
	private String id;
	
	private int logLevel;
	
	/**
	 * Create a new log instance with the default minimum log level.
	 * @param logLevel
	 */
	public OsgiLog(String id) {
		this(id, LogService.LOG_INFO);
	}
	
	/**
	 * Create a new log instance with the specified minimum log level.
	 * @param logLevel
	 */
	public OsgiLog(String logId, int logLevel) {
		this.id = logId;
		this.logLevel = logLevel;
	}
	
	/* (non-Javadoc)
	 * @see org.apache.commons.logging.Log#debug(java.lang.Object)
	 */
	public void debug(Object message) {
		Logs.log(id, LogService.LOG_DEBUG, message);
	}

	/* (non-Javadoc)
	 * @see org.apache.commons.logging.Log#debug(java.lang.Object, java.lang.Throwable)
	 */
	public void debug(Object message, Throwable e) {
		Logs.log(id, LogService.LOG_DEBUG, message, e);
	}

	/* (non-Javadoc)
	 * @see org.apache.commons.logging.Log#error(java.lang.Object)
	 */
	public void error(Object message) {
		Logs.log(id, LogService.LOG_ERROR, message);
	}

	/* (non-Javadoc)
	 * @see org.apache.commons.logging.Log#error(java.lang.Object, java.lang.Throwable)
	 */
	public void error(Object message, Throwable e) {
		Logs.log(id, LogService.LOG_ERROR, message, e);
	}

	/* (non-Javadoc)
	 * @see org.apache.commons.logging.Log#fatal(java.lang.Object)
	 */
	public void fatal(Object message) {
		Logs.log(id, LogService.LOG_ERROR, message);
	}

	/* (non-Javadoc)
	 * @see org.apache.commons.logging.Log#fatal(java.lang.Object, java.lang.Throwable)
	 */
	public void fatal(Object message, Throwable e) {
		Logs.log(id, LogService.LOG_ERROR, message, e);
	}

	/* (non-Javadoc)
	 * @see org.apache.commons.logging.Log#info(java.lang.Object)
	 */
	public void info(Object message) {
		Logs.log(id, LogService.LOG_INFO, message);
	}

	/* (non-Javadoc)
	 * @see org.apache.commons.logging.Log#info(java.lang.Object, java.lang.Throwable)
	 */
	public void info(Object message, Throwable e) {
		Logs.log(id, LogService.LOG_INFO, message, e);
	}

	/* (non-Javadoc)
	 * @see org.apache.commons.logging.Log#isDebugEnabled()
	 */
	public boolean isDebugEnabled() {
		return LogService.LOG_DEBUG >= logLevel;
	}

	/* (non-Javadoc)
	 * @see org.apache.commons.logging.Log#isErrorEnabled()
	 */
	public boolean isErrorEnabled() {
		return LogService.LOG_ERROR >= logLevel;
	}

	/* (non-Javadoc)
	 * @see org.apache.commons.logging.Log#isFatalEnabled()
	 */
	public boolean isFatalEnabled() {
		return LogService.LOG_ERROR >= logLevel;
	}

	/* (non-Javadoc)
	 * @see org.apache.commons.logging.Log#isInfoEnabled()
	 */
	public boolean isInfoEnabled() {
		return LogService.LOG_INFO >= logLevel;
	}

	/* (non-Javadoc)
	 * @see org.apache.commons.logging.Log#isTraceEnabled()
	 */
	public boolean isTraceEnabled() {
		return LogService.LOG_DEBUG >= logLevel;
	}

	/* (non-Javadoc)
	 * @see org.apache.commons.logging.Log#isWarnEnabled()
	 */
	public boolean isWarnEnabled() {
		return LogService.LOG_WARNING >= logLevel;
	}

	/* (non-Javadoc)
	 * @see org.apache.commons.logging.Log#trace(java.lang.Object)
	 */
	public void trace(Object message) {
		Logs.log(id, LogService.LOG_DEBUG, message);
	}

	/* (non-Javadoc)
	 * @see org.apache.commons.logging.Log#trace(java.lang.Object, java.lang.Throwable)
	 */
	public void trace(Object message, Throwable e) {
		Logs.log(id, LogService.LOG_DEBUG, message, e);
	}

	/* (non-Javadoc)
	 * @see org.apache.commons.logging.Log#warn(java.lang.Object)
	 */
	public void warn(Object message) {
		Logs.log(id, LogService.LOG_WARNING, message);
	}

	/* (non-Javadoc)
	 * @see org.apache.commons.logging.Log#warn(java.lang.Object, java.lang.Throwable)
	 */
	public void warn(Object message, Throwable e) {
		Logs.log(id, LogService.LOG_WARNING, message, e);
	}
}
