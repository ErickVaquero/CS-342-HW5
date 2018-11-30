//////////////////////////////////////////////////////////////////////////////////////////////////
//										CS 342 FALL 2018										//
//////////////////////////////////////////////////////////////////////////////////////////////////
//		Author:			Jeremy Robles															//
//		Instructor:     Dr. John T. Bell														//
//		Lecture:		12:30 PM																//
//		Assignment:		HW4																		//
//		File Name:		AggressionAI.java														//
//////////////////////////////////////////////////////////////////////////////////////////////////
//		Description:																			//
//	This implementation decides what move the non-player characters will make. 					//
//  This type of AI will be used for certain characters to have a certain level of aggression   //
//  and can hurt each other.																	//
//////////////////////////////////////////////////////////////////////////////////////////////////


import java.util.Random;

 
public class AggressiveAI implements AI
{
	Random rand = new Random();
	@Override
	public Move getMove(Character ch, Place p)
	{
		String text = "";
		Character target = p.getRandTarget(ch);
		
		// Priority is to hurt other characters
		if (target != null)
		{
			return new MoveAttack(ch,p, target.name());
		}
		
		int randNum = rand.nextInt(100);
		
		// An aggressive NPC has the priority of Getting, Going, Dropping, then Using
		// If it does not meet any of the conditions, it will twiddle its thumbs
		
		text = p.firstItemToString();
		if (!text.isEmpty())
			return new MoveGet(ch, p, text);
		
		text = p.firstUnlockedDirection();
		if (!text.isEmpty())
			return new MoveGo(ch, p, text);
		
		text = ch.firstItemToString();
		if (randNum < 50 && !text.isEmpty())
			return new MoveDrop(ch, p, text);
		
		if (randNum < 100 && !text.isEmpty())
			return new MoveUse(ch, p, text);
		
		return new MoveTwiddle(ch);
		
	}// End public Move getMove(Character ch, Place p)

}// End of class AggressiveAI implements AI
