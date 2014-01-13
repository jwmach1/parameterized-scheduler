package org.jenkinsci.plugins.parameterizedschedular;

import static hudson.Util.fixNull;
import hudson.Extension;
import hudson.model.Item;
import hudson.model.AbstractProject;
import hudson.model.ParametersDefinitionProperty;
import hudson.triggers.TriggerDescriptor;
import hudson.util.FormValidation;

import org.kohsuke.stapler.AncestorInPath;
import org.kohsuke.stapler.QueryParameter;

import antlr.ANTLRException;

@Extension
public class DescriptorImpl extends TriggerDescriptor {

	/**
	 * I don't like inner classes. Using the declaritive support here by calling super constructor with class.
	 */
	public DescriptorImpl() {
		super(ParameterizedTimerTrigger.class);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean isApplicable(Item item) {
		boolean result = false;
		if (item instanceof AbstractProject) {
			result = ((AbstractProject) item).isParameterized();
		}
		return result;
	}

	@Override
	public String getDisplayName() {
		return Messages.ParameterizedTimerTrigger_DisplayName();
	}

	/**
	 * Performs syntax check.
	 */
	public FormValidation doCheckParameterizedSpecification(@QueryParameter String value,
			@AncestorInPath AbstractProject<?, ?> project) {
		try {

			String msg = ParameterizedCronTabList.create(fixNull(value)).checkSanity();
			if (msg != null) {
				return FormValidation.warning(msg);
			}

			ParametersDefinitionProperty paramDefProp = project.getProperty(ParametersDefinitionProperty.class);
			msg = new ParameterParser().checkSanity(value, paramDefProp);
			if (msg != null) {
				return FormValidation.warning(msg);
			}

			return FormValidation.ok();
		} catch (ANTLRException e) {
			if (value.trim().indexOf('\n') == -1 && value.contains("**"))
				return FormValidation.error(Messages.ParameterizedTimerTrigger_MissingWhitespace());
			return FormValidation.error(e.getMessage());
		} catch (IllegalArgumentException e) {
			return FormValidation.error(e.getMessage());
		}
	}
}