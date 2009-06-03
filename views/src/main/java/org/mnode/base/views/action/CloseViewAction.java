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
package org.mnode.base.views.action;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.util.ResourceBundle;

import javax.swing.AbstractAction;

/**
 * @author Ben
 *
 */
public class CloseViewAction extends AbstractAction {
    
    /**
     * 
     */
    private static final long serialVersionUID = -4371176730666389581L;

    private static final ResourceBundle MESSAGES = ResourceBundle.getBundle(
            "org.mnode.base.views.messages");

    private Frame frame;
    
    /**
     * Default constructor.
     */
    public CloseViewAction() {
        super(MESSAGES.getString("action.closeView"));
        setEnabled(false);
    }
    
    /**
     * {@inheritDoc}
     */
    public void actionPerformed(ActionEvent e) {
        frame.setVisible(false);
    }

    /**
     * @param frame the frame to set
     */
    public final void setFrame(Frame frame) {
        this.frame = frame;
        setEnabled(frame != null);
    }

}
