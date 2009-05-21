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
