
//////////////////////////////////////////////////////////////////////////////////////////////////
//										CS 342 FALL 2018										//
//////////////////////////////////////////////////////////////////////////////////////////////////
//		Author:			Jeremy Robles															//
//		Instructor:     Dr. John T. Bell														//
//		Lecture:		12:30 PM																//
//		Assignment:		HW4																		//
//		File Name:		MoveConsume.java													 	//
//////////////////////////////////////////////////////////////////////////////////////////////////
//		Description:																			//
//	Implements the Move interface. 	Allows character to consume an item.						//
//////////////////////////////////////////////////////////////////////////////////////////////////

public class MoveConsume implements Move
{
/////////////////////////////////////////// ATTRIBUTES ///////////////////////////////////////////	
	private Character character;
	private Place place;
	private String argument;
	private Artifact consumable;
	
//////////////////////////////////////////CONSTRUCTOR //////////////////////////////////////////
	MoveConsume(Character ch, Place p, String arg)
	{
		character = ch;
		place = p;
		argument = arg;
		consumable = character.getArtifactByName(arg);
	}
	
////////////////////////////////////////MEMBER FUNCTIONS ////////////////////////////////////////	
//--------------------------------------------------------------------------------------------
	@Override
	public void execute() 
	{
		if (consumable == null)
		{
			System.out.println(character.name() + " could not consume the "
			+ argument + ".");
			return;
		}

		character.consumeItem((Consumable) consumable);
	}
}// End of public class MoveConsume implements Move
