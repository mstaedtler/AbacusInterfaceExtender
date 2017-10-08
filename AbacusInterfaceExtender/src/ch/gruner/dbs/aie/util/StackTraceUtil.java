package ch.gruner.dbs.aie.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;


/**************************************************************************************
                          
 **************************************************************************************
 * @name      StackTraceUtil.java
 * @author    MS
 * @generated 26.03.2010 at 14:16:22 
 * 
 * Simple utilities to return the stack trace of an exception as a String.
 * 
 * 
 **************************************************************************************/
public final class StackTraceUtil {

	/**
	 * Gives you the stack trace as String.
	 * @param aThrowable the throwable (exception or sth.like this)
	 * @return the stacktrace as an String
	 */
	public static String getStackTrace(Throwable aThrowable) {
		final Writer result = new StringWriter();
		final PrintWriter printWriter = new PrintWriter(result);
		aThrowable.printStackTrace(printWriter);
		return result.toString();
	}

	/**
	 * Defines a custom format for the stack trace as String.
	 * @param aThrowable the throwable (exception or sth.like this)
	 * @return the stacktrace as an String
	 */
	public static String getCustomStackTrace(Throwable aThrowable) {
		// add the class name and any message passed to constructor
		final StringBuilder result = new StringBuilder();
		result.append(aThrowable.toString());
		final String NEW_LINE = System.getProperty("line.separator");
		result.append(NEW_LINE);

		// add each element of the stack trace
		for (StackTraceElement element : aThrowable.getStackTrace()) {
			result.append(element);
			result.append(NEW_LINE);
		}
		return result.toString();
	}
}
