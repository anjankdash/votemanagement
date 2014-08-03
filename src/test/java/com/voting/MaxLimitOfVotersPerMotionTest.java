
package com.voting;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.voting.core.Motion;
import com.voting.core.Vote;
import com.voting.core.enums.VoteStateEnum;
import com.voting.core.impl.MotionImpl;
import com.voting.core.impl.VoteImpl;
import com.voting.exception.DuplicateVoteException;
import com.voting.exception.InvalidVoteException;
import com.voting.exception.MaxVoteReachedException;
import com.voting.exception.MotionCanNotBeClosedException;
import com.voting.util.ApplicationConstants;
import com.voting.util.Util;


/**
 * This test is for checking Voting Management module
 * @author anjandash@gmail.com
 * 
 * 
 * The maximum votes that can be received on a motion is 101.
 */
public class MaxLimitOfVotersPerMotionTest {

	String motionID = "motion01";
	String motionName = "First Motion";
	
	Motion mot;
	
	@Before
	public void setUp() {
	
		 mot = new MotionImpl(motionID, motionName);
		
	}

	
	@After
	public void clear() throws Exception {

	}

	
	@Test(expected=MaxVoteReachedException.class)
	public void MaxLimitOfVotersPerMotionTest_test() throws InvalidVoteException, DuplicateVoteException, MaxVoteReachedException {
		mot.open();
		
		for( int i=0; i<102; i++){
			mot.castVote( new VoteImpl( Util.getRandomVoterID(), motionID, Util.getRandomVoteState()));
		}
	
		
		
		
		
	}

	
}
















