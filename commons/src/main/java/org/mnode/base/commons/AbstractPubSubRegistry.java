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
package org.mnode.base.commons;

import java.util.List;
import java.util.Map;

public abstract class AbstractPubSubRegistry<T, S> {

	private List<T> publishers;

	private List<S> subscribers;
	
	public final void registerPublisher(T publisher, Map<?, ?> properties) {
		publishers.add(publisher);
		for (S subscriber : subscribers) {
			subscribe(publisher, subscriber);
		}
	}
	
	public final void unregisterPublisher(T publisher, Map<?, ?> properties) {
		publishers.remove(publisher);
		for (S subscriber : subscribers) {
			unsubscribe(publisher, subscriber);
		}
	}
	
	public final void registerSubscriber(S subscriber, Map<?, ?> properties) {
		subscribers.add(subscriber);
		for (T publisher : publishers) {
			subscribe(publisher, subscriber);
		}
	}
	
	public final void unregisterSubscriber(S subscriber, Map<?, ?> properties) {
		subscribers.remove(subscriber);
		for (T publisher : publishers) {
			unsubscribe(publisher, subscriber);
		}
	}
	
	protected abstract void subscribe(T publisher, S subscriber);
	
	protected abstract void unsubscribe(T publisher, S subscriber);
}
