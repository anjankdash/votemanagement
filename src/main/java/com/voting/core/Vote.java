package com.voting.core;

import java.util.Date;

import com.voting.core.enums.VoteStateEnum;

/**
 * @author anjandash@gmail.com
 *
 */
public interface Vote {

	
	/**
	 * get Voter ID
	 */
	public String getVoterID();
	
	/**
	 * get Motion ID 
	 */
	public String getMotionID();
	
	
	/**
	 *  State of the Vote ..  
	 */
	public VoteStateEnum getVotingState();
	
}
