//////////////////////////////////////////////////////////////////////////////////////////////////
//										CS 342 FALL 2018										//
//////////////////////////////////////////////////////////////////////////////////////////////////
//		Author:			Erick Vaquero											     			//
//		Instructor:     Dr. John T. Bell														//
//		Lecture:		12:30 PM																//
//		Assignment:		HW4																		//
//		File Name:		Weapon.java														        //
//////////////////////////////////////////////////////////////////////////////////////////////////
//		Description:																			//
//  This file is the Weapon Class which extends from Artifacts. Characters are able to pick up	// 
//  the this Weapon to defend themselves from other aggressive characters.						//
//////////////////////////////////////////////////////////////////////////////////////////////////

import java.util.Scanner;


public class Weapon extends Artifact{
	
/////////////////////////////////////////// ATTRIBUTES ///////////////////////////////////////////
	protected int baseAttack;
	
////////////////////////////////////////// CONSTRUCTORS //////////////////////////////////////////
	Weapon(Scanner stream, double gameVersion){
		super(stream, gameVersion); //call the super class constructor
		
		baseAttack = stream.nextInt();
	}
	
//////////////////////////////////////// MEMBER FUNCTIONS ////////////////////////////////////////
//--------------------------------------------------------------------------------------------
	public int baseAttack()
	{
		return baseAttack;
	}
	
	@Override
	public void display()
	{
		System.out.println("You attack with your " + name);
	}
	
	@Override
	public void print()
	{
		System.out.println("Weapon Artifact: " + name + "\nID" + ID + "\n" + description);
		System.out.println("initial place: " + placeID);
		System.out.println("value: " + value);
		System.out.println("keyPattern: " + keyPattern);
		System.out.println("baseAttack: " + baseAttack);

	}

}// End of public class Weapon extends Artifact
