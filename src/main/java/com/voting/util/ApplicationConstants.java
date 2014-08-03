package com.voting.util;

public class ApplicationConstants {

	/*
A motion cannot be closed for voting less than 15 minutes after it was opened.
No voter can vote more than once on the same motion.
The maximum votes that can be received on a motion is 101.

	 */

	public static int MIN_TIME_TO_CLOSE_MOTION_IN_SECONDS = 15*60;
	
	public static long MAX_VOTES_PER_MOTION = 101;
	
    public static String VP_VOTER_ID= "vp_voter_id";

    
    
    
    
	public static int getMIN_TIME_TO_CLOSE_MOTION_IN_SECONDS() {
		return MIN_TIME_TO_CLOSE_MOTION_IN_SECONDS;
	}

	public static void setMIN_TIME_TO_CLOSE_MOTION_IN_SECONDS(
			int mIN_TIME_TO_CLOSE_MOTION_IN_SECONDS) {
		MIN_TIME_TO_CLOSE_MOTION_IN_SECONDS = mIN_TIME_TO_CLOSE_MOTION_IN_SECONDS;
	}

	public static long getMAX_VOTES_PER_MOTION() {
		return MAX_VOTES_PER_MOTION;
	}

	public static void setMAX_VOTES_PER_MOTION(long mAX_VOTES_PER_MOTION) {
		MAX_VOTES_PER_MOTION = mAX_VOTES_PER_MOTION;
	}

	public static String getVP_VOTER_ID() {
		return VP_VOTER_ID;
	}

	public static void setVP_VOTER_ID(String vP_VOTER_ID) {
		VP_VOTER_ID = vP_VOTER_ID;
	}
	
    
    
    
	
	
	
}
