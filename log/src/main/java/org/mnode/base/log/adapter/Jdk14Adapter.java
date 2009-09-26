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

import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * @author fortuna
 *
 */
public class Jdk14Adapter extends AbstractLogAdapter {

    private final Logger logger;
    
    public Jdk14Adapter(Logger logger) {
        this.logger = logger;
    }

    @Override
    protected boolean isDebugEnabled() {
        return logger.isLoggable(Level.FINE);
    }

    @Override
    protected boolean isInfoEnabled() {
        return logger.isLoggable(Level.INFO);
    }

    @Override
    protected boolean isWarnEnabled() {
        return logger.isLoggable(Level.WARNING);
    }

    @Override
    protected boolean isErrorEnabled() {
        return logger.isLoggable(Level.SEVERE);
    }

    @Override
    protected void debug(String message) {
        logger.log(Level.FINE, message);
    }
    
    @Override
    protected void debug(String message, Throwable e) {
        logger.log(Level.FINE, message, e);
    }
    
    @Override
    protected void info(String message) {
        logger.log(Level.INFO, message);
    }
    
    @Override
    protected void info(String message, Throwable e) {
        logger.log(Level.INFO, message, e);
    }
    
    @Override
    protected void warn(String message) {
        logger.log(Level.WARNING, message);
    }
    
    @Override
    protected void warn(String message, Throwable e) {
        logger.log(Level.WARNING, message, e);
    }
    
    @Override
    protected void error(String message) {
        logger.log(Level.SEVERE, message);
    }
    
    @Override
    protected void error(String message, Throwable e) {
        logger.log(Level.SEVERE, message, e);
    }
}
