
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
 * No voter can vote more than once on the same motion
 */
public class DuplicateVoterTest {

	String motionID = "motion01";
	String motionName = "First Motion";
	
	Motion mot;
	
	@Before
	public void setUp() {
	
		 mot = new MotionImpl(motionID, motionName);
		
	}

	
	@After
	public void clear() throws Exception {
		mot = null;
	}

	
	@Test(expected=DuplicateVoteException.class)
	public void DuplicateVoterTest_test() throws InvalidVoteException, DuplicateVoteException, MaxVoteReachedException {
		mot.open();
		mot.castVote( new VoteImpl("voter001", motionID, Util.getRandomVoteState()));
		mot.castVote( new VoteImpl("voter001", motionID, Util.getRandomVoteState()));  // duplicate vote
		
	}

	
}
















