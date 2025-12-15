/*
 * Copyright (C) 2005 - 2030 YGSoft.Inc All Rights Reserved.
 * YGSoft.Inc PROPRIETARY/CONFIDENTIAL.Use is subject to license terms.
 */
package com.ygsoft.jt.mapp.sample.impl.component.scheduler;

import com.ygsoft.jt.teng.cp.scheduler.core.engine.service.IEcpJob;
import com.ygsoft.jt.teng.cp.scheduler.core.engine.service.model.JobResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Jt调度任务测试.<br>
 *
 * @author mayanchao <br>
 * @version 1.0.0 2022-5-30<br>
 * @see
 * @since JDK 1.8.0
 */
public class MyTaskIEcpJob implements IEcpJob {
	/**
	 * 日志.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(MyTaskIEcpJob.class);

	/**
	 * {@inheritDoc}
	 *
	 * @see com.ygsoft.jt.teng.cp.scheduler.quartz.engine.service.IEcpJob#execute(com.ygsoft.jt.teng.cp.scheduler.quartz.engine.service.model.JobExecutionContext)
	 */
	@Override
	public JobResult execute(
			final com.ygsoft.jt.teng.cp.scheduler.core.engine.service.model.JobExecutionContext context)
			throws Exception {

		// 在这里执行任务的工作内容
		final StringBuilder rVal = new StringBuilder("Jt调度任务执行成功");
		if (context.getExecParam().containsKey("targetParameter")) {
			rVal.append(",调用参数:").append(context.getExecParam().get("targetParameter").toString());
		}

		if (LOG.isInfoEnabled()) {
			LOG.info(rVal.toString());
		}
		final JobResult jobResult = new JobResult();
		jobResult.setFeedbackContent(rVal.toString());
		return jobResult;
	}
}
