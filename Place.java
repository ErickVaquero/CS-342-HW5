//////////////////////////////////////////////////////////////////////////////////////////////////
//										CS 342 FALL 2018										//
//////////////////////////////////////////////////////////////////////////////////////////////////
//		Author:			Cecilia Avila											     			//
//		Instructor:     Dr. John T. Bell														//
//		Lecture:		12:30 PM																//
//		Assignment:		HW4																		//
//		File Name:		Place.java														        //
//////////////////////////////////////////////////////////////////////////////////////////////////
//		Description:																			//
//  This is the Place.java class. The place class will keep track of a collection of			//
//  Characters, Places, Artifacts, and Directions.												// 
//////////////////////////////////////////////////////////////////////////////////////////////////

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;


public class Place {
	
/////////////////////////////////////////// ATTRIBUTES ///////////////////////////////////////////
	private int id;
	private String pName;
	private String pDescription;
	//private Integer Exit = 0;
	
	// Keeps track of which characters are in each place, must update when character moves.
	protected Vector<Character>charVector = new Vector <Character>();
		
	// Requires static collection of all known places be kept within the Place class
	private static HashMap<Integer,Place> placesMap = new HashMap<>(); 
	
	// Creates a vector of type Direction in order to store valid directions
	private Vector<Direction>vectorDirection = new Vector<Direction>();
		
	// Creates a vector of type Place in order to store artifacts
	protected Vector<Artifact>artifactVector = new Vector<Artifact>(); 
	
		
//////////////////////////////////////////CONSTRUCTORS //////////////////////////////////////////
	public Place(Scanner s, double version) {
		
		String line = CleanLineScanner.getCleanLine(s);
		Scanner lineScanner = new Scanner(line);
		id = lineScanner.nextInt();
		lineScanner.skip("[ \t]");
		pName = lineScanner.nextLine();
		
		line = CleanLineScanner.getCleanLine(s);
		lineScanner = new Scanner(line);
		int numLines = lineScanner.nextInt();
		pDescription = "";
		for (int i = 0; i < numLines ; i++) {
			pDescription += CleanLineScanner.getCleanLine(s) + "\n";
		}
		
		// Debugging purposes
		/*System.out.println("INSIDE PLACE CLASS:");
		System.out.println("Place name: " + pName);
		System.out.println("Number of lines " + numLines);
		System.out.println("Description: " + pDescription);*/
		
		placesMap.put(this.id, this);

	}
	
//--------------------------------------------------------------------------------------------
	// Constructor w/o Scanner
	public Place(int ID, String Name, String Description) {
		id = ID;
		pName = Name;
		pDescription = Description;
	}
	
////////////////////////////////////////MEMBER FUNCTIONS ////////////////////////////////////////
//--------------------------------------------------------------------------------------------
	// Adds an artifact to this Place's collection of artifacts
	public void addArtifact(Artifact t ) {
		artifactVector.add(t);
	}
		
		
//--------------------------------------------------------------------------------------------
	static Place getPlaceByID(int t ) {
		// Returns the place with the current ID number or null	
		return placesMap.get(t);
	}
		
//--------------------------------------------------------------------------------------------	
	public void useKey(Artifact t ) {
		// Passes the artifact to the useKey() method of all Directions present in this Place 
		for (Direction d: vectorDirection) {
			d.useKey(t);
		}	
	}
	
	
//--------------------------------------------------------------------------------------------
	// Personal method: Checks to see if the artifact the user entered existed in the Place
	public Boolean valid(String m) {
		for(int i = 0; i < artifactVector.size(); i++) {
			if (artifactVector.get(i).name().equals(m)) {
				if(artifactVector.get(i).value() < 120) {
					System.out.println("Artifact valid");
					return true;
				}
			}
		}
		return false;
	}
		
//--------------------------------------------------------------------------------------------
	// Personal Method: Remove artifact and return it
	public Artifact removeArtifactByName( String s) {
		for (Artifact a : artifactVector) {
			if(a.match(s)) {
				artifactVector.remove(a);
				return a;
			}
		}
		return null;
	}
		
//--------------------------------------------------------------------------------------------
	// Adds character to the collection of characters
	public void addCharacter(Character c) {
		charVector.add(c);
	}

//--------------------------------------------------------------------------------------------
	public void removeCharacter(Character c) {
		charVector.remove(c);
		
	}
	
//--------------------------------------------------------------------------------------------
	// Returns the name
	public String name() {
		return pName;
	}
		
