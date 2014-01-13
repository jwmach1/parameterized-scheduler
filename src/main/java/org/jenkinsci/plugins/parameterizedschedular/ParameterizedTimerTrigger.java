package org.jenkinsci.plugins.parameterizedschedular;

import hudson.model.ParameterValue;
import hudson.model.AbstractProject;
import hudson.model.ParameterDefinition;
import hudson.model.ParametersAction;
import hudson.model.ParametersDefinitionProperty;
import hudson.scheduler.Hash;
import hudson.triggers.Trigger;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.kohsuke.stapler.DataBoundConstructor;

import antlr.ANTLRException;

/**
 * {@link Trigger} that runs a job periodically with support for parameters.
 * 
 * @author jameswilson
 *
 */
@SuppressWarnings("rawtypes")
public class ParameterizedTimerTrigger extends Trigger<AbstractProject> {
	private static final Logger LOGGER = Logger.getLogger(ParameterizedTimerTrigger.class.getName());
	private transient ParameterizedCronTabList cronTabList;
	private final String parameterizedSpecification;

	@DataBoundConstructor
	public ParameterizedTimerTrigger(String parameterizedSpecification) throws ANTLRException {
		this.parameterizedSpecification = parameterizedSpecification;
		this.cronTabList = ParameterizedCronTabList.create(parameterizedSpecification);
	}

	@Override
	public void run() {
		LOGGER.fine("tried to run from base Trigger, nothing will happen");
	}

	/**
	 * this method started out as hudson.model.AbstractProject.getDefaultParametersValues()
	 * @param parameterValues 
	 * @return the ParameterValues as set from the crontab row or their defaults
	 */
	@SuppressWarnings("unchecked")
	private List<ParameterValue> configurePropertyValues(Map<String, String> parameterValues) {
		assert job != null : "job must not be null if this was 'started'";
		ParametersDefinitionProperty paramDefProp = (ParametersDefinitionProperty) job
				.getProperty(ParametersDefinitionProperty.class);
		ArrayList<ParameterValue> defValues = new ArrayList<ParameterValue>();

		/* Scan for all parameter with an associated default values */
		for (ParameterDefinition paramDefinition : paramDefProp.getParameterDefinitions()) {
			ParameterValue defaultValue = paramDefinition.getDefaultParameterValue();

			if (parameterValues.containsKey(paramDefinition.getName())) {
				ParameterizedStaplerRequest request = new ParameterizedStaplerRequest(
						parameterValues.get(paramDefinition.getName()));
				defValues.add(paramDefinition.createValue(request));
			} else if (defaultValue != null)
				defValues.add(defaultValue);
		}

		return defValues;
	}

	public void checkCronTabsAndRun(Calendar calendar) {
		LOGGER.fine("checking and maybe running at " + calendar);
		ParameterizedCronTab cronTab = cronTabList.check(calendar);
		if (cronTab != null) {
			Map<String, String> parameterValues = cronTab.getParameterValues();
			ParametersAction parametersAction = new ParametersAction(configurePropertyValues(parameterValues));
			assert job != null : "job must not be null, if this was 'started'";
			job.scheduleBuild2(0, new ParameterizedTimerTriggerCause(parameterValues), parametersAction);
		}
	}

	@Override
	public void start(AbstractProject project, boolean newInstance) {
		this.job = project;

		try {// reparse the tabs with the job as the hash
			cronTabList = ParameterizedCronTabList.create(parameterizedSpecification, Hash.from(project.getFullName()));
		} catch (ANTLRException e) {
			// this shouldn't fail because we've already parsed stuff in the constructor,
			// so if it fails, use whatever 'tabs' that we already have.
			LOGGER.log(Level.FINE, "Failed to parse crontab spec: " + spec, e);
		}
	}

	/**
	 * for the config.jelly to populate
	 * 
	 * @return the raw specification
	 */
	public String getParameterizedSpecification() {
		return parameterizedSpecification;
	}

}
