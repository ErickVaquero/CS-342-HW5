
//////////////////////////////////////////////////////////////////////////////////////////////////
//										CS 342 FALL 2018										//
//////////////////////////////////////////////////////////////////////////////////////////////////
//		Author:			Jeremy Robles															//
//		Instructor:     Dr. John T. Bell													    //
//		Lecture:		12:30 PM																//
//		Assignment:		HW4																		//
//		File Name:		MoveEquip.java															//
//////////////////////////////////////////////////////////////////////////////////////////////////
//		Description:																			//
//	Implements the Move interface. Allows player to equip gear/weapon.							//
//////////////////////////////////////////////////////////////////////////////////////////////////

public class MoveEquip  implements Move
{
/////////////////////////////////////////// ATTRIBUTES ///////////////////////////////////////////
	private Character character;
	private Place place;
	private String argument;
	private Artifact equipable;
	
//////////////////////////////////////////CONSTRUCTOR //////////////////////////////////////////
	MoveEquip(Character ch, Place p, String arg)
	{
		character = ch;
		place = p;
		argument = arg;
		equipable = character.getArtifactByName(arg);
	}
	
////////////////////////////////////////MEMBER FUNCTIONS ////////////////////////////////////////	
//--------------------------------------------------------------------------------------------
	@Override
	public void execute() 
	{
		if (equipable == null)
		{
			character.display(character.name() + " could not equip the "
			+ argument + ".");
			return;
		}
		
		if (equipable instanceof Gear)
			character.equipGear((Gear)equipable);
		
		else if (equipable instanceof Weapon)
			character.equipWeapon((Weapon)equipable);
	}
}// End of public class MoveEquip  implements Move
