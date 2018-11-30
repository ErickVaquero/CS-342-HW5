//////////////////////////////////////////////////////////////////////////////////////////////////
//										CS 342 FALL 2018										//
//////////////////////////////////////////////////////////////////////////////////////////////////
//		Author:			Jeremy Robles															//
//		Instructor:     Dr. John T. Bell														//
//		Lecture:		12:30 PM																//
//		Assignment:		HW4																		//
//		File Name:		MoveDrop.java															//
//////////////////////////////////////////////////////////////////////////////////////////////////
//		Description:																			//
//	Implements the Move interface. It lets a character to drop an artifact in its current place.//
//////////////////////////////////////////////////////////////////////////////////////////////////

public class MoveDrop implements Move
{
/////////////////////////////////////////// ATTRIBUTES ///////////////////////////////////////////	
	private Character character;
	private Place place;
	private String argument;
	
//////////////////////////////////////////CONSTRUCTOR //////////////////////////////////////////
	MoveDrop(Character ch, Place p, String arg)
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
		if (place instanceof Shop)
		{
			System.out.println("The vendor won't allow you to drop your items.");
			System.out.println("\"I'll be happy to buy that off you instead!\"");
			return;
		}
		
		//System.out.println(CleanLineScanner.divider1);
		Artifact temp = character.getArtifactByName(argument);
		if (temp == null)
		{
			System.out.println(character.name() + " could not drop the " + argument.toLowerCase() + ".");
			return;
		}
		character.removeArtifact(temp);
		System.out.println(character.name() + " dropped the " + argument.toLowerCase()
		+ " in the " + place.name() + ".");
		
		
	}

}// End of public class MoveDrop implements Move
