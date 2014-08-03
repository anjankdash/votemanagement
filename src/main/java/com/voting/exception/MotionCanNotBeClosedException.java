package com.voting.exception;


/**
 * @author anjandash@gmail.com
 *
 */
public class MotionCanNotBeClosedException extends VotingManagementException {

	public MotionCanNotBeClosedException() {
		super();
		
	}

	public MotionCanNotBeClosedException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

	public MotionCanNotBeClosedException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public MotionCanNotBeClosedException(String message) {
		super(message);
		
	}

	public MotionCanNotBeClosedException(Throwable cause) {
		super(cause);
		
	}

	 
}
