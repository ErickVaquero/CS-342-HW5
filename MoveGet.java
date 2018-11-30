
//////////////////////////////////////////////////////////////////////////////////////////////////
//										CS 342 FALL 2018										//
//////////////////////////////////////////////////////////////////////////////////////////////////
//		Author:			Jeremy Robles															//
//		Instructor:     Dr. John T. Bell														//
//		Lecture:		12:30 PM																//
//		Assignment:		HW4																		//
//		File Name:		MoveGet.java															//
//////////////////////////////////////////////////////////////////////////////////////////////////
//		Description:																			//
//	Implements the Move interface. Lets a character get an artifact in the current place.		//
//////////////////////////////////////////////////////////////////////////////////////////////////

public class MoveGet implements Move
{
/////////////////////////////////////////// ATTRIBUTES ///////////////////////////////////////////
	private Character character;
	private Place place;
	private String argument;
	
//////////////////////////////////////////CONSTRUCTOR //////////////////////////////////////////
	MoveGet(Character ch, Place p, String arg)
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
		if (place instanceof Shop)
		{
			System.out.println("The vendor won't allow you to pick up the merchandise.");
			System.out.println("\"Hey sexy stranger! Don't steal!\"");
			return;
		}
		
		//Character has no space to carry more items
		if (character.inventorySpace() == character.currentInventorySpace())
		{
			System.out.println(character.name() + " has no more space to carry anything!");
			return;
		}

		//Gets the artifact instance from place and removes it
		Artifact temp = place.removeArtifactByName(argument);

		//Item name does not exist
		if (temp == null)
		{
			System.out.println(character.name() + " could not get the " + argument.toLowerCase() + ".");
			return;
		}
		
		//Item is too heavy to carry, so drop item back on floor
		if (character.strength() < temp.mobility || temp.mobility < 0)
		{
			System.out.println(argument.toLowerCase() + " is to heavy to carry!");
			place.addArtifact(temp);
			return;
		}
		
		//Add item to inventory
		character.addArtifact(temp);
		System.out.println(character.name() + " gets the " + argument.toLowerCase() + ".");
		
		
	}
}// End of public class MoveGet implements Move
