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

import java.lang.reflect.Proxy;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


/**
 * @author fortuna
 *
 */
public class RetryInvocationHandlerTest {

    private RetryInvocationHandler handler;
    
    private RetryHandler retryHandler;
    
    private ExceptionGenerator proxy;

    private boolean exceptionGenerated = false;
    
    @Before
    public void runBeforeEachTest() {
        retryHandler = new RetryHandler() {
            public boolean retryOnException(Throwable e) {
                if (!exceptionGenerated) {
                    return true;
                }
                return false;
            }
        };
        
        handler = new RetryInvocationHandler(new ExceptionGenerator() {
            public void generateException() {
                exceptionGenerated = true;
                throw new RuntimeException("Error");
            }
        }, retryHandler);
        
        proxy = (ExceptionGenerator) Proxy.newProxyInstance(ExceptionGenerator.class.getClassLoader(),
                new Class<?>[] {ExceptionGenerator.class}, handler);
    }
    
    @Test
    public void testRetry() {
        try {
            proxy.generateException();
        }
        catch (RuntimeException e) {
            Assert.assertTrue(exceptionGenerated);
        }
    }
    
    private interface ExceptionGenerator {
        
        void generateException();
    }
}
