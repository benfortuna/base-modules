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
package org.mnode.base.views;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ResourceBundle;

import javax.swing.JLabel;

import org.jdesktop.swingx.JXStatusBar;
import org.jdesktop.swingx.JXStatusBar.Constraint.ResizeBehavior;

/**
 * @author Ben
 * 
 */
public class ViewStatusBar extends JXStatusBar {

    /**
     * 
     */
    private static final long serialVersionUID = -1704890150047395745L;

    private static final ResourceBundle MESSAGES = ResourceBundle.getBundle("org.mnode.base.views.messages");

    private JLabel messageLabel;

    private String defaultMessage;

    /**
     * @param view the view associated with the status bar
     */
    public ViewStatusBar(View view) {
        messageLabel = new JLabel();
        defaultMessage = MESSAGES.getString("status.ready");
        resetMessage();
        add(messageLabel, new JXStatusBar.Constraint(ResizeBehavior.FILL));
        view.addPropertyChangeListener("statusMessage", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent e) {
                setMessage((String) e.getNewValue());
            }
        });
    }

    /**
     * Updates the status message.
     * 
     * @param message the new status message
     */
    public void setMessage(String message) {
        messageLabel.setText(message);
    }

    /**
     * Updates the default status message.
     * 
     * @param message the new default status message
     */
    public void setDefaultMessage(String message) {
        defaultMessage = message;
    }

    /**
     * Resets the status message.
     */
    public void resetMessage() {
        messageLabel.setText(defaultMessage);
    }
}
