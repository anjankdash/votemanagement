
package com.voting;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.voting.core.Motion;
import com.voting.core.Vote;
import com.voting.core.enums.MotionStateEnum;
import com.voting.core.enums.VoteStateEnum;
import com.voting.core.impl.MotionImpl;
import com.voting.core.impl.VoteImpl;
import com.voting.exception.DuplicateVoteException;
import com.voting.exception.InvalidVoteException;
import com.voting.exception.MaxVoteReachedException;
import com.voting.exception.MotionCanNotBeClosedException;
import com.voting.exception.MotionInTiedStateException;
import com.voting.util.ApplicationConstants;
import com.voting.util.Util;


/**
 * This test is for checking Voting Management module
 * @author anjandash@gmail.com
 * 
 * 
 * Do not allow normal voting when in TIED state
 */
public class DoNotAllowNormalVotingInTiedStateTest {

	String motionID = "motion01";
	String motionName = "First Motion";
	
	Motion mot;
	
	@Before
	public void setUp() {
	
		ApplicationConstants.MIN_TIME_TO_CLOSE_MOTION_IN_SECONDS = 0;
		 mot = new MotionImpl(motionID, motionName);
		
	}

	
	@After
	public void clear() throws Exception {

		mot = null;
	}

	
	@Test(expected=InvalidVoteException.class)
	public void DoNotAllowNormalVotingInTiedStateTest_test() throws MotionInTiedStateException, InvalidVoteException, DuplicateVoteException, MaxVoteReachedException, MotionCanNotBeClosedException {
		mot.open();
		
		// Cast YEAS vote
		for( int i=0; i<50; i++){
			mot.castVote( new VoteImpl( Util.getRandomVoterID(), motionID, VoteStateEnum.YEAS));
		}
		
		// Cast NAYS vote
		for( int i=0; i<50; i++){
			mot.castVote( new VoteImpl( Util.getRandomVoterID(), motionID, VoteStateEnum.NAYS));
		}
		
		
		try {
			mot.close();
		} catch (Exception e) {
			//e.printStackTrace();
		}
		
		mot.castVote( new VoteImpl( Util.getRandomVoterID(), motionID, VoteStateEnum.NAYS));
		
	}

	
}
















