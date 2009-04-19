/*
 * $Id: CacheDescriptor.java $
 *
 * Created: [13/08/2007]
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

package org.mnode.base.cache;


/**
 * @author Ben
 *
 */
public class CacheDescriptor {

    public static final String KEY_CACHE_ID = "cacheId";

    public static final String KEY_MAX_ELEMENTS = "maxElements";

    public static final String KEY_PERSISTENT = "persistent";

	private String id;
	
	private int maxElements;
	
	private boolean persistent;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the maxElements
	 */
	public int getMaxElements() {
		return maxElements;
	}

	/**
	 * @param maxElements the maxElements to set
	 */
	public void setMaxElements(int maxElements) {
		this.maxElements = maxElements;
	}

	/**
	 * @return the persistent
	 */
	public boolean isPersistent() {
		return persistent;
	}

	/**
	 * @param persistent the persistent to set
	 */
	public void setPersistent(boolean persistent) {
		this.persistent = persistent;
	}
}
