package com.voting.core.impl;

import java.util.Date;
import java.util.HashMap;

import com.voting.core.Motion;
import com.voting.core.MotionResult;
import com.voting.core.Vote;
import com.voting.core.enums.MotionResultEnum;
import com.voting.core.enums.MotionStateEnum;
import com.voting.core.enums.VoteStateEnum;
import com.voting.exception.DuplicateVoteException;
import com.voting.exception.InvalidVoteException;
import com.voting.exception.MaxVoteReachedException;
import com.voting.exception.MotionCanNotBeClosedException;
import com.voting.exception.MotionInTiedStateException;
import com.voting.util.ApplicationConstants;

/**
 * @author anjandash@gmail.com
 *
 */

public class MotionImpl  implements Motion{

	// Motion Identifiers
	private String motionID;
	private String motionName;
	
	
	
	private long opentime;
	private long closetime;
	
	private MotionStateEnum motionState ;
	
	private HashMap<String, Boolean> listOfYeas;
	private HashMap<String, Boolean> listOfNays;
	
	
	private boolean isMotionForceClosed = false;
	
	public MotionImpl(String motionID, String motionName) {
		super();
		
		// intialize identifiers
		
		this.motionID = motionID;
		this.motionName = motionName;
		
		// initialize the state
		motionState = MotionStateEnum.NOT_OPEN;
		
		listOfYeas = new HashMap<String, Boolean>();
		listOfNays = new HashMap<String, Boolean>();
		
	}

	
	
	/**
	 * Motion should open. Closing time should be noted
	 */
	public void open(){
		
		opentime = System.currentTimeMillis();
		motionState = MotionStateEnum.OPEN;
		
	}
	
	/**
	 * Motion should close.  Closing time should be noted 
	 * @throws MotionCanNotBeClosedException 
	 */
	public MotionResult close() throws MotionCanNotBeClosedException,MotionInTiedStateException{
		
		
		// if motion is not opened then motion can not be closed. Raise exception
		if( getCurrentState() == MotionStateEnum.CLOSED)
			throw new MotionCanNotBeClosedException(" Motion "+ motionName + " ( motion id : "+ motionID + ") is already closed");
	
		
		// if motion is not opened then motion can not be closed. Raise exception
		if( getCurrentState() == MotionStateEnum.NOT_OPEN)
			throw new MotionCanNotBeClosedException(" Motion can not be closed as "+ motionName + " ( motion id : "+ motionID + ") is not yet open for voting");
	
	
		// if motion open for minimum duration
		if(  getDurationOfMotionTillNow() < (ApplicationConstants.MIN_TIME_TO_CLOSE_MOTION_IN_SECONDS * 1000)  )
			throw new MotionCanNotBeClosedException(" Motion  "+ motionName + " ( motion id : "+ motionID + ") is not open for minimum duration of " +  ApplicationConstants.MIN_TIME_TO_CLOSE_MOTION_IN_SECONDS + " Seconds !");
	
	
		// Is motion tied state?
		 if( getMotionResult() == MotionResultEnum.TIED){
			 motionState = MotionStateEnum.TIED;  // As the result is TIED state.. So we are assigning the sate of the motion as TIED
			 throw new MotionInTiedStateException(" Motion  "+ motionName + " ( motion id : "+ motionID + ") is in TIED state. Vice President needs to cast his vote. If he is not available then forceClose() can be called !"); 
		 }
			
		 
		
		closetime = System.currentTimeMillis();
		motionState = MotionStateEnum.CLOSED;
		return prepareMotionResult();
		
	}
	
	
	
	
	
	
	/**
	 * Find the current status of the Motion. Motion Status may be  NOT_OPEN, OPEN, CLOSED, TIED
	 */
	public MotionStateEnum getCurrentState(){
		return motionState;
	}
	
	
	/**
	 * Get duration of Motion ... i.e  (Close time - Open time) 
	 */
	public long getDurationOfMotion(){
		
		return (closetime - opentime);
		
	}
	
	
	