	//--------------------------------------------------------------------------------------------
	// Returns the description of the Place
	public String description() {
		return pDescription;
	}
		
	//--------------------------------------------------------------------------------------------
	// Adds direction to vector
	public void addDirection (Direction d){
		vectorDirection.add(d);
	}

	//--------------------------------------------------------------------------------------------
	// Returns the place arrived if it's a valid direction otherwise it returns itself
	public Place followDirection(String direction ) {
		int i;
		int totalDirections = vectorDirection.size();
		// Loop through vector to compare directions
		for(i = 0; i < totalDirections ; i++) {
			// Check for a valid direction
			if (vectorDirection.get(i).match(direction)) {
				return (vectorDirection.get(i).follow());	
			}
		}
		System.out.println("Invalid Direction " + this.name()); // used for testing
			
		// Did not find a valid direction, return itself
		return this; 
			
	}
	
	
	//--------------------------------------------------------------------------------------------
	// Prints out Directions, Artifacts, and Characters present in each place.
	public static void printAll() {
		for (int i = 0; i < placesMap.size(); i++) {
			System.out.println("Place : " + placesMap.get(i).name());
			System.out.println("This place has the following directions: \n" );
			for( int j = 0 ; j < placesMap.get(i).vectorDirection.size(); j++) {
				System.out.println(placesMap.get(i).vectorDirection.get(j) + ", ");
			}
			System.out.println("This place has the following artifacts: \n" );
			for( int k = 0 ; k < placesMap.get(i).artifactVector.size(); k++) {
				System.out.println(placesMap.get(i).artifactVector.get(k) + ", ");
			}
			System.out.println("This place has the following characters: \n" );
			for( int l = 0 ; l < placesMap.get(i).charVector.size(); l++) {
				System.out.println(placesMap.get(i).charVector.get(l) + ", ");
			}		
		}
	}
		
	//--------------------------------------------------------------------------------------------
	// Prints out Place ID, Name, and Place Description, and artifacts for debugging purposes	
	public void print() {
		System.out.println("Place id: " + id);
		System.out.println("Place name: " + pName);
		System.out.println("Place Description: /n" + pDescription );
		
		// loop through directions
		System.out.println("Directions: ");
		for (int i = 0 ; i < vectorDirection.size(); i++) {
			System.out.println( vectorDirection.get(i) + ", ");
		}		
		// loop through artifacts
		System.out.println("Artifacts: ");
		for (int i = 0; i < artifactVector.size(); i++) {
			System.out.println(artifactVector.get(i) + ", ");
		}
		
	}	
		
//--------------------------------------------------------------------------------------------
	public void display() {
		System.out.println("This is the  " + pName);
		System.out.println(pDescription);
		System.getProperty("line.separator");
		System.out.println("From here you can go: ");
		System.out.print("| ");
		for (int i = 0 ; i < vectorDirection.size(); i++)
		{
			System.out.print(vectorDirection.get(i).directionToString() + " | ");
		}
		System.out.println();
		System.lineSeparator();
		System.out.println("The following artifacts are in this place: ");
		for (int i = 0; i < artifactVector.size(); i++) {
			System.out.println("   " + artifactVector.get(i).name());
		}
		System.getProperty("line.separator");
	}
	
//--------------------------------------------------------------------------------------------
	public void look() {
		System.out.println("You are in the " + pName);
		System.out.println(pDescription);
		System.getProperty("line.separator");
		System.out.println();
		System.lineSeparator();
		System.out.println("The following artifacts are in this place: ");
		for (int i = 0; i < artifactVector.size(); i++) {
			System.out.println("   " + artifactVector.get(i).name());
		}
		System.getProperty("line.separator");
		
		System.out.println("The following people are in this place: ");
		for (int i = 0; i < charVector.size(); i++)
		{
			charVector.get(i).display();
		}
	}

//--------------------------------------------------------------------------------------------
	// Returns the name of the first item, used in AI.java
	public String firstItemToString()
	{
		String text = "";
		
		if (artifactVector.size() == 0)
			return text;
		
		return artifactVector.get(0).name();
	}
	
