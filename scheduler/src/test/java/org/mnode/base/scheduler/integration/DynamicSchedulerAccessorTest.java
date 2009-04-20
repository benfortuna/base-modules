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
		return new String[] { "org.mnode.base, scheduler, 0.0.1-SNAPSHOT",
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
