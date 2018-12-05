
//////////////////////////////////////////////////////////////////////////////////////////////////
//										CS 342 FALL 2018										//
//////////////////////////////////////////////////////////////////////////////////////////////////
//		Author:			Jeremy Robles															//
//		Instructor:     Dr. John T. Bell														//
//		Lecture:		12:30 PM																//
//		Assignment:		HW4																		//
//		File Name:		MoveAttack.java															//
//////////////////////////////////////////////////////////////////////////////////////////////////
//		Description:																			//
//	Implements the Move interface. Allows character to attack another character.				//
//////////////////////////////////////////////////////////////////////////////////////////////////

public class MoveAttack  implements Move
{
/////////////////////////////////////////// ATTRIBUTES ///////////////////////////////////////////	
	private Character character;
	private Place place;
	private Character target;
	private String argument;
	
//////////////////////////////////////////CONSTRUCTOR //////////////////////////////////////////
	MoveAttack(Character ch, Place p, String arg)
	{
		character = ch;
		place = p;
		argument = arg;
		target = Character.stringToCharacter(arg);
	}
////////////////////////////////////////MEMBER FUNCTIONS ////////////////////////////////////////	
//--------------------------------------------------------------------------------------------
	@Override
	public void execute() 
	{
		if (target == null)
		{
			character.display(character.name() + " could not attack "
			+ argument + ".");
			return;
		}
		
		character.attack(target);
	}
}// End of public class MoveAttack implements Move
