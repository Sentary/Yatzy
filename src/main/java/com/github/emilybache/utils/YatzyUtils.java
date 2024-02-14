package com.github.emilybache.utils;

import java.util.Arrays;
import java.util.stream.Collectors;

import com.github.emilybache.enums.DiceStatus;
import com.github.emilybache.exception.YatzyException;

public class YatzyUtils {

	private YatzyUtils() {
		throw new IllegalStateException("Utility class");
	}

	public static String getRollDicesInfo(final DiceStatus... diceStatusArray) {
		String info = "N/A";
		if (diceStatusArray != null) {
			info = Arrays.stream(diceStatusArray).filter(dice -> dice != null)
					.map(dice -> String.valueOf(dice.getNumber())).collect(Collectors.joining(", "));
		}
		return info;
	}

	public static String formatYatzyExceptionMessage(final YatzyException yatzyException) {
		if (yatzyException == null) {
			return null;
		}
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Code:").append(yatzyException.getCode());
		stringBuilder.append("; Message:").append(yatzyException.getMessage());
		if (yatzyException.getCause() != null) {
			stringBuilder.append("; Cause:").append(yatzyException.getCause().getMessage());
		}
		return stringBuilder.toString();
	}
}
