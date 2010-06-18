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

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.mnode.base.log.LogAdapter;
import org.mnode.base.log.LogEntry;

/**
 * @author Ben
 *
 */
public class AsyncLogAdapter implements LogAdapter {

    private static final BlockingQueue<Log> QUEUE = new LinkedBlockingQueue<Log>();
    
    private static final Thread QUEUE_PROCESSOR = new Thread(new QueueProcessor(), "AsyncLogAdapter");
    static {
        QUEUE_PROCESSOR.start();
    }
    
    private final LogAdapter delegate;
    
    public AsyncLogAdapter(LogAdapter delegate) {
        this.delegate = delegate;
    }
    
    /**
     * {@inheritDoc}
     */
    public boolean isLoggable(LogEntry entry) {
        return delegate.isLoggable(entry);
    }

    /**
     * {@inheritDoc}
     */
    public void log(LogEntry entry, Object... args) {
        QUEUE.add(new Log(delegate, entry, null, args));
    }

    /**
     * {@inheritDoc}
     */
    public void log(LogEntry entry, Throwable exception, Object... args) {
        QUEUE.add(new Log(delegate, entry, exception, args));
    }

    private static class Log {
        
        private final LogAdapter logAdapter;
        
        private final LogEntry logEntry;
        
        private final Throwable exception;
        
        private final Object[] args;
        
        public Log(LogAdapter logAdapter, LogEntry logEntry, Throwable exception, Object...args) {
            this.logAdapter = logAdapter;
            this.logEntry = logEntry;
            this.exception = exception;
            this.args = args;
        }
    }
    
    private static class QueueProcessor implements Runnable {
        
        private transient boolean shuttingDown;
        
        /**
         * {@inheritDoc}
         */
        public void run() {
            Runtime.getRuntime().addShutdownHook(new Thread() {
                public void run() {
                    shuttingDown = true;
                    try {
                        QUEUE_PROCESSOR.join(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                };
            });
            
            while (!shuttingDown || !QUEUE.isEmpty()) {
                try {
                    Log log = QUEUE.take();
                    if (log.exception != null) {
                        log.logAdapter.log(log.logEntry, log.exception, log.args);
                    }
                    else {
                        log.logAdapter.log(log.logEntry, log.args);
                    }
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
}
