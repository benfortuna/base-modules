/*
 * $Id: FrameTracker.java Exp $
 *
 * Created: [24/05/2007]
 *
 * Copyright (c) 2007, Ben Fortuna
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */
package org.mnode.base.views.tracker;

import java.awt.Frame;
import java.awt.event.ComponentEvent;

/**
 * @author Ben
 *
 */
public class FrameTracker extends ComponentTracker {

    private Frame frame;
    
    /**
     * @param component
     */
    public FrameTracker(Frame frame) {
        super(frame);
    }

    /**
     * @param component
     * @param id
     */
    public FrameTracker(Frame frame, String id) {
        super(frame, id);
        this.frame = frame;
//        if (isMaximised()) {
//            frame.setExtendedState(Frame.MAXIMIZED_BOTH);
//        }
    }

    @Override
    public void componentMoved(ComponentEvent e) {
    	try {
    		// Micro-sleep may be needed to ensure frame extended state
    		// is set correctly (???? - seems to work)
        	Thread.sleep(10);
    	}
    	catch (InterruptedException ie) {
    	}
        if (Frame.MAXIMIZED_BOTH == frame.getExtendedState()) {
            // if frame is maximised don't update the saved location..
            return;
        }
        super.componentMoved(e);
    }
    
    @Override
    public void componentResized(ComponentEvent e) {
        if (Frame.MAXIMIZED_BOTH == frame.getExtendedState()) {
            getPreferences().putBoolean(getUniqueId() + ".maximised", true);
            // if frame is maximised don't update the saved dimensions..
            return;
        }
        else {
            getPreferences().putBoolean(getUniqueId() + ".maximised", false);
            super.componentResized(e);
        }
    }
    
    /**
     * Returns the saved maximised state of the component.
     * @return
     */
    public boolean isMaximised() {
        return getPreferences().getBoolean(getUniqueId() + ".maximised",
                Frame.MAXIMIZED_BOTH == frame.getExtendedState());
    }
}