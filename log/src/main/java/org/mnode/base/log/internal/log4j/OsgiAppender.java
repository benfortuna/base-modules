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
