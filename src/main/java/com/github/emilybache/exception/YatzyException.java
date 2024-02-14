package com.github.emilybache.exception;

import java.text.MessageFormat;

public class YatzyException extends Exception{

	private static final long serialVersionUID = 1L;

	protected int code;

    public YatzyException(final IErrorCode errorCode) {
		super(errorCode.getMessage());
		this.code = errorCode.getCode();
	}

    public YatzyException(final IErrorCode errorCode, final Throwable cause) {
		super(errorCode.getMessage(), cause);
		this.code = errorCode.getCode();
	}
    
    public YatzyException(final IErrorCode errorCode, final Throwable cause, final Object... arguments) {
		super(formatBasicMessage(errorCode, arguments), cause);
		this.code = errorCode.getCode();
	}

    public YatzyException(final IErrorCode errorCode, final Object... arguments) {
		super(formatBasicMessage(errorCode, arguments));
		this.code = errorCode.getCode();
	}

	public int getCode() {
		return code;
	}

    private static String formatBasicMessage(final IErrorCode errorCode, final Object... arguments) {
    	String basicMessage = errorCode.getMessage();
    	String message = basicMessage.trim();
    	if(arguments != null && arguments.length > 0) {
        	message = MessageFormat.format(basicMessage, arguments).trim();
    	}
    	return message;
    }
    
}
