/*
 * $Id: OsgiAppender.java $
 *
 * Created: [11/07/2007]
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

package org.mnode.base.log.internal.log4j;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Level;
import org.apache.log4j.spi.LoggingEvent;
import org.mnode.base.log.internal.util.Logs;
import org.osgi.service.log.LogService;

/**
 * @author Ben
 *
 */
public class OsgiAppender extends AppenderSkeleton {

	/**
	 * Default constructor.
	 */
	public OsgiAppender() {
	}

	/* (non-Javadoc)
	 * @see org.apache.log4j.AppenderSkeleton#append(org.apache.log4j.spi.LoggingEvent)
	 */
	@Override
	protected void append(LoggingEvent e) {
		if (e.getThrowableInformation() != null) {
			Throwable ex = e.getThrowableInformation().getThrowable();
			Logs.log(e.getLoggerName(), getLevel(e.getLevel()), e.getMessage(), ex);
		}
		else {
			Logs.log(e.getLoggerName(), getLevel(e.getLevel()), e.getMessage());
		}
	}

	/**
	 * @param level
	 */
	private int getLevel(Level level) {
		if (Level.DEBUG.equals(level)) {
			return LogService.LOG_DEBUG;
		}
		else if (Level.INFO.equals(level)) {
			return LogService.LOG_INFO;
		}
		else if (Level.WARN.equals(level)) {
			return LogService.LOG_WARNING;
		}
		else if (Level.ERROR.equals(level)) {
			return LogService.LOG_ERROR;
		}
		// default level..
		return LogService.LOG_ERROR;
	}
	
	/* (non-Javadoc)
	 * @see org.apache.log4j.AppenderSkeleton#close()
	 */
	public void close() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.apache.log4j.AppenderSkeleton#requiresLayout()
	 */
	public boolean requiresLayout() {
		// TODO Auto-generated method stub
		return false;
	}
}
