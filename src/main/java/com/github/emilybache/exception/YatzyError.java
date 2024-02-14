package com.github.emilybache.exception;

/**
 *
 * Enumeration of error code raised by the Automation application Each enum
 * contain an integer error code and a message that is the generic message
 * associated to the error code
 *
 * @author Ismail
 *
 */
public enum YatzyError implements IErrorCode {
	ROLL_CATEGORY_NULL(1000, YatzyErrorMessages.ROLL_CATEGORY_NULL),
	ROLL_DICE_MESSING(1001, YatzyErrorMessages.ROLL_DICE_MESSING),
	APPLICATION_ERROR(5000, YatzyErrorMessages.APPLICATION_ERROR);

	private final int code;
	private final String message;

	private YatzyError(final int code, final String message) {
		this.code = code;
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public int getCode() {
		return code;
	}

}
