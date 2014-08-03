package com.voting.exception;


/**
 * @author anjandash@gmail.com
 *
 */
public class DuplicateVoteException extends VotingManagementException {

	public DuplicateVoteException() {
		super();
		
	}

	public DuplicateVoteException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

	public DuplicateVoteException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public DuplicateVoteException(String message) {
		super(message);
		
	}

	public DuplicateVoteException(Throwable cause) {
		super(cause);
		
	}

	 
}
