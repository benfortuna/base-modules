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

import org.osgi.service.log.LogService;

/**
 * @author fortuna
 *
 */
public class OsgiAdapter extends AbstractLogAdapter {

    private final LogService logger;
    
    public OsgiAdapter(LogService logger) {
        this.logger = logger;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void debug(String message) {
        logger.log(LogService.LOG_DEBUG, message);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void debug(String message, Throwable e) {
        logger.log(LogService.LOG_DEBUG, message, e);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void error(String message) {
        logger.log(LogService.LOG_ERROR, message);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void error(String message, Throwable e) {
        logger.log(LogService.LOG_ERROR, message, e);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void info(String message) {
        logger.log(LogService.LOG_INFO, message);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void info(String message, Throwable e) {
        logger.log(LogService.LOG_INFO, message, e);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean isDebugEnabled() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean isErrorEnabled() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean isInfoEnabled() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean isWarnEnabled() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void warn(String message) {
        logger.log(LogService.LOG_WARNING, message);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void warn(String message, Throwable e) {
        logger.log(LogService.LOG_WARNING, message, e);
    }

}
