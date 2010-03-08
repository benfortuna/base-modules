/**
 * This file is part of Base Modules.
 *
 * Copyright (c) 2010, Ben Fortuna [fortuna@micronode.com]
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

import org.apache.commons.lang.time.StopWatch;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.Test;
import org.mnode.base.log.FormattedLogEntry;
import org.mnode.base.log.LogEntry;
import org.mnode.base.log.LogEntry.Level;

/**
 * @author Ben
 *
 */
public class AsyncLogAdapterTest {

    private static final Log LOG = LogFactory.getLog(AsyncLogAdapterTest.class);
    
    private AsyncLogAdapter logAdapter;
    
    private LogEntry logEntry;
    
    @Before
    public void runBeforeEachTest() {
        logAdapter = new AsyncLogAdapter(new JclAdapter(LOG));
        logEntry = new FormattedLogEntry(Level.Info, "Test message: %s");
    }
    
    @Test
    public void testLog() {
        logAdapter.log(logEntry, "123");
    }
    
    @Test
    public void testMetrics() {
        StopWatch sw = new StopWatch();
        sw.start();
        for (int i = 0; i < 10000; i++) {
            LOG.info("Test message: " + i);
        }
        sw.stop();
        LOG.info(String.format("JCL Logging: %sms", sw.getTime()));
        sw.reset();
        sw.start();
        for (int i = 0; i < 10000; i++) {
            logAdapter.log(logEntry, i);
        }
        sw.stop();
        LOG.info(String.format("Async Logging: %sms", sw.getTime()));
    }
}
