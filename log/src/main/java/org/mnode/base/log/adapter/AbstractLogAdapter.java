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

package org.mnode.base.log.adapter;

import org.mnode.base.log.LogAdapter;
import org.mnode.base.log.LogEntry;
import org.mnode.base.log.LogEntry.Level;

/**
 * @author fortuna
 *
 */
public abstract class AbstractLogAdapter implements LogAdapter {

    /**
     * {@inheritDoc}
     */
    public final boolean isLoggable(LogEntry entry) {
        boolean loggable = false;
        if (entry.getLevel() == Level.Debug) {
            loggable = isDebugEnabled();
        }
        else if (entry.getLevel() == Level.Info) {
            loggable = isInfoEnabled();
        }
        else if (entry.getLevel() == Level.Warn) {
            loggable = isWarnEnabled();
        }
        else if (entry.getLevel() == Level.Error) {
            loggable = isErrorEnabled();
        }
        return loggable;
    }

    protected abstract boolean isDebugEnabled();

    protected abstract boolean isInfoEnabled();

    protected abstract boolean isWarnEnabled();

    protected abstract boolean isErrorEnabled();
    
    /**
     * {@inheritDoc}
     */
    public final void log(LogEntry entry, Object... args) {
        if (isLoggable(entry)) {
            if (entry.getLevel() == Level.Debug) {
                debug(entry.getMessage(args));
            }
            else if (entry.getLevel() == Level.Info) {
                info(entry.getMessage(args));
            }
            else if (entry.getLevel() == Level.Warn) {
                warn(entry.getMessage(args));
            }
            else if (entry.getLevel() == Level.Error) {
                error(entry.getMessage(args));
            }
        }
    }

    protected abstract void debug(String message);

    protected abstract void info(String message);

    protected abstract void warn(String message);

    protected abstract void error(String message);
    
    /**
     * {@inheritDoc}
     */
    public final void log(LogEntry entry, Throwable exception, Object... args) {
        if (isLoggable(entry)) {
            if (entry.getLevel() == Level.Debug) {
                debug(entry.getMessage(args), exception);
            }
            else if (entry.getLevel() == Level.Info) {
                info(entry.getMessage(args), exception);
            }
            else if (entry.getLevel() == Level.Warn) {
                warn(entry.getMessage(args), exception);
            }
            else if (entry.getLevel() == Level.Error) {
                error(entry.getMessage(args), exception);
            }
        }
    }

    protected abstract void debug(String message, Throwable e);

    protected abstract void info(String message, Throwable e);

    protected abstract void warn(String message, Throwable e);

    protected abstract void error(String message, Throwable e);

}
