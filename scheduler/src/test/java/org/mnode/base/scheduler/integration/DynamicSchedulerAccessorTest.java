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
package org.mnode.base.scheduler.integration;

import java.text.ParseException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Trigger;
import org.quartz.TriggerUtils;
import org.springframework.osgi.test.AbstractConfigurableBundleCreatorTests;

public class DynamicSchedulerAccessorTest extends
		AbstractConfigurableBundleCreatorTests {

	private static final Log LOG = LogFactory.getLog(DynamicSchedulerAccessorTest.class);
	
	public void testScheduleJob() throws ParseException, InterruptedException {
		JobDetail jobDetail = new JobDetail("TestJob", "Test", TestJob.class);
		jobDetail.setDurability(true);
		bundleContext.registerService(JobDetail.class.getName(), jobDetail, null);
		
//		CronTrigger trigger = new CronTrigger("TestTrigger", "Test");
//		trigger.setCronExpression("0 0/2 * * * ?");
		Trigger trigger = TriggerUtils.makeImmediateTrigger(5, 1000);
		trigger.setName("TestTrigger");
		trigger.setGroup("Test");
		trigger.setJobName("TestJob");
		trigger.setJobGroup("Test");
		bundleContext.registerService(Trigger.class.getName(), trigger, null);
		
		Thread.sleep(10000);
	}
	
	@Override
	protected String[] getTestBundlesNames() {
		return new String[] { "org.mnode.base, org.mnode.base.scheduler, 0.0.1-SNAPSHOT",
				"org.springframework, spring-context-support, 2.5.5",
				"org.springframework, spring-tx, 2.5.5",
				"net.sourceforge.cglib, com.springsource.net.sf.cglib, 2.1.3"};
	}
	
	public static class TestJob implements Job {
		@Override
		public void execute(JobExecutionContext context)
				throws JobExecutionException {
			LOG.info("Test JOb executed");
		}
	}
}
