
//////////////////////////////////////////////////////////////////////////////////////////////////
//										CS 342 FALL 2018										//
//////////////////////////////////////////////////////////////////////////////////////////////////
//		Author:			Jeremy Robles															//
//		Instructor:     Dr. John T. Bell														//
//		Lecture:		12:30 PM																//
//		Assignment:		HW4																		//
//		File Name:		MoveUnequip.java														//
//////////////////////////////////////////////////////////////////////////////////////////////////
//		Description:																			//
//	Implements the Move interface. Allows the user to unequip their current gear/weapon.		//
//////////////////////////////////////////////////////////////////////////////////////////////////

public class MoveUnequip implements Move
{
/////////////////////////////////////////// ATTRIBUTES ///////////////////////////////////////////
	private Character character;
	private Place place;
	private String argument;
	private Artifact equipable;
	private int type;	//0-Gear, 1-weapon, else none
	
//////////////////////////////////////////CONSTRUCTOR //////////////////////////////////////////
	MoveUnequip(Character ch, Place p, String arg)
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
		if(type != 0 && type != 1)
		{
			System.out.println(character.name() + " could not equip the "
			+ argument + ".");
			return;
		}
		
		if (type == 0)
			character.unequipGear();
		
		else if (type == 2)
			character.unequipWeapon();
	}
}// End of public class MoveUnequip implements Move
