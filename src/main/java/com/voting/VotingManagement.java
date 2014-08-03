package com.voting;

import com.voting.core.Motion;
import com.voting.core.Vote;
import com.voting.core.enums.VoteStateEnum;
import com.voting.core.impl.MotionImpl;
import com.voting.core.impl.VoteImpl;

/*
 * 

Scenario/requirements:

You have been selected by the United States Senate to create a Java solution for managing the voting on 
motions once debate has concluded on the senate floor.

The system you create must accept votes on a specified motion, tabulate Yeas and Nays and determine 
whether motions pass or fail. 

No votes can be accepted upon a motion until it is opened for voting.

When a motion is closed for voting, a result is returned that describes 
o whether the motion passed or failed
o the number of votes for and against
o the time that voting opened and closed

A motion cannot be closed for voting less than 15 minutes after it was opened.
No voter can vote more than once on the same motion.
The maximum votes that can be received on a motion is 101.

If voting is a tie, then an attempt to close the motion for voting will cause it to enter a special 
“tied” state. 
o In the “tied” state, the Vice-president of the United States is the only person allowed to 
vote. Once the VP votes, the motion is automatically closed.
o The VP is not allowed to vote except when voting has entered the “tied” state.
o If the VP is not available to vote, then voting can be forced to the closed state which 
causes the motion to fail.
The system must support a query to discover the current state of a motion.

 * 
 */

public class VotingManagement {

	public static void main(String args[]){
		
		
		//System.out.println( Math.random()*100 );
		
		for( int i=0; i<100; i++){
			
			System.out.println(  i + ")  " + randomNumber(0, 1) );
			
		}
		
		
		
		System.out.println( " ###  Welcome to Voting Management ###" );
		
		Motion mot = new MotionImpl("motion01", "First Motion");
		
		mot.open();
		
		try{
		mot.castVote( new VoteImpl("v001", "motion01" , VoteStateEnum.YEAS));
		mot.castVote( new VoteImpl("v002", "motion01" , VoteStateEnum.NAYS));
		mot.castVote( new VoteImpl("v003", "motion01" , VoteStateEnum.NAYS));
		mot.castVote( new VoteImpl("v005", "motion01" , VoteStateEnum.YEAS));
		mot.castVote( new VoteImpl("v006", "motion01" , VoteStateEnum.YEAS));
		} catch( Exception e){
			e.printStackTrace();
		}
		
		
		try {
			mot.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		mot.printMotionResult();
		
	}
	
	
	public static int randomNumber(int minimum, int maximum){
		
		return (int) Math.round(Math.random());
		//return minimum + (int)(Math.random()*maximum); 
		
	}
	
	
}
