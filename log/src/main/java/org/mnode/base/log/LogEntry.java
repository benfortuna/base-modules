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

package org.mnode.base.log;

/**
 * Defines a structured log message.
 * 
 * @author fortuna
 *
 */
public interface LogEntry {

    public enum Level {
        Trace, Debug, Info, Warn, Error;
    }
    
    Level getLevel();
    
    /**
     * @param args optional arguments to include in the message
     * @return a formatted log message
     */
    String getMessage(Object...args);
}
