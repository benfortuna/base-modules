/*
 * $Id: ActivityEvent.java $
 *
 * Created: [03/08/2008]
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

package org.mnode.base.views.activity;

import java.util.EventObject;

/**
 * @author Ben
 *
 */
public class ActivityEvent extends EventObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3393401314231042562L;

	/**
	 * @param arg0
	 */
	public ActivityEvent(Object source) {
		super(source);
	}

}
