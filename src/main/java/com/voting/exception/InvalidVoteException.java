package com.voting.exception;


/**
 * @author anjandash@gmail.com
 *
 */
public class InvalidVoteException extends VotingManagementException {

	public InvalidVoteException() {
		super();
		
	}

	public InvalidVoteException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

	public InvalidVoteException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public InvalidVoteException(String message) {
		super(message);
		
	}

	public InvalidVoteException(Throwable cause) {
		super(cause);
		
	}

	 
}
