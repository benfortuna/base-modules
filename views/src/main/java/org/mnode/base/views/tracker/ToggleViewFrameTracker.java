/*
 * $Id: ToggleViewFrameTracker.java $
 *
 * Created: [07/06/2008]
 *
 * Copyright (c) 2008, Ben Fortuna
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
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.mnode.base.views.ToggleViewFrame;
import org.mnode.base.views.ToggleViewFrame.Mode;

/**
 * @author Ben
 *
 */
public class ToggleViewFrameTracker extends FrameTracker implements PropertyChangeListener {

    private ToggleViewFrame frame;

	/**
	 * @param frame
	 * @param id
	 */
	public ToggleViewFrameTracker(ToggleViewFrame frame, String id) {
		super(frame, id);
        this.frame = frame;

        frame.setToggleMode(getToggleMode());
        frame.setMiniWidth(getMiniWidth());
        frame.setFullWidth(getFullWidth());
        if (isMaximised()) {
            frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        }
        frame.addPropertyChangeListener(this);
	}

	/*
	@Override
	public void componentResized(ComponentEvent e) {
		if (Frame.MAXIMIZED_BOTH != frame.getExtendedState()) {
			if (Mode.MINIMAL.equals(frame.getToggleMode())) {
		        // save preferences on component mini size..
		        getPreferences().putInt(getUniqueId() +  ".miniWidth", frame.getWidth());
			}
			else {
		        // save preferences on component full size..
		        getPreferences().putInt(getUniqueId() +  ".fullWidth", frame.getWidth());
			}
		}
		super.componentResized(e);
	}
	*/
	
	/* (non-Javadoc)
	 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
	 */
	public void propertyChange(PropertyChangeEvent e) {
        if ("toggleMode".equals(e.getPropertyName())) {
        	getPreferences().put(getUniqueId() + "." + e.getPropertyName(),
        			((Mode) e.getNewValue()).name());
        }
        else if ("miniWidth".equals(e.getPropertyName()) || "fullWidth".equals(e.getPropertyName())) {
        	getPreferences().putInt(getUniqueId() + "." + e.getPropertyName(),
        			(Integer) e.getNewValue());
        }
		
	}
	
	/**
	 * @return
	 */
	public Mode getToggleMode() {
		return Mode.valueOf(getPreferences().get(getUniqueId() + ".toggleMode", Mode.MINIMAL.name()));
	}
	
	/**
	 * @return
	 */
	public int getMiniWidth() {
		return getPreferences().getInt(getUniqueId() + ".miniWidth", frame.getSize().width);
	}
	
	/**
	 * @return
	 */
	public int getFullWidth() {
		return getPreferences().getInt(getUniqueId() + ".fullWidth", frame.getSize().width);
	}
}
