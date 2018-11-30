//////////////////////////////////////////////////////////////////////////////////////////////////
//										CS 342 FALL 2018										//
//////////////////////////////////////////////////////////////////////////////////////////////////
//		Author:			Cecilia Avila											     			//
//		Instructor:     Dr. John T. Bell														//
//		Lecture:		12:30 PM																//
//		Assignment:		HW4																		//
//		File Name:		Direction.java														    //
//////////////////////////////////////////////////////////////////////////////////////////////////
//		Description:																			//
//  Player can pickup and drop artifacts, open doors with keys, and travel to different rooms. 	//
//  This class implements an enumerated type class for the different directions. 				//
//////////////////////////////////////////////////////////////////////////////////////////////////

import java.util.Scanner;

public class Direction {
	
	// Private data members 
	private int dirID;
	private Place dirFrom;
	private Place dirTo;
	private String goDir;
	private boolean locked;
	private int lockPattern;
	private dirType direction;	//Contains direction N/S/W/E/U/D/etc.
	
	// Enumerated class meant for the different directions
	private enum dirType {
		NONE ("None","None"),
		N ("North","N"),
		S ("South", "S"),
		E ("East", "E"),
		W ("West", "W"),
		U ("Up","U"),
		D ("Down","D"),
		NE ("Northeast","NE"),
		NW ("Northwest","NW"),
		SE ("Southeast", "SE"),
		SW ("Southwest", "SW"),
		NNE ("North-Northeast", "NNE"),
		NNW ("North-Northwest", "NNW"),
		ENE ("East-Northeast","ENE"),
		WNW ("West-Northwest","WNW"),
		ESE ("East-Southeast", "ESE"),
		WSW ("West-Southwest","WSW"),
		SSE ("South-Southeast","SSE"),
		SSW ("South-Southwest","SSW"),;
				
				
		private final String txt;
		private final String abbrev;
				
		private dirType(String text, String abbreviation){
			txt = text;
			abbrev = abbreviation;
		}
		public String toString() {
			// returns the text field
			return txt;
		}
		public boolean match(String s) {
			if (s.equalsIgnoreCase(txt.toUpperCase()) || s.equalsIgnoreCase(abbrev.toUpperCase())) {
				return true;
			}
			else{
				return false;
			}
		}
	}
	
//////////////////////////////////////////CONSTRUCTOR //////////////////////////////////////////
//-------------------------------------------------------------------------------------------			
	Direction(Scanner s, double version) {
		String line = CleanLineScanner.getCleanLine(s);
		Scanner lineScanner = new Scanner(line);
		dirID = lineScanner.nextInt();
		
		int placeID = lineScanner.nextInt();
		dirFrom = Place.getPlaceByID(placeID);
		
		String dir = lineScanner.next();
		direction = dirType.NONE;
		
		// Make sure Directions in file are valid
		for(dirType t : dirType.values()) {
			if(t.match(dir)) {
				direction = t;
				break;
			}
		}
		
		// Doors are initially unlocked
		locked = false;
		int destID = lineScanner.nextInt();
		if(destID <= 0) {
			locked = true;
			destID = -destID;
		}
		
		dirTo = Place.getPlaceByID(destID);	
		
		lockPattern = lineScanner.nextInt();
		// Set lockPattern to 0 if it negative in file
		if (lockPattern < 0) { 
			lockPattern = 0;
		}
	
		dirFrom.addDirection(this);	// Add direction to its designated place
		
		
	}
		

	// Constructor w/o scanner
	public Direction(int ID, Place from, Place to, String dir, int lockPattern) {
		dirID = ID;
		dirFrom = Place.getPlaceByID(dirID);
		dirTo = to;
		goDir = dir;
		locked = false;		// Doors by default are unlocked
		this.lockPattern = lockPattern;
	}	
	
////////////////////////////////////////MEMBER FUNCTIONS ////////////////////////////////////////
//-------------------------------------------------------------------------------------------			

	public boolean match (String s) {
		if (direction.match(s))
			return true;
		return false;
		}
			
//-------------------------------------------------------------------------------------------
		// Locks the Place
		public void lock() {
			locked = true;
		}
			
//-------------------------------------------------------------------------------------------	
		public boolean useKey(Artifact item)
		{
			//Patterns are match
			if (lockPattern != 0 && item.getKeyPattern() == lockPattern)
			{
				//If the direction is locked, the key will unlock it
				if (isLocked())
				{
					locked = false;
					System.out.println("The door to the " + direction.txt.toLowerCase()
					+ " is unlocked with the " + item.name().toLowerCase() + ".");
				}
				
				//If the direction is unlocked, the key will lock it
				else
				{
					lock();
					System.out.println("The door to the " + direction.txt.toLowerCase()
					+ " is locked with the " + item.name().toLowerCase());
				}
				
				return true;
			}//end if (item.tryKey() == lockPattern)
		
			return false;	
		}//end member method void useKey(Artifact item)
			
			
//-------------------------------------------------------------------------------------------
		// Unlocks the Place
		public boolean isLocked() {
			return locked;
		}
			
//-------------------------------------------------------------------------------------------
		// Returns the 'to' Place if the Direction is unlocked
		public Place follow() {
			// If direction is unlocked return the Place they are going to
			if(locked == true) {
				System.out.println("\n Sorry, that place is locked. \n");
				return dirFrom;
			}
			// If direction is locked, return the place it is coming from
			else {
				return dirTo;
			}
		}
		
//-------------------------------------------------------------------------------------------				
		// Prints Direction information for debugging purposes
		public void print(){
			System.out.println("direction id: "+ dirID);
			System.out.println("direction from: " + dirFrom.name());
			System.out.println("direction to: " + dirTo.name());
			System.out.println("direction goDir: " + goDir);
			System.out.println("direction locked: " + locked);
				
			// Will now print out the lock pattern
			System.out.println("Lock pattern: " + lockPattern);
				
		}
		
//-------------------------------------------------------------------------------------------		
		public int getDirFromID()
		{
			return dirFrom.getID();
		}
		
//--------------------------------------------------------------------------------------------
		// Returns the string text from DirType
		public String directionToString()
		{
			return direction.toString();
		}// End member method public String directionToString()
			
}// End of public class Direction

