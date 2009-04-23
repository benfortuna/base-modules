/*
 * $Id: CloseViewAction.java Exp $
 *
 * Created: [23/05/2007]
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
    
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
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
