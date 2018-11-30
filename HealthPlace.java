//////////////////////////////////////////////////////////////////////////////////////////////////
//										CS 342 FALL 2018										//
//////////////////////////////////////////////////////////////////////////////////////////////////
//		Author:			Cecilia Avila											     			//
//		Instructor:     Dr. John T. Bell														//
//		Lecture:		12:30 PM																//
//		Assignment:		HW4																		//
//		File Name:		HealthPlace.java														//
//////////////////////////////////////////////////////////////////////////////////////////////////
//		Description:																			//
//  This is the HealthPlace.java which extends from the Place class. This type of place is a    //
//  place where it can either health or damaged the characters in the room. 					// 
//////////////////////////////////////////////////////////////////////////////////////////////////

import java.util.Scanner;

public class HealthPlace extends Place{
/////////////////////////////////////////// ATTRIBUTES ///////////////////////////////////////////	
	private int healthChange;
	 
////////////////////////////////////////// CONSTRUCTOR //////////////////////////////////////////	
	public HealthPlace(Scanner s, double version) {
		super(s, version);
		healthChange = s.nextInt();
	}
	
//////////////////////////////////////// MEMBER FUNCTION ////////////////////////////////////////	 	
	// Add/subtract health to all characters in this place 
	public void applyHealthChange() {
		for (int i = 0; i < charVector.size(); i++) {
			// Character has been heeled
			if (charVector.get(i).incapacitated() == false) {
				if (healthChange > 0) {
					charVector.get(i).receiveAttack(healthChange); 
					System.out.println("NICE! You've entered a healing place. /n Your health health is now at " + charVector.get(i).currentHealth + ".");
				}
				// Character has been hurt
				else if(healthChange < 0)  {
					charVector.get(i).receiveAttack(healthChange);
					System.out.println("Oh no, you've just been hurt! /n Your health health is now at " + charVector.get(i).currentHealth + ".");
				}
			}

		}// End of for (int i = 0; i < charVector.size(); i++)
	}
}// End of public class HealthPlace extends Place
