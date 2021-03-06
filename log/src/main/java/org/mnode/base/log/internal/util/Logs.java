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
package org.mnode.base.log.internal.util;

import java.util.ResourceBundle;

import org.mnode.base.log.internal.LogServiceAdapter;
import org.osgi.service.log.LogEntry;
import org.osgi.service.log.LogService;

/**
 * Utility methods relating to OSGi logging.
 * @author Ben Fortuna
 */
public final class Logs {

    private static final ResourceBundle MESSAGES = ResourceBundle.getBundle(
            "org.mnode.base.log.internal.messages");

    /**
     * Constructor made private to enforce static nature.
     */
    private Logs() {
    }
	
	/**
	 * @param logLevel
	 * @param message
	 * @param e
	 */
	public static void log(String id, int level, Object message) {
		LogServiceAdapter adapter = LogServiceAdapter.getInstance();
		if (adapter != null) {
			adapter.log(id, level, message);
		}
	}
	
	/**
	 * @param logLevel
	 * @param message
	 * @param e
	 */
	public static void log(String id, int level, Object message, Throwable e) {
		LogServiceAdapter adapter = LogServiceAdapter.getInstance();
		if (adapter != null) {
			adapter.log(id, level, message, e);
		}
	}
    
    /**
     * @param entry
     * @return
     */
    public static String getLogLevel(final LogEntry entry) {
        
        switch (entry.getLevel()) {
            case LogService.LOG_ERROR:
                return MESSAGES.getString("log.level.error");
            case LogService.LOG_WARNING:
                return MESSAGES.getString("log.level.warning");
            case LogService.LOG_INFO:
                return MESSAGES.getString("log.level.info");
            case LogService.LOG_DEBUG:
                return MESSAGES.getString("log.level.debug");
            default: return "";
        }
    }
}
