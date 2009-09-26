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

import org.slf4j.Logger;

/**
 * @author fortuna
 *
 */
public class Slf4jAdapter extends AbstractLogAdapter {

    private final Logger logger;
    
    /**
     * @param logger a logger instance
     */
    public Slf4jAdapter(Logger logger) {
        this.logger = logger;
    }

    @Override
    protected boolean isDebugEnabled() {
        return logger.isDebugEnabled();
    }

    @Override
    protected boolean isInfoEnabled() {
        return logger.isInfoEnabled();
    }

    @Override
    protected boolean isWarnEnabled() {
        return logger.isWarnEnabled();
    }

    @Override
    protected boolean isErrorEnabled() {
        return logger.isErrorEnabled();
    }

    @Override
    protected void debug(String message) {
        logger.debug(message);
    }
    
    @Override
    protected void debug(String message, Throwable e) {
        logger.debug(message, e);
    }
    
    @Override
    protected void info(String message) {
        logger.info(message);
    }
    
    @Override
    protected void info(String message, Throwable e) {
        logger.info(message, e);
    }
    
    @Override
    protected void warn(String message) {
        logger.warn(message);
    }
    
    @Override
    protected void warn(String message, Throwable e) {
        logger.warn(message, e);
    }
    
    @Override
    protected void error(String message) {
        logger.error(message);
    }
    
    @Override
    protected void error(String message, Throwable e) {
        logger.error(message, e);
    }

}
