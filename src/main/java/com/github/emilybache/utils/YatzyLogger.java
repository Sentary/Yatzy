package com.github.emilybache.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.emilybache.exception.YatzyException;

public class YatzyLogger {

	private static Logger logger = LogManager.getLogger(YatzyLogger.class);

	private YatzyLogger() {
		throw new IllegalStateException("Utility class");
	}

	public static void logInfo(String message) {
		logger.info(message);
	}

	public static void logError(String message) {
		logger.error(message);
	}

	public static void logError(YatzyException yatzyException) {
		logError(YatzyUtils.formatYatzyExceptionMessage(yatzyException));
	}

	public static void logWarn(String message) {
		logger.warn(message);
	}

	public static void logWarn(YatzyException yatzyException) {
		logWarn(YatzyUtils.formatYatzyExceptionMessage(yatzyException));
	}
}
