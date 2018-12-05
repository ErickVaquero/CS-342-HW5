//////////////////////////////////////////////////////////////////////////////////////////////////
//										CS 342 FALL 2018										//
//////////////////////////////////////////////////////////////////////////////////////////////////
//		Author:			Jeremy Robles															//
//		Instructor:     Dr. John T. Bell														//
//		Lecture:		12:30 PM																//
//		Assignment:		HW4																		//
//		File Name:		MoveUse.java															//
//////////////////////////////////////////////////////////////////////////////////////////////////
//		Description:																			//
//	Implements the Move interface. It lets a character use an item in the current place.		//
//////////////////////////////////////////////////////////////////////////////////////////////////

public class MoveUse implements Move
{
/////////////////////////////////////////// ATTRIBUTES ///////////////////////////////////////////
	private Character character;
	private Place place;
	private String argument;
	
//////////////////////////////////////////CONSTRUCTOR //////////////////////////////////////////
	MoveUse(Character ch, Place p, String arg)
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
		
		Artifact temp = character.getArtifactByName(argument);
		
		if (temp == null)
		{
			character.display(character.name() + " could not use " +
					argument.toLowerCase() + " in the " + place.name() + ".");
			return;
		}
		character.display(character.name() + " uses " +
				argument.toLowerCase() + " in the " + place.name() + ".");
		temp.use(character, place);
		
	}// End public void execute()

}// End public class MoveUse implements Move
