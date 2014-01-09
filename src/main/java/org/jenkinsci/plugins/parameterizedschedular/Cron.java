package org.jenkinsci.plugins.parameterizedschedular;

import hudson.Extension;
import hudson.model.PeriodicWork;
import hudson.model.AbstractProject;
import hudson.triggers.Trigger;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import jenkins.model.Jenkins;

@Extension
public class Cron extends PeriodicWork {
	private static final Logger LOGGER = Logger.getLogger(Cron.class.getName());

	@Override
	public long getRecurrencePeriod() {
		long period = TimeUnit.MINUTES.toMillis(1);
		LOGGER.warning("period set to " + period);
		return period;
	}

	@Override
	protected void doRun() throws Exception {
		LOGGER.finer("dorun-run");
		checkTriggers(new GregorianCalendar());
	}

	private void checkTriggers(Calendar calendar) {
		Jenkins instance = Jenkins.getInstance();

		for (AbstractProject<?, ?> project : instance.getAllItems(AbstractProject.class)) {
			for (Trigger<?> trigger : project.getTriggers().values()) {
				if (trigger instanceof ParameterizedTimerTrigger) {
					LOGGER.fine("cron checking " + project.getName());
					ParameterizedTimerTrigger ptTrigger = (ParameterizedTimerTrigger) trigger;

					try {
						ptTrigger.checkCronTabsAndRun(calendar);
					} catch (Throwable e) {
						// t.run() is a plugin, and some of them throw RuntimeException and other things.
						// don't let that cancel the polling activity. report and move on.
						LOGGER.log(Level.WARNING,
								trigger.getClass().getName() + ".run() failed for " + project.getName(), e);
					}
				}
			}
		}
	}

}
