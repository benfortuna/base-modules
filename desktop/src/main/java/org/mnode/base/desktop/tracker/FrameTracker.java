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
package org.mnode.base.desktop.tracker;

import java.awt.Frame;
import java.awt.event.ComponentEvent;

/**
 * @author Ben
 *
 */
public class FrameTracker extends ComponentTracker {

    private Frame frame;
    
    /**
     * @param frame the frame to track
     */
    public FrameTracker(Frame frame) {
        super(frame);
    }

    /**
     * @param frame the frame to track
     * @param id an identifier for this tracker
     */
    public FrameTracker(Frame frame, String id) {
        super(frame, id);
        this.frame = frame;
//        if (isMaximised()) {
//            frame.setExtendedState(Frame.MAXIMIZED_BOTH);
//        }
    }

    /**
     * {@inheritDoc}
     */
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
    
    /**
     * {@inheritDoc}
     */
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
     * @return true if the last state of the frame was maximised
     */
    public boolean isMaximised() {
        return getPreferences().getBoolean(getUniqueId() + ".maximised",
                Frame.MAXIMIZED_BOTH == frame.getExtendedState());
    }
}
