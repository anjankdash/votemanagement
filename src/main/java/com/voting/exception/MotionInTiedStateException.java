package com.voting.exception;


/**
 * @author anjandash@gmail.com
 *
 */
public class MotionInTiedStateException extends VotingManagementException {

	public MotionInTiedStateException() {
		super();
		
	}

	public MotionInTiedStateException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

	public MotionInTiedStateException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public MotionInTiedStateException(String message) {
		super(message);
		
	}

	public MotionInTiedStateException(Throwable cause) {
		super(cause);
		
	}

	 
}
