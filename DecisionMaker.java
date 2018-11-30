
//////////////////////////////////////////////////////////////////////////////////////////////////
//										CS 342 FALL 2018										//
//////////////////////////////////////////////////////////////////////////////////////////////////
//		Author:			Jeremy Robles															//
//		Instructor:     Dr. John T. Bell													    //
//		Lecture:		12:30 PM																//
//		Assignment:		HW4																	    //
//		File Name:		DecisionMaker.java														//
//////////////////////////////////////////////////////////////////////////////////////////////////
//		Description:																			//
// The UI implementation of DecisionMaker will conduct a lot of the work formerly in the		//
// Game.play() method, displaying the current location to the user player, getting a command	//
// from the keyboard, verifying it is a valid command, and then encapsulating the user's input	//
// into a Move object for return.																//
// The AI implementation will decide what move the non-player characters make. "LOOK", "QUIT",	//
// "EXIT", and "INVENTORY" are not allowed for non-player characters.							//
//////////////////////////////////////////////////////////////////////////////////////////////////

public interface DecisionMaker
{
	/////////////////////////////////////////// ATTRIBUTES ///////////////////////////////////////////
	
	//////////////////////////////////////////CONSTRUCTORS //////////////////////////////////////////
	
	////////////////////////////////////////MEMBER FUNCTIONS ////////////////////////////////////////
	//--------------------------------------------------------------------------------------------
	// Determines the next move to make for the given Character ch in the given Place p. The
	// result is encapsulated in a Move object for return to the Character.makeMove() method.
	public Move getMove(Character ch, Place p);
}//end public class DecisionMaker
