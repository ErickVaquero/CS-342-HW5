//////////////////////////////////////////////////////////////////////////////////////////////////
//										CS 342 FALL 2018										//
//////////////////////////////////////////////////////////////////////////////////////////////////
//		Author:			Erick Vaquero											     			//
//		Instructor:     Dr. John T. Bell														//
//		Lecture:		12:30 PM																//
//		Assignment:		HW4																		//
//		File Name:		Consumable.java														    //
//////////////////////////////////////////////////////////////////////////////////////////////////
//		Description:																			//
//  This file is the Consumable.java class. It allows characters to get these different types   //
//  of consumables and can boost up the characters attributes.									//
//////////////////////////////////////////////////////////////////////////////////////////////////

import java.util.Scanner;


public class Consumable extends Artifact{
	
/////////////////////////////////////////// ATTRIBUTES ///////////////////////////////////////////
	
	//Amount that a consumable artifact will increase a stat of a character when consumed
	//stats include: money, strength, health, experience, inventory
	protected int moneyBoost;  
	protected int strengthBoost; 
	protected int healthBoost;
	protected int expBoost;
	protected int inventoryBoost;
	
	
////////////////////////////////////////// CONSTRUCTOR //////////////////////////////////////////
	Consumable(Scanner stream, double gameVersion){
		super(stream, gameVersion); //call the super class constructor
		
		moneyBoost = stream.nextInt();
		strengthBoost = stream.nextInt();
		healthBoost = stream.nextInt();
		expBoost = stream.nextInt();
		inventoryBoost = stream.nextInt();

	}
////////////////////////////////////////MEMBER FUNCTIONS ////////////////////////////////////////	
//--------------------------------------------------------------------------------------------

	// Functions getters for the protected attributes 
	public int moneyBoost(){	return moneyBoost;}
	public int strengthBoost()	{	return strengthBoost;}
	public int healthBoost(){	return healthBoost;}
	public int expBoost(){	return expBoost;}
	public int inventoryBoost() {	return inventoryBoost;}
	
//--------------------------------------------------------------------------------------------
	@Override
	public void display()
	{
		System.out.print("You eat the" + name);

	}
	
//--------------------------------------------------------------------------------------------
	@Override
	public void print()
	{
		System.out.println("Consumable Artifact: " + name + "\nID" + ID + "\n" + description);
		System.out.println("initial place: " + placeID);
		System.out.println("value: " + value);
		System.out.println("keyPattern: " + keyPattern);
		System.out.println("moneyBoost: " + moneyBoost + "stengthBoost: " + strengthBoost + "healthBoost: " + healthBoost);
		System.out.println("expBoost: " + expBoost + "inventoryBoost: " + inventoryBoost);

	}
	
}// End of Consumable.java
