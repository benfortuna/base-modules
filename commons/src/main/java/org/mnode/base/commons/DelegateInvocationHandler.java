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
import java.lang.reflect.Method;

/**
 * @author fortuna
 *
 */
public class DelegateInvocationHandler implements InvocationHandler {

    private final DelegateSelector<?> selector;
    
    public DelegateInvocationHandler(DelegateSelector<?> selector) {
        this.selector = selector;
    }
    
    /**
     * {@inheritDoc}
     */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(selector.getDelegate(), args);
    }

}