	// Returns the first unlocked direction. Use in AI.java
	public String firstUnlockedDirection()
	{
		String text = "";
		int dSize = vectorDirection.size();
		
		if (dSize == 0)
			return text;
		
		for (int i = 0; i < dSize; i++)
			if (!vectorDirection.get(i).isLocked())
				return vectorDirection.get(i).directionToString();
		
		return text;
	}

//--------------------------------------------------------------------------------------------
	// Returns the ID of the instance
	public int getID()
	{
		return id;
	}// End member function int getID()
	
//--------------------------------------------------------------------------------------------
		// Returns a random place ID from the Place hashmap
		static public int getRandPlaceID()
		{
			Random rand = new Random();
			int randNum = 0;
			
			//NOTE: Not a very good randomizer, but will do for now
			while (true)
			{
				for (Map.Entry<Integer, Place> entry : placesMap.entrySet())
				{
					randNum = rand.nextInt(100) + 1;
					
					if (randNum > 90 && entry.getValue().getID() > 1)
					return entry.getValue().getID();
				}// End for loop
			}// End while loop
		}// End static public int getRandPlaceID()
		
//--------------------------------------------------------------------------------------------
		// Uses an artifact in the room. It will apply the artifact to all directions possible,
		// since a key may be used multiple times in one Place
		public void useItem(Artifact item)
		{
			int directionSize = vectorDirection.size();
			boolean hasUse = false; //Whether an item has a use
					//it may have more than one use in a place

			// Apply artifact in all directions
			for (int i = 0; i < directionSize; i++)
			{
				// Check to see if artifact has at least one purpose
				if (vectorDirection.get(i).useKey(item))
					hasUse = true;
				// vectorDirection.get(i).useKey(item);
			}
			
			// Item has no use, so display message saying so
			if (!hasUse)
			System.out.println("*It looks like there are no ways to use the " +
			item.name().toLowerCase() + ".");
		}// End method public void useItem(Artifact item)
		
//--------------------------------------------------------------------------------------------
		//Added by Erick Vaquero
		public static Place getRandomPlace() {
			
			Place request = null;
			int min = 1;
			int max = placesMap.size();
			int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
			for (Place value : placesMap.values()) {
				if (min == randomNum) {
					request = value;
					break;
				}
				min++;
			}
			
			return request;
		}
		
//--------------------------------------------------------------------------------------------
		//Added by Jeremy Robles. Checks if character exists in this place
		public boolean characterExists(Character character)
		{
			int charVectorSize = charVector.size();

			for (int i = 0; i < charVectorSize; i++)
				if (character == charVector.get(i))
					return true;
			
			return false;
		}
		
//--------------------------------------------------------------------------------------------
		//Added by Jeremy Robles. Finds random target to attack
		public Character getRandTarget(Character attacker)
		{
			// Amount of tries to select target
			int targetTry = 10;
			// Try to find new target
			if (characterExists(attacker) == false)
				return null;
	
			 int x = ThreadLocalRandom.current().nextInt(0, charVector.size());
			 Character target = charVector.get(x);
			 
			 // Make sure attacker's target is not itself or a dead target
			 while ((attacker == target || target.incapacitated())&& targetTry > 0)
			 {
				 x = ThreadLocalRandom.current().nextInt(0, charVector.size());
				 target = charVector.get(x);
				 targetTry--;
			 }
			 
			 if (targetTry == 0)
				 return null;

			 
			 return target;
		}
		
		public int charVectorSize()
		{
			return charVector.size();
		}
		
		public int artifactVectorSize()
		{
			return artifactVector.size();
		}
		
		public Character charVectorAt(int i)
		{
			if (i < 0 || i >= charVectorSize())
				return null;
			return charVector.get(i);
		}
		
		public Artifact artifactVectorAt(int i)
		{
			if (i < 0 || i >= charVectorSize())
				return null;
			return artifactVector.get(i);
		}
}// End of public Place class



