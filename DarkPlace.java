//////////////////////////////////////////////////////////////////////////////////////////////////
//										CS 342 FALL 2018										//
//////////////////////////////////////////////////////////////////////////////////////////////////
//		Author:			Cecilia Avila											     			//
//		Instructor:     Dr. John T. Bell														//
//		Lecture:		12:30 PM																//
//		Assignment:		HW4																		//
//		File Name:		DarkPlace.java														    //
//////////////////////////////////////////////////////////////////////////////////////////////////
//		Description:																			//
//  This is the DarkPlace.java which extends from the Place class. This type of place is a dark //
//  place where characters need a lamp, otherwise they can't see where their surroundings.      //
//////////////////////////////////////////////////////////////////////////////////////////////////

import java.util.Scanner;

public class DarkPlace extends Place {
/////////////////////////////////////////// ATTRIBUTES ///////////////////////////////////////////
	private String darkDesc;
	private boolean isLit = false;
	
	
////////////////////////////////////////// CONSTRUCTOR //////////////////////////////////////////
	public DarkPlace(Scanner s, double version) {
		super(s, version);
		isLit = false;
		
	}
	
//////////////////////////////////////// MEMBER FUNCTIONS ////////////////////////////////////////	
//--------------------------------------------------------------------------------------------
	 // Determine if room is lit or dark. If character has lamp, isLit is true 
	public boolean islit(Character c, Artifact lamp) {
		if (characterExists(c) && c.hasArtifact(lamp)) {
			return isLit = true;
		}
		return false;
	}
	
//--------------------------------------------------------------------------------------------
	public void display() {
		if (isLit == true) {
			System.out.println("The room is lit by your latern.");
		}
		else {
			System.out.println("Careful, you have no way of seeing where you are going.");
		}
		
	}
//--------------------------------------------------------------------------------------------
	public void print() {
		System.out.println("isLit mode: " + isLit);
		System.out.println("Dark description: " + darkDesc );
	}
	
}// End of public class DarkPlace extends Place
