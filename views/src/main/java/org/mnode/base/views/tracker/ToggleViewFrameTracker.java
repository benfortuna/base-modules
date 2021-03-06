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
package org.mnode.base.views.tracker;

import java.awt.Frame;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.mnode.base.desktop.tracker.FrameTracker;
import org.mnode.base.views.ToggleViewFrame;
import org.mnode.base.views.ToggleViewFrame.Mode;

/**
 * @author Ben
 * 
 */
public class ToggleViewFrameTracker extends FrameTracker implements PropertyChangeListener {

    private ToggleViewFrame frame;

    /**
     * @param frame the toggle view frame to track
     * @param id an identifier for this tracker
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
     * @Override public void componentResized(ComponentEvent e) { if (Frame.MAXIMIZED_BOTH != frame.getExtendedState())
     * { if (Mode.MINIMAL.equals(frame.getToggleMode())) { // save preferences on component mini size..
     * getPreferences().putInt(getUniqueId() + ".miniWidth", frame.getWidth()); } else { // save preferences on
     * component full size.. getPreferences().putInt(getUniqueId() + ".fullWidth", frame.getWidth()); } }
     * super.componentResized(e); }
     */

    /**
     * {@inheritDoc}
     */
    public void propertyChange(PropertyChangeEvent e) {
        if ("toggleMode".equals(e.getPropertyName())) {
            getPreferences().put(getUniqueId() + "." + e.getPropertyName(), ((Mode) e.getNewValue()).name());
        } else if ("miniWidth".equals(e.getPropertyName()) || "fullWidth".equals(e.getPropertyName())) {
            getPreferences().putInt(getUniqueId() + "." + e.getPropertyName(), (Integer) e.getNewValue());
        }

    }

    /**
     * @return the last know toggle mode of the frame
     */
    public Mode getToggleMode() {
        return Mode.valueOf(getPreferences().get(getUniqueId() + ".toggleMode", Mode.MINIMAL.name()));
    }

    /**
     * @return the last known compact mode width of the frame
     */
    public int getMiniWidth() {
        return getPreferences().getInt(getUniqueId() + ".miniWidth", frame.getSize().width);
    }

    /**
     * @return the last known full mode width of the frame
     */
    public int getFullWidth() {
        return getPreferences().getInt(getUniqueId() + ".fullWidth", frame.getSize().width);
    }
}
