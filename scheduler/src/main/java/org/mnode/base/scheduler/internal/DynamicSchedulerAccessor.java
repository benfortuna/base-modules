package org.mnode.base.scheduler.internal;

import java.util.Map;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;

public class DynamicSchedulerAccessor {

	private Scheduler scheduler;

	/**
	 * @param scheduler the scheduler to set
	 */
	public void setScheduler(Scheduler scheduler) {
		this.scheduler = scheduler;
	}
	
	/**
	 * @param jobDetail
	 * @param props
	 * @throws SchedulerException
	 */
	public void addJob(JobDetail jobDetail, Map<?, ?> props) throws SchedulerException {
		scheduler.addJob(jobDetail, Boolean.TRUE.equals(props.get("replace")));
	}
	
	/**
	 * @param jobDetail
	 * @param props
	 * @throws SchedulerException
	 */
	public void removeJob(JobDetail jobDetail, Map<?, ?> props) throws SchedulerException {
		if (!scheduler.isShutdown()) {
			scheduler.deleteJob(jobDetail.getName(), jobDetail.getGroup());
		}
	}
	
	/**
	 * @param trigger
	 * @param props
	 * @throws SchedulerException
	 */
	public void scheduleJob(Trigger trigger, Map<?, ?> props) throws SchedulerException {
		scheduler.scheduleJob(trigger);
	}
	
	/**
	 * @param trigger
	 * @param props
	 * @throws SchedulerException
	 */
	public void unscheduleJob(Trigger trigger, Map<?, ?> props) throws SchedulerException {
		if (!scheduler.isShutdown()) {
			scheduler.unscheduleJob(trigger.getName(), trigger.getGroup());
		}
	}
}
