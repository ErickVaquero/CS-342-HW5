//////////////////////////////////////////////////////////////////////////////////////////////////
//										CS 342 FALL 2018										//
//////////////////////////////////////////////////////////////////////////////////////////////////
//		Author:			Erick Vaquero											     			//
//		Instructor:     Dr. John T. Bell														//
//		Lecture:		12:30 PM																//
//		Assignment:		HW4																		//
//		File Name:		Artifact.java														    //
//////////////////////////////////////////////////////////////////////////////////////////////////
//		Description:																			//
//  This file is the Artifact Class. The artifact class acts as the "things"	                //
//  which the player can interact with within the game. The game creator can					//
//  make these items carry-able by the player. The Artifact class can also act as a key			//
//  which unlocks or locks doors/paths between two places										//
//////////////////////////////////////////////////////////////////////////////////////////////////

import java.util.HashMap;
import java.util.Scanner;

public class Artifact {
	protected int ID; 		  //The ID of the artifact
	protected int placeID;	  //Initial place ID of artifact
	protected String name;    //The name of the artifact
	protected String description; // Description of the artifact
	protected int value;      // The value/worth of an artifact
	protected int mobility;   // The mobility, meaning if it can be picked up or not. Could be thought of as its weight
	protected int keyPattern; // Assigning it a non-0 value makes it a key and the number is its "shape"
	
	private static HashMap<Integer, Artifact> artifactMap; // All the Artifacts in this Place
	 
	 static 
	 {
		 artifactMap = new HashMap<Integer, Artifact>();
	 }

//////////////////////////////////////////CONSTRUCTOR //////////////////////////////////////////

		// Constructor for an Artifact instance. It uses an input stream passed by the Game
		// class constructor.
		public Artifact(Scanner stream, double version)
		{
			placeID = stream.nextInt();
			ID = stream.nextInt();
			value = stream.nextInt();
			mobility = stream.nextInt();
			keyPattern = stream.nextInt();
			name = CleanLineScanner.getCleanLine(stream);
			description = "";
			
			int nDesc = 0;			//# of strings for description
			nDesc = stream.nextInt();
			for (int i = 0; i < nDesc; i++)
				description += "  " + CleanLineScanner.getCleanLine(stream) + "\n";
		}// End constructor public Artifact(Scanner stream)
	 
		
////////////////////////////////////////MEMBER FUNCTIONS ////////////////////////////////////////
//-------------------------------------------------------------------------------------------
	 public static void addArtifactToMap(int IDKey, Artifact newArtifact) {
		 Integer toInt = new Integer(IDKey);
		 artifactMap.put(toInt, newArtifact);
	 }
	 
//-------------------------------------------------------------------------------------------
	 public static Artifact getArtifactByID(int ID) {
		 Integer toInt = new Integer(ID);
		 Artifact request = artifactMap.get(toInt);		
		 return request;
	 }
	 
//-------------------------------------------------------------------------------------------
	 public int getID() {
		 return this.ID;
	 }

//-------------------------------------------------------------------------------------------
	//The proper constructor of Artifact which defines all its variables
	Artifact(int inID, String inName, String inDesc, int inValue, int inMobility, int inKeyPattern){
		this.ID = inID;
		this.name = inName;
		this.description = inDesc;
		this.value = inValue;
		this.mobility = inMobility;
		this.keyPattern = inKeyPattern;
	}
	
//-------------------------------------------------------------------------------------------
	//returns the pattern of the key artifact (used to check if it matches with a "door"
	public int getKeyPattern()
	{	
		return this.keyPattern;
	}
	
//-------------------------------------------------------------------------------------------
	//Prints the description of the artifact
	public void printDescription() 
	{
		System.out.println(name + ":\n" + description);
	}
	
//-------------------------------------------------------------------------------------------
	// Returns the name of this Artifact instance
	public String name()
	{
		return name;
	}
	
//--------------------------------------------------------------------------------------------
	// Returns the Place ID of original place it was found.
	public int getPlaceID()
	{
		return placeID;
	}
	
//-------------------------------------------------------------------------------------------- 
	// Use
	public void use(Character character, Place place)
	{
		// If there is a match
		if (character.hasArtifact(this))
		{
			// Apply item in the current Place
			place.useItem(this);
			return;
		}// End conditional if 
	System.out.println("*You look in your pockets to use the " + name + ", but couldn't find it.");
	}
	
//--------------------------------------------------------------------------------------------
	// Checks to see if the string input is a match to this Artifact's name.
	public boolean match(String input)
	{
		if (name.equalsIgnoreCase(input))
			return true;
		return false;
	}// End member method public boolean matches(String input)	
	
//-------------------------------------------------------------------------------------------
	public int value()
	{
		return value;
	}
	
//-------------------------------------------------------------------------------------------
	public boolean isLamp()
	{
		return false;
	}
	
//-------------------------------------------------------------------------------------------
	public void display()
	{
		System.out.print(name + "\n" + description);
	}
	
//-------------------------------------------------------------------------------------------
	public void print()
	{		
		System.out.println("Generic Artifact: " + name + "\nID" + ID + "\n" + description);
		System.out.println("initial place: " + placeID);
		System.out.println("value: " + value);
		System.out.println("keyPattern: " + keyPattern);
		
	}
	
	public String summary()
	{
		return description + "\n" + "Value: " + value + "G\n" + "Weight: " + mobility;
	}

}// End of public class Artifact
