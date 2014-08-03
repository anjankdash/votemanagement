package com.voting.core;

import java.util.Date;

import com.voting.core.enums.MotionResultEnum;

public class MotionResult {
	
	private String MotionStatus;
	
	private String motionName;
	private String motionID;
			
	private int votesForMotion;
	private int votesAgainstMotion;
		
	private Date votingOpenTime;
	private Date votingCloseTime;
	
	
	
	public String getMotionName() {
		return motionName;
	}
	public void setMotionName(String motionName) {
		this.motionName = motionName;
	}
	public String getMotionID() {
		return motionID;
	}
	public void setMotionID(String motionID) {
		this.motionID = motionID;
	}
	public String getMotionStatus() {
		return MotionStatus;
	}
	public void setMotionStatus(String motionStatus) {
		MotionStatus = motionStatus;
	}
	public int getVotesForMotion() {
		return votesForMotion;
	}
	public void setVotesForMotion(int votesForMotion) {
		this.votesForMotion = votesForMotion;
	}
	public int getVotesAgainstMotion() {
		return votesAgainstMotion;
	}
	public void setVotesAgainstMotion(int votesAgainstMotion) {
		this.votesAgainstMotion = votesAgainstMotion;
	}
	public Date getVotingOpenTime() {
		return votingOpenTime;
	}
	public void setVotingOpenTime(Date votingOpenTime) {
		this.votingOpenTime = votingOpenTime;
	}
	public Date getVotingCloseTime() {
		return votingCloseTime;
	}
	public void setVotingCloseTime(Date votingCloseTime) {
		this.votingCloseTime = votingCloseTime;
	}
	
	
	@Override
	public String toString(){
		
		StringBuilder str = new StringBuilder("");
		
		str.append("  ######################################################################################### ");
		str.append( "  ######## Result of Motion :  "+ motionName + " ( motion id : "+ motionID + ")");
		str.append( "  ######################################################################################### ");
		
			str.append( "  \n\n Motion Status :  "+ getMotionStatus() );
		
			str.append( "  Number of votes FOR motion : " + votesForMotion );
			str.append( "  Number of votes AGAINST motion : " + votesAgainstMotion);
		
			
			str.append( "\n  Motion Open Time : " + votingOpenTime);
			str.append( "  Motion Close Time : " + votingCloseTime);
		
		
		return "";
	}
	
	
}
