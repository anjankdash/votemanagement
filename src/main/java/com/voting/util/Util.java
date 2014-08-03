package com.voting.util;

import com.voting.core.Motion;
import com.voting.core.Vote;
import com.voting.core.enums.VoteStateEnum;

public class Util {

	
public static void cast(Motion mot, Vote v){
		
		try{
			mot.castVote( v);
		} catch( Exception e){
			e.printStackTrace();
		}
	}

	
public static String getRandomVoterID(){
		
		String voterID = "vot" + ( 0 + (int)(Math.random()*100000) ) ;
		
		voterID += System.currentTimeMillis();
		return voterID;
		
	}
	
	

public static VoteStateEnum getRandomVoteState(){
		
		int voteState = (int) Math.round(Math.random());
		 
		if( voteState == 0)
			return VoteStateEnum.NAYS;
		else
			return VoteStateEnum.YEAS;
		
	}

	
}
