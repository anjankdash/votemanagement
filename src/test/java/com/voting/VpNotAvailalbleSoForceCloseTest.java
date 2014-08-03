
package com.voting;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.voting.core.Motion;
import com.voting.core.MotionResult;
import com.voting.core.Vote;
import com.voting.core.enums.MotionResultEnum;
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
 * In the “tied” state, the Vice-president of the United States is the only person allowed to 
vote. Once the VP votes, the motion is automatically closed.

 */
public class VpNotAvailalbleSoForceCloseTest {

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

	}

	
	@Test
	public void VpNotAvailalbleSoForceCloseTest_test() throws MotionInTiedStateException, InvalidVoteException, DuplicateVoteException, MaxVoteReachedException, MotionCanNotBeClosedException {
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
		
		assertTrue("State of the Motion Before VP vote is TIED : ", (mot.getCurrentState() == MotionStateEnum.TIED) );
		MotionResult mr = mot.forceClose();
		
		assertTrue("State of the Motion After force close : ", (mot.getCurrentState() == MotionStateEnum.CLOSED) );
		assertTrue("Result of the Motion Failed : ", ( mr.getMotionStatus().equalsIgnoreCase("FAILED")) );
		
	}
}
















