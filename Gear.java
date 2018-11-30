//////////////////////////////////////////////////////////////////////////////////////////////////
//										CS 342 FALL 2018										//
//////////////////////////////////////////////////////////////////////////////////////////////////
//		Author:			Erick Vaquero											     			//
//		Instructor:     Dr. John T. Bell														//
//		Lecture:		12:30 PM																//
//		Assignment:		HW4																		//
//		File Name:		Gear.java														        //
//////////////////////////////////////////////////////////////////////////////////////////////////
//		Description:																			//
//  This file is the Gear Class which extends from Artifacts. Characters are able to pick up	// 
//  the different types of gear which also affects their health and strength.					//
//  which the player can interact with within the game. The game creator can					//
//  make these items carry-able by the player. 													//
//////////////////////////////////////////////////////////////////////////////////////////////////

import java.util.Scanner;

public class Gear extends Artifact{
/////////////////////////////////////////// ATTRIBUTES ///////////////////////////////////////////	
	protected int maxHealthBoost;
	protected int strengthBoost;
	
//////////////////////////////////////////CONSTRUCTOR //////////////////////////////////////////	
	Gear(Scanner stream, double gameVersion){
		super(stream, gameVersion); //call the super class constructor
		maxHealthBoost = stream.nextInt();
		strengthBoost = stream.nextInt();
	}
	
////////////////////////////////////////MEMBER FUNCTIONS ////////////////////////////////////////	
//--------------------------------------------------------------------------------------------
	@Override
	public void display() {
		System.out.println("Gear: " + name +  description);

	}
//--------------------------------------------------------------------------------------------
	@Override
	public void print() {
		System.out.println("Gear Artifact: " + name + "\nID" + ID + "\n" + description);
		System.out.println("initial place: " + placeID);
		System.out.println("value: " + value);
		System.out.println("keyPattern: " + keyPattern);
		System.out.println("maxHealthBoost: " + maxHealthBoost);
		System.out.println("strengthBoost: " + strengthBoost);
	}
//--------------------------------------------------------------------------------------------
	public int maxHealthBoost() {
		return maxHealthBoost;
	}
//--------------------------------------------------------------------------------------------
	public int strengthBoost() {
		return strengthBoost;
	}
	
}// End of public class Gear extends Artifact
