//////////////////////////////////////////////////////////////////////////////////////////////////
//										CS 342 FALL 2018										//
//////////////////////////////////////////////////////////////////////////////////////////////////
//		Author:			Erick Vaquero											     			//
//		Instructor:     Dr. John T. Bell														//
//		Lecture:		12:30 PM																//
//		Assignment:		HW4																		//
//		File Name:		Lamp.java														        //
//////////////////////////////////////////////////////////////////////////////////////////////////
//		Description:																			//
//  This file is the Lamp Class which extends from Artifacts. Characters are able to pick up	// 
//  the this lamp to guide them through the dark places.										//
//////////////////////////////////////////////////////////////////////////////////////////////////

import java.util.Scanner;


public class Lamp extends Artifact{
//////////////////////////////////////////CONSTRUCTOR //////////////////////////////////////////
	Lamp(Scanner stream, double gameVersion){
		super(stream, gameVersion); //call the super class constructor
	}
	
////////////////////////////////////////MEMBER FUNCTIONS ////////////////////////////////////////	
//--------------------------------------------------------------------------------------------
	@Override
	public boolean isLamp(){
		return true;
	}
	
//--------------------------------------------------------------------------------------------
	@Override
	public void display(){
		System.out.println("The lantern floods the room with light");
	}
	
//--------------------------------------------------------------------------------------------
	@Override
	public void print()
	{		
		System.out.println("Lamp Artifact: " + name + "\nID" + ID + "\n" + description);
		System.out.println("initial place: " + placeID);
		System.out.println("value: " + value);
		System.out.println("keyPattern: " + keyPattern);
	}
	
}// End of class Lamp extends Artifact
