package com.github.emilybache.exception;

public class YatzyErrorMessages {

	private YatzyErrorMessages() {
		throw new IllegalStateException("Utility class");
	}

	public static final String ROLL_CATEGORY_NULL = "The given roll category should not be null";
	public static final String ROLL_DICE_MESSING = "One or multiple dices are missing, please check and send {0} non null dices";
	public static final String APPLICATION_ERROR = "An error occured while processing the current dice roll [{0}]";
}
