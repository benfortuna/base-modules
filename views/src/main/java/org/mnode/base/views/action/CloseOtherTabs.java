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
/**
 * 
 */
package org.mnode.base.views.action;

import java.awt.event.ActionEvent;

import org.jdesktop.swingx.action.ActionManager;
import org.mnode.base.views.AbstractTabbedView;

/**
 * @param <T>
 *            the view type for this action
 * @param <K>
 *            the key for views in the tab view
 *            
 * @author fortuna
 * 
 */
public class CloseOtherTabs<T extends AbstractTabbedView<K, ?>, K> extends AbstractTabAction<T, K> {

    private static final long serialVersionUID = 3857936811611961784L;

    /**
     * @param id the action identifier
     */
    public CloseOtherTabs(String id) {
        super("Close Others", id);
        setEnabled(false);
        ActionManager.getInstance().addAction(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        getView().closeOtherTabs(getTabKey());
    }
}
