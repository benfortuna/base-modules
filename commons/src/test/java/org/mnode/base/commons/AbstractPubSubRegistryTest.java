package org.mnode.base.commons;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class AbstractPubSubRegistryTest {

	private AbstractPubSubRegistry<String, String> registry;

	private Map<String, String> subscribed;
	
	@Before
	public void runBeforeEachTest() {
		subscribed = new HashMap<String, String>();
		
		registry = new AbstractPubSubRegistry<String, String>() {
			@Override
			protected void subscribe(String publisher, String subscriber) {
				subscribed.put(publisher, subscriber);
			}
			@Override
			protected void unsubscribe(String publisher, String subscriber) {
				subscribed.remove(publisher);
			}
		};
	}
	
	@Test
	public void testSubscribeAfterPublisher() {
		String publisher = "pub";
		String subscriber = "sub";
		Map<String, String> props = new HashMap<String, String>();
		registry.registerPublisher(publisher, props);
		registry.registerSubscriber(subscriber, props);
		
		Assert.assertEquals(subscriber, subscribed.get(publisher));
	}
	
	@Test
	public void testSubscribeBeforePublisher() {
		String publisher = "pub";
		String subscriber = "sub";
		Map<String, String> props = new HashMap<String, String>();
		registry.registerSubscriber(subscriber, props);
		registry.registerPublisher(publisher, props);
		
		Assert.assertEquals(subscriber, subscribed.get(publisher));
	}
	
	@Test
	public void testUnsubscribeAfterPublisher() {
		String publisher = "pub";
		String subscriber = "sub";
		Map<String, String> props = new HashMap<String, String>();
		registry.registerPublisher(publisher, props);
		registry.registerSubscriber(subscriber, props);
		
		Assert.assertEquals(subscriber, subscribed.get(publisher));
		registry.unregisterPublisher(publisher, props);
		registry.unregisterSubscriber(subscriber, props);
		Assert.assertNull(subscribed.get(publisher));
	}
	
	@Test
	public void testUnsubscribeBeforePublisher() {
		String publisher = "pub";
		String subscriber = "sub";
		Map<String, String> props = new HashMap<String, String>();
		registry.registerSubscriber(subscriber, props);
		registry.registerPublisher(publisher, props);
		
		Assert.assertEquals(subscriber, subscribed.get(publisher));
		registry.unregisterSubscriber(subscriber, props);
		registry.unregisterPublisher(publisher, props);
		
		Assert.assertNull(subscribed.get(publisher));
	}
}
