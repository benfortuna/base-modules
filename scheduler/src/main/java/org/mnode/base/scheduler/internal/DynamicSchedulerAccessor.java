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
