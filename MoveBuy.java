
//////////////////////////////////////////////////////////////////////////////////////////////////
//										CS 342 FALL 2018										//
//////////////////////////////////////////////////////////////////////////////////////////////////
//		Author:			Jeremy Robles															//
//		Instructor:     Dr. John T. Bell														//
//		Lecture:		12:30 PM																//
//		Assignment:		HW4																		//
//		File Name:		MoveBuy.java															//
//////////////////////////////////////////////////////////////////////////////////////////////////
//		Description:																			//
//	Implements the Move interface. Allows a player to purchase an item at the shop.				//
//////////////////////////////////////////////////////////////////////////////////////////////////

public class MoveBuy implements Move
{
/////////////////////////////////////////// ATTRIBUTES ///////////////////////////////////////////	
	private Character character;
	private Place place;
	private String argument;
	
//////////////////////////////////////////CONSTRUCTOR //////////////////////////////////////////
	MoveBuy(Character ch, Place p, String arg)
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
		if ((place instanceof Shop) == false)
		{
			character.display("You can only buy items in shops.");
			return;
		}
		
		Shop shop = (Shop) place;
		
		shop.purchase(character, argument);
	}
}// End of public class MoveBuy implements Move
