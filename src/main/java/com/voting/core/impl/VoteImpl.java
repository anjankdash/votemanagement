package com.voting.core.impl;

import java.util.Date;

import com.voting.core.Vote;
import com.voting.core.enums.VoteStateEnum;

/**  2nd change
 * @author anjandash@gmail.com
 *
 */
public class VoteImpl implements Vote{

	
	String voterID;
	String motionID;
	VoteStateEnum voteState;
	
	
	public VoteImpl(String voterID, String motionID, VoteStateEnum voteState) {
		super();
		this.voterID = voterID;
		this.motionID = motionID;
		this.voteState = voteState;
	}
	
	
	/**
	 * get Voter ID
	 */
	public String getVoterID(){
		return voterID;
	}
	
	/**
	 * get Motion ID 
	 */
	public String getMotionID(){
		return motionID;
	}
	
	/**
	 *  State of the Vote ..  
	 */
	public VoteStateEnum getVotingState(){
		return voteState;
	}


	public VoteStateEnum getVoteState() {
		return voteState;
	}

/*
	public void setVoteState(VoteState voteState) {
		this.voteState = voteState;
	}

	public void setVoterID(String voterID) {
		this.voterID = voterID;
	}


	public void setMotionID(String motionID) {
		this.motionID = motionID;
	}
*/	
	
	
}
