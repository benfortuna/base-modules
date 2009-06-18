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

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;


/**
 * @author fortuna
 *
 */
public class IdGeneratorTest {

    private IdGenerator generator;
    
    private final String prefix = "Test";
    
    @Before
    public void runBeforeEachTest() {
        generator = new IdGenerator(prefix);
    }
    
    @Test
    public void testGenerateId() {
        String id = generator.generateId();
        Assert.assertTrue(id.startsWith(prefix));
        
        String anotherId = generator.generateId();
        Assert.assertNotSame(id, anotherId);
    }
}
