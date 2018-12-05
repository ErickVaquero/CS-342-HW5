//////////////////////////////////////////////////////////////////////////////////////////////////
//										CS 342 FALL 2018										//
//////////////////////////////////////////////////////////////////////////////////////////////////
//		Author:			Cecilia Avila											     			//
//		Instructor:     Dr. John T. Bell														//
//		Lecture:		12:30 PM																//
//		Assignment:		HW4																		//
//		File Name:		Shop.java														        //
//////////////////////////////////////////////////////////////////////////////////////////////////
//		Description:																			//
//  This is the Shop.java which extends from the Place class. 							        //
//  Characters can shop, sell, but cannot drop or pick up items from their 						//
//  superclass Artifact. That ArrayList is the merchandise.										// 
//////////////////////////////////////////////////////////////////////////////////////////////////

import java.util.Scanner;
import java.util.Vector;

public class Shop extends Place{
	
/////////////////////////////////////////// ATTRIBUTES ///////////////////////////////////////////
	// the shop has an infinite amount of consumables the player can buy
	private Vector<Consumable> consumables = new Vector<Consumable>();// arrayList wares of type Consumables
	
	
//////////////////////////////////////////CONSTRUCTORS //////////////////////////////////////////
	public Shop(Scanner s, double version) {
		super(s, version);
	}
	
////////////////////////////////////////MEMBER FUNCTIONS ////////////////////////////////////////
//--------------------------------------------------------------------------------------------
	// subtract character funds and add artifact/consumable to character inventory
	public void purchase(Character c, String item) {
		
		Artifact temp = null;
		
		// check through consumable and remove it from there
		for(int i = 0; i < consumables.size(); i++) {
			if(consumables.get(i).name().equalsIgnoreCase(item) == true) {
				temp = consumables.get(i);
				consumables.remove(i);
				break;
			}	
		}
		// check through artifactVector and remove it from there
		if(temp == null)
			for(int i = 0; i < artifactVector.size(); i++) {
				if(artifactVector.get(i).name().equalsIgnoreCase(item) == true) {
					temp = artifactVector.get(i);
					artifactVector.remove(i);
					break;
				}
			}
		// Item not found
		if(temp == null)
		{
			c.display("You couldn't purchase that item.");
			return;
		}
		// Character does not have enough money
		if (c.money() < temp.value()) {
			c.display("You can't afford that, you're too poor.");
			return;
		}
		// Character does not have enough space on their inventory
		if(c.inventorySpace() == c.currentInventorySpace()) {
			c.display("You have no space to carry that.");
			return;
		}
		
		c.dealMoney(temp.value() * -1);
		c.addArtifact(temp);
	}
	
//--------------------------------------------------------------------------------------------
	// add character funds and remove artifact from character inventory
	public void sell(Character c, String item) {
		
		Artifact temp = c.getArtifactByName(item);
		
		if (temp == null) {
			c.display("We couldn't sell that item.");
			return;
		}
		
		c.dealMoney(temp.value());
		c.removeArtifact(temp);
		
	}	
	
}// End of public class Shop extends Place
