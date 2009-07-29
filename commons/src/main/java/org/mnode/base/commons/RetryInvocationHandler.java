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

package org.mnode.base.commons;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author fortuna
 *
 */
public class RetryInvocationHandler implements InvocationHandler {

    private final Object target;
    
    private final RetryHandler retryHandler;
    
    /**
     * @param target the invocation target
     * @param retryHandler controls retry on invocation exceptions
     */
    public RetryInvocationHandler(Object target, RetryHandler retryHandler) {
        this.target = target;
        this.retryHandler = retryHandler;
    }
    
    /**
     * {@inheritDoc}
     */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        boolean retry = true;
        while (retry) {
            try {
                result = method.invoke(target, args);
                break;
            }
            catch (InvocationTargetException e) {
                retry = retryHandler.retryOnException(e.getTargetException());
                if (!retry) {
                    throw e.getTargetException();
                }
            }
        }
        return result;
    }

}
