package com.voting;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ DoNotAllowNormalVotingInTiedStateTest.class,
		DuplicateVoterTest.class, MaxLimitOfVotersPerMotionTest.class,
		MotionCannotBeClosedLessThan15MinutesOfOpenTest.class,
		NoVotingAcceptedTillMotionOpenTest.class,
		QueryToGetCurrentStateTest.class, TIEDstateTest.class,
		VPNotAllowedToVoteWithoutTiedStateTest.class,
		VpNotAvailalbleSoForceCloseTest.class,
		VpVoteInTiedStateOfMotionTest.class,MotionCloseReturnResultofMotionTest.class })
public class AllTests {

}
