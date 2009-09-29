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

package org.mnode.base.commons.osgi;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mnode.base.commons.osgi.ServiceProxy;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceReference;


/**
 * @author fortuna
 *
 */
public class ServiceProxyTest {

    private CharSequence proxy;
    
    private BundleContext bundleContext;
    
    private Bundle bundle;
    
    private ServiceReference serviceReference;
    
    private CharSequence service;
    
    private Mockery mockContext;
    
    @Before
    public void runBeforeEachTest() {
        mockContext = new Mockery();
        
        bundleContext = mockContext.mock(BundleContext.class);
        
        bundle = mockContext.mock(Bundle.class);
        
        serviceReference = mockContext.mock(ServiceReference.class);
        
        service = "TestService";
    }
    
    @After
    public void assertExpections() {
        mockContext.assertIsSatisfied();
    }

    @Test
    public void testProxyInvoke() throws InvalidSyntaxException {

        mockContext.checking(new Expectations() {
            {
                one(bundleContext).createFilter(with(any(String.class)));
                one(bundleContext).addServiceListener(with(any(ServiceListener.class)),
                        with(any(String.class)));
                one(bundleContext).getServiceReferences(with(any(String.class)), with(any(String.class)));
                    will(returnValue(new ServiceReference[] { serviceReference }));
                allowing(bundleContext).getService(serviceReference);
                    will(returnValue(service));
                allowing(serviceReference).getBundle();
                    will(returnValue(bundle));
            }
        });

        proxy = ServiceProxy.newProxyInstance(getClass().getClassLoader(), CharSequence.class, bundleContext);
        Assert.assertEquals(service.length(), proxy.length());
    }
}
