package com.voting.core;

import java.util.Date;

import com.voting.core.enums.MotionResultEnum;
import com.voting.core.enums.MotionStateEnum;
import com.voting.exception.DuplicateVoteException;
import com.voting.exception.InvalidVoteException;
import com.voting.exception.MaxVoteReachedException;
import com.voting.exception.MotionCanNotBeClosedException;
import com.voting.exception.MotionInTiedStateException;

/**
 * @author anjandash@gmail.com
 *
 */
public interface Motion {

	
	/**
	 * Motion should open. Closing time should be noted
	 */
	public void open();
	
	/**
	 * Motion should close.  Closing time should be noted 
	 */
	public MotionResult close() throws MotionCanNotBeClosedException, MotionInTiedStateException;
	
	
	/**
	 * Force Close the motion 
	 */
	public MotionResult forceClose() throws MotionCanNotBeClosedException, MotionInTiedStateException;
	
	
	/**
	 * Find the current status of the Motion. Motion Status may be  NOT_OPEN, OPEN, CLOSED, TIED
	 */
	public MotionStateEnum getCurrentState();
	
	
	/**
	 * Get duration of Motion ... i.e  (Close time - Open time) 
	 */
	public long getDurationOfMotion();
	
	
	/**
	 *  Cast A Vote on a Motion  
	 */
	public boolean castVote( Vote v) throws InvalidVoteException, DuplicateVoteException, MaxVoteReachedException;
	
	
	/**
	 *  get motion opening time.  
	 */
	public Date getTimeWhenMotionOpened();
	
	
	/**
	 *  get motion closing time
	 */
	public Date getTimeWhenMotionClosed();
	
	
	
	/**
	 *  Print Result of the Motion
	 *  
	 *  When a motion is closed for voting, a result is returned that describes 
			o whether the motion passed or failed
			o the number of votes for and against
			o the time that voting opened and closed
	 */
	public void printMotionResult();
	
	
	
}
