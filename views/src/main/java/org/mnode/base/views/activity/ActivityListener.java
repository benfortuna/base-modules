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
package org.mnode.base.views.activity;

import java.util.EventListener;

/**
 * @author Ben
 * 
 */
public interface ActivityListener extends EventListener {

    /**
     * @param e the event fired when mouse is idle
     */
    void mouseIdle(ActivityEvent e);

    /**
     * @param e the event fired when mouse is active
     */
    void mouseActive(ActivityEvent e);
}
