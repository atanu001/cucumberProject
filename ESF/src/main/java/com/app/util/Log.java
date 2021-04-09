package com.app.util;

import org.apache.log4j.Logger;

public class Log {

	private static Logger logger = Logger.getLogger(Log.class);

	public static void info(String message) {
		logger.info(message);
	}
}
