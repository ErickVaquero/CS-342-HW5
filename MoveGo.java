
//////////////////////////////////////////////////////////////////////////////////////////////////
//										CS 342 FALL 2018										//
//////////////////////////////////////////////////////////////////////////////////////////////////
//		Author:			Jeremy Robles															//
//		Instructor:     Dr. John T. Bell														//
//		Lecture:		12:30 PM																//
//		Assignment:		HW4																		//
//		File Name:		MoveGo.java																//
//////////////////////////////////////////////////////////////////////////////////////////////////
//		Description:																			//
//	Implements the Move interface. Lets the character move toward a direction.					//
//////////////////////////////////////////////////////////////////////////////////////////////////

public class MoveGo implements Move
{
/////////////////////////////////////////// ATTRIBUTES ///////////////////////////////////////////
	private Character character;
	private Place place;
	private String argument;
	
//////////////////////////////////////////CONSTRUCTOR //////////////////////////////////////////
	MoveGo(Character ch, Place p, String arg)
	{
		character = ch;
		place = p;
		argument = arg;
	}
	
////////////////////////////////////////MEMBER FUNCTIONS ////////////////////////////////////////	
//--------------------------------------------------------------------------------------------
	@Override
	public void execute()
	{
		//System.out.println(CleanLineScanner.divider1);
		
		Place temp = place.followDirection(argument);
		if (temp == null)
		{
			System.out.println(character.name() + " could not go " + argument.toLowerCase() + ".");
			return;
		}
		place.removeCharacter(character);
		character.goToPlace(temp);
		temp.addCharacter(character);
		if (place == temp)
			System.out.println(character.name() + " stays in the " + temp.name() + ".");
		else
			System.out.println(character.name() + " goes " + argument.toLowerCase() + " to the " + temp.name() + ".");
	}

}// End of public class MoveGo implements Move
