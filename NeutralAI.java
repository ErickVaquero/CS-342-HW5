//////////////////////////////////////////////////////////////////////////////////////////////////
//										CS 342 FALL 2018										//
//////////////////////////////////////////////////////////////////////////////////////////////////
//		Author:			Jeremy Robles															//
//		Instructor:     Dr. John T. Bell														//
//		Lecture:		12:30 PM																//
//		Assignment:		HW4																		//
//		File Name:		Neutral.java															//
//////////////////////////////////////////////////////////////////////////////////////////////////
//		Description:																			//
//	Implements the AI interface. It creates a type of neutral AI character to move around.		//
//////////////////////////////////////////////////////////////////////////////////////////////////

import java.util.Random;

public class NeutralAI implements AI
{
/////////////////////////////////////////// ATTRIBUTES ///////////////////////////////////////////
Random rand = new Random();
	
////////////////////////////////////////MEMBER FUNCTIONS ////////////////////////////////////////	
//--------------------------------------------------------------------------------------------
	@Override
	public Move getMove(Character ch, Place p)
	{
		String text = "";
		int randNum = rand.nextInt(100);
		
		// An NPC has 1/4 chance of Getting, Going, Dropping, or Using
		// If it does not meet any of the conditions, it will twiddle its thumbs
		
		text = p.firstItemToString();
		if (randNum < 25 && !text.isEmpty())
			return new MoveGet(ch, p, text);
		
		text = p.firstUnlockedDirection().trim();
		if (randNum  < 50 && !text.isEmpty())
			return new MoveGo(ch, p, text);
		
		text = ch.firstItemToString();
		if (randNum < 75 && !text.isEmpty())
			return new MoveDrop(ch, p, text);
		
		if (randNum < 100 && !text.isEmpty())
			return new MoveUse(ch, p, text);
		
		return new MoveTwiddle(ch);
		
	}// End public Move getMove(Character ch, Place p)
	
}// End of public class NeutralAI implements AI