	/**
	 * Get duration of Motion ... i.e  (Close time - Open time) 
	 */
	public long getDurationOfMotionTillNow(){
		
		return (System.currentTimeMillis() - opentime);
		
	}
	
	
	/* (non-Javadoc)
	 * @see com.voting.core.Motion#forceClose()
	 * 
	 * Force close the motion in case of non-availability of Vice President
	 */
	public MotionResult forceClose() throws MotionCanNotBeClosedException, MotionInTiedStateException {
		
		isMotionForceClosed = true;
		closetime = System.currentTimeMillis();
		motionState = MotionStateEnum.CLOSED;
		
		return prepareMotionResult();
		
	}

	
	/**
	 *  get motion opening time.  
	 */
	public Date getTimeWhenMotionOpened(){
		
		return new Date( opentime);
	}
	
	
	/**
	 *  get motion closing time
	 */
	public Date getTimeWhenMotionClosed(){
		return new Date( closetime);
	}

	
	/**
	 *  Cast A Vote on a Motion  
	 */
	public boolean castVote(Vote v) throws InvalidVoteException, DuplicateVoteException, MaxVoteReachedException{
		
		// Check the TIED state and Check if Person casting vote is VP 
		if( getCurrentState() == MotionStateEnum.TIED && !isVP( v.getVoterID())  )
			throw new InvalidVoteException("Motion "+ motionName + " ( motion id : "+ motionID + ") is in TIED State. Only VP can cast vote");
		
		// if VP Then check if the result is tied. Then only allow VP to vote
		if( getCurrentState() != MotionStateEnum.TIED && isVP( v.getVoterID())  )
			throw new InvalidVoteException(" Invalid Vote. Vice President can not vote if the motion is not in TIED state ! ");
				   
			
		
		// if motion is closed raise exception 
		if( getCurrentState() == MotionStateEnum.CLOSED)
			throw new InvalidVoteException("Invalid Vote as motion : "+ motionName + " ( motion id : "+ motionID + ") is closed for voting");
		
		// if motion is not opned raise exception 
		if( getCurrentState() == MotionStateEnum.NOT_OPEN)
			throw new InvalidVoteException("Invalid Vote as motion : "+ motionName + " ( motion id : "+ motionID + ") is not opened for voting");
		
		
		// if motion id of Voter is not equal to motion id of the motion then vote is invalid
		if( ! v.getMotionID().equals(motionID) )
			throw new InvalidVoteException("Motion ID mentioned in the Vote does not match with motion id of the specific motion ! ");
			

		// Is max limit reached
		if( isMaxVotingLimitReached())
			throw new MaxVoteReachedException(" Maximum Limit of allowed votes reached for motion : "+ motionName + " ( motion id : "+ motionID + ")");
		
		
		// Is the voter casting duplicate Vote ?
		if( isVoterDuplicate(v.getVoterID()))
			throw new DuplicateVoteException(" Voter id "+ v.getVoterID() + " has already casted vote !");
		
				
		if(v.getVotingState() == VoteStateEnum.YEAS){
			listOfYeas.put(v.getVoterID(), true);
		
		}else if(v.getVotingState() == VoteStateEnum.NAYS){
			listOfNays.put(v.getVoterID(), true);
		
		}else if(v.getVotingState() == VoteStateEnum.NOT_CASTED){
			throw new InvalidVoteException(" Vote state is \"NOT_CASTED\". This can not be considered a vote");
		}

		// If VP casted the vote then motion need to be closed
		if( isVP( v.getVoterID()) ){
			try {
				close();
			} catch (MotionCanNotBeClosedException | MotionInTiedStateException e) {
				
				// rollback
				if( listOfNays.containsKey(v.getVoterID()))
					listOfNays.remove(v.getVoterID());
				
				if( listOfYeas.containsKey(v.getVoterID()))
					listOfYeas.remove(v.getVoterID());
				
				throw new InvalidVoteException(" VP Vote casted but motion could not be closed ! Try Again later .. ", e);
				
			}	   
		}

		
		return true;
	}

	
	private boolean isVoterDuplicate( String voterID){
		if( listOfNays.containsKey(voterID))
			return true;
		
		if( listOfYeas.containsKey(voterID))
			return true;
		return false;
	}
	
	
	private boolean isMaxVotingLimitReached(){
		if( (listOfNays.size() + listOfYeas.size())  >= ApplicationConstants.MAX_VOTES_PER_MOTION )
			return true;
		return false;
	}
	
	private boolean isVP(String voterId){
		if( ( voterId.equals(ApplicationConstants.VP_VOTER_ID)))
			return true;
		return false;
	}
	
	
	
	
	
	private  MotionResultEnum getMotionResult() {
		
		// If the motion has been force closed then motion failed
		if(isMotionForceClosed)
			return MotionResultEnum.FAILED;
		
		
		if( listOfNays.size() == listOfYeas.size() )
			  return MotionResultEnum.TIED;
		
		else if( listOfNays.size() > listOfYeas.size() )
			  return MotionResultEnum.FAILED;
		else
			  return MotionResultEnum.PASSED;
	
	}

	public void printMotionResult() {
		System.out.println( "  ######################################################################################### ");
		System.out.println( "  ######## Result of Motion :  "+ motionName + " ( motion id : "+ motionID + ")");
		System.out.println( "  ######################################################################################### ");
		
		if( getMotionResult() == MotionResultEnum.PASSED)
			System.out.println( "  \n\n Motion Status :  PASSED ");
		
		if( getMotionResult() == MotionResultEnum.FAILED)
			System.out.println( "  \n\n Motion Status :  FAILED");
		
		if( getMotionResult() == MotionResultEnum.TIED)
			System.out.println( "  \n\n Motion Status :  TIED");
		
			System.out.println( "  Number of votes FOR motion : " + listOfYeas.size());
			System.out.println( "  Number of votes AGAINST motion : " + listOfNays.size());
		
			
			System.out.println( "\n  Motion Open Time : " + getTimeWhenMotionOpened());
			System.out.println( "  Motion Close Time : " + getTimeWhenMotionClosed());
		
	}
	
	
	public MotionResult prepareMotionResult() {
		
		MotionResult mr = new MotionResult();
		
		mr.setMotionID(motionID);
		mr.setMotionName(motionName);
		
		if( getMotionResult() == MotionResultEnum.PASSED)
			mr.setMotionStatus("PASSED");
		
		if( getMotionResult() == MotionResultEnum.FAILED)
			mr.setMotionStatus("FAILED");
		
		mr.setVotesForMotion(listOfYeas.size());
		mr.setVotesAgainstMotion(listOfNays.size());
			
		
		mr.setVotingOpenTime(getTimeWhenMotionOpened());
		mr.setVotingCloseTime(getTimeWhenMotionClosed());
		
		return mr;
	}
	
	
	
}
