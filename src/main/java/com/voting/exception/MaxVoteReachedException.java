package com.voting.exception;


/**
 * @author anjandash@gmail.com
 *
 */
public class MaxVoteReachedException extends VotingManagementException {

	public MaxVoteReachedException() {
		super();
		
	}

	public MaxVoteReachedException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

	public MaxVoteReachedException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public MaxVoteReachedException(String message) {
		super(message);
		
	}

	public MaxVoteReachedException(Throwable cause) {
		super(cause);
		
	}

	 
}
