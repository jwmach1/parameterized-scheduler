package org.jenkinsci.plugins.parameterizedschedular;

import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.HOUR_OF_DAY;
import static java.util.Calendar.MINUTE;
import static java.util.Calendar.MONTH;
import hudson.scheduler.CronTab;
import hudson.scheduler.Hash;

import java.util.Calendar;
import java.util.Map;

import antlr.ANTLRException;

import com.google.common.collect.Maps;

/**
 * this is a copy of {@link CronTab} with added parameters map support
 * 
 * @author jameswilson
 */
public class ParameterizedCronTab {

	private final Map<String, String> parameterValues;
	private final long[] bits;
	private final Integer dayOfWeek;
	private final CronTab cronTab;

	/**
	 * look at this fragile happy-crappy! Is this better than duplicating all of the Crontab source-tree? you decide.
	 * 
	 * @param cronTab the crontab to use as a template
	 * @param parameters the parameters in name=value key pairings
	 */
	public ParameterizedCronTab(CronTab cronTab, Map<String, String> parameters) {
		this.cronTab = cronTab;
		FieldAccessor fieldAccessor = new FieldAccessor();
		bits = fieldAccessor.access(cronTab, "bits");
		dayOfWeek = fieldAccessor.access(cronTab, "dayOfWeek");

		parameterValues = parameters;
	}

	/**
	 * @param hash
	 *      Used to spread out token like "@daily". Null to preserve the legacy behaviour
	 *      of not spreading it out at all.
	 */
	public static ParameterizedCronTab create(String line, int lineNumber, Hash hash) throws ANTLRException {
		String[] lineParts = line.split("%");
		CronTab cronTab = new CronTab(lineParts[0].trim(), lineNumber, hash);
		Map<String, String> parameters = Maps.newHashMap();
		if (lineParts.length == 2) {
			parameters = new ParameterParser().parse(lineParts[1]);
		}
		return new ParameterizedCronTab(cronTab, parameters);
	}

	public Map<String, String> getParameterValues() {
		return parameterValues;
	}

	/**
	 * Returns true if n-th bit is on.
	 */
	private boolean checkBits(long bitMask, int n) {
		return (bitMask | (1L << n)) == bitMask;
	}

	public boolean check(Calendar calendar) {
		if (!checkBits(bits[0], calendar.get(MINUTE)))
			return false;
		if (!checkBits(bits[1], calendar.get(HOUR_OF_DAY)))
			return false;
		if (!checkBits(bits[2], calendar.get(DAY_OF_MONTH)))
			return false;
		if (!checkBits(bits[3], calendar.get(MONTH) + 1))
			return false;
		if (!checkBits(dayOfWeek, calendar.get(Calendar.DAY_OF_WEEK) - 1))
			return false;

		return true;
	}

	public String checkSanity() {
		return cronTab.checkSanity();
	}
}
