package org.jenkinsci.plugins.parameterizedschedular;

import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.HOUR_OF_DAY;
import static java.util.Calendar.MINUTE;
import static java.util.Calendar.MONTH;
import hudson.scheduler.CronTab;
import hudson.scheduler.Hash;

import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.Maps;

import antlr.ANTLRException;

/**
 * this is a copy of {@link CronTab} with added parameters map support
 * 
 * @author jameswilson
 */
public class ParameterizedCronTab {

	private final Map<String, String> parameterValues;
//	private final CronTab cronTab;
	private long[] bits;
	private Integer dayOfWeek;

	public ParameterizedCronTab(CronTab cronTab, Map<String, String> parameters) {
//		this.cronTab = cronTab;
		bits = (long[])FieldAccessor.access(cronTab, "bits");
		dayOfWeek = (Integer)FieldAccessor.access(cronTab, "dayOfWeek");
		
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
        return (bitMask|(1L<<n))==bitMask;
    }

	public boolean check(Calendar calendar) {
        if(!checkBits(bits[0],calendar.get(MINUTE)))
            return false;
        if(!checkBits(bits[1],calendar.get(HOUR_OF_DAY)))
            return false;
        if(!checkBits(bits[2],calendar.get(DAY_OF_MONTH)))
            return false;
        if(!checkBits(bits[3],calendar.get(MONTH)+1))
            return false;
        if(!checkBits(dayOfWeek,calendar.get(Calendar.DAY_OF_WEEK)-1))
            return false;

        return true;

//		Object result;
//		try {
//			Method method = CronTab.class.getMethod("check", Calendar.class);
//			result = method.invoke(cronTab, calendar);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		}
//		return (Boolean) result;
		//		return cronTab.check(calendar);
	}

	public String checkSanity() {
//        for( int i=0; i<5; i++ ) {
//            long bitMask = (i<4)?bits[i]:(long)dayOfWeek;
//            for( int j=BaseParser.LOWER_BOUNDS[i]; j<=BaseParser.UPPER_BOUNDS[i]; j++ ) {
//                if(!checkBits(bitMask,j)) {
//                    // this rank has a sparse entry.
//                    // if we have a sparse rank, one of them better be the left-most.
//                    if(i>0)
//                        return Messages.CronTab_do_you_really_mean_every_minute_when_you(spec, "0 " + spec.substring(spec.indexOf(' ')+1));
//                    // once we find a sparse rank, upper ranks don't matter
//                    return null;
//                }
//            }
//        }

        return null;
	}
}
