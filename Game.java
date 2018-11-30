
//////////////////////////////////////////////////////////////////////////////////////////////////
//										CS 342 FALL 2018										//
//////////////////////////////////////////////////////////////////////////////////////////////////
//		Author:			Jeremy Robles															//
//		Additions by: 	Erick Vaquero															//
//	    Instructor:     Dr. John T. Bell														//
//		Lecture:		12:30 PM																//
//		Assignment:		HW4																	    //
//		File Name:		Game.java																//
//////////////////////////////////////////////////////////////////////////////////////////////////
//		Description:																			//
//	An instance of this class is a container character instances. It is used to call			//
//	constructors for all different object types, as well as calling the play() function to		//
//	play the game.																				//
//////////////////////////////////////////////////////////////////////////////////////////////////

import java.util.Vector;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner; 
import java.io.FileWriter;

public class Game
{
/////////////////////////////////////////// ATTRIBUTES ///////////////////////////////////////////	
	//vector of Place objects

	private Vector<Character> characters;	//All characters in game
	private String gameName;				//Title of the game map
	private double gameVersion;				//Stores the version number of map

//////////////////////////////////////////CONSTRUCTORS //////////////////////////////////////////
	
	// A constructor for creating the Game object. The string passed is the name of the game.
	public Game(Scanner stream) throws IOException, FileNotFoundException
	{
		gameName = null;
		gameVersion = 0;
		characters = new Vector<Character>();

		int placeAmount = 0;
		int directionAmount = 0;
		int artifactAmount= 0;
		int charAmount = 0;
		int locationID = 0;
		
		FileWriter outputStream = new FileWriter("rawdata.gdf");
		
		
		while(stream.hasNextLine())
		{
			outputStream.write(CleanLineScanner.getCleanLine(stream) + "\n");
			stream.nextLine();
		}
		
		outputStream.close();
		stream.close();
		
		File rawData = new File("rawdata.gdf");
		stream = new Scanner(rawData);
		
		stream.next("(GDF)\\s*");
		gameVersion = stream.nextDouble();
		gameName = CleanLineScanner.getCleanLine(stream);
		
		// Get data for Place
		stream.next("\\s*(PLACES)\\s*");
		placeAmount = stream.nextInt();
		String placeType = "";
		
		for (int i = 0; i < placeAmount; i++)
		{
			
			//ADDED ***********************
			if (gameVersion >= 5.1) {
				placeType = stream.next().trim();
				
				//Create new artifact
				if (placeType.equalsIgnoreCase("GENERIC"))
					new Place(stream, gameVersion);
				else if (placeType.equalsIgnoreCase("HEALTH"))
					new HealthPlace(stream, gameVersion);
				else if (placeType.equalsIgnoreCase("SHOP"))
					new Shop(stream, gameVersion);
				else if (placeType.equalsIgnoreCase("DARK"))
					new DarkPlace(stream, gameVersion);
				else {
					System.out.println("ERROR: Place is not of a valid type");
					return;
				}
			} else { //Version 3.1 or 4.0
				new Place(stream, gameVersion);
			}
			//ADDED END***********************

		}// End of for (int i = 0; i < placeAmount; i++)
		
		// Get data for Direction  
		stream.next("\\s*(DIRECTIONS)\\s*");
		directionAmount = stream.nextInt();
		
		Direction tempDirection;
		
		for (int i = 0; i < directionAmount; i++)
		{
			tempDirection = new Direction(stream, gameVersion);

		}
		
		if (gameVersion >= 4.0) {
			// Get Character data
			stream.next("\\s*(CHARACTERS)\\s*");
			charAmount = stream.nextInt();
		
			int charType;
			Character tempChar = null;
			
			for (int i = 0; i < charAmount; i++)
			{
				charType= stream.nextInt();
				// Create new player character
				if (charType == 0)
					tempChar = new Player(stream, gameVersion);
				
				// Create new NPC
				else
					tempChar = new NPC(stream, gameVersion, charType);
				
				// Add the character to vector
				characters.addElement(tempChar);
				
				locationID = tempChar.placeID();
			
				// Add character to place (in a random place or its designated place
				if (locationID == 0)
				{
					locationID = Place.getRandPlaceID();
					tempChar.goToPlaceByID(locationID);
				}
				else
					Place.getPlaceByID(locationID).addCharacter(tempChar);
			}// End of for (int i = 0; i < charAmount; i++)
		}
		
		if (gameVersion >= 3.1) {
			stream.next("\\s*(ARTIFACTS)\\s*");
			artifactAmount = stream.nextInt();
			String artifactType;
			Artifact tempArtifact;
			
			for (int i = 0; i < artifactAmount; i++)
			{	
				if (gameVersion >= 5.1) {
					artifactType = stream.next().trim();
					
					//Create new artifact
					if (artifactType.equalsIgnoreCase("GENERIC"))
						tempArtifact = new Artifact(stream, gameVersion);
					else if (artifactType.equalsIgnoreCase("CONSUMABLE"))
						tempArtifact = new Consumable(stream, gameVersion);
					else if (artifactType.equalsIgnoreCase("WEAPON"))
						tempArtifact = new Weapon(stream, gameVersion);
					else if (artifactType.equalsIgnoreCase("GEAR"))
						tempArtifact = new Gear(stream, gameVersion);
					else if (artifactType.equalsIgnoreCase("LAMP"))
						tempArtifact = new Lamp(stream, gameVersion);
					else {
						System.out.println("ERROR: Artifact is not of a valid type");
						return;
					}
				} else { // Version 3.1 or 4.0
					tempArtifact = new Artifact(stream, gameVersion);
				}
				locationID = tempArtifact.getPlaceID();
				if (locationID < 0) {  // For a specific Character's possessions
					locationID *= -1;
					Character.getCharacterByID(locationID).addArtifact(tempArtifact);
				} else if (locationID == 0) { // Put in a random place
					Place.getRandomPlace().addArtifact(tempArtifact);
				} else if (locationID > 0){   // Put in a specific place
				 	Place.getPlaceByID(locationID).addArtifact(tempArtifact);
				} else { 
					System.out.println("ERROR: Artifact is not of a valid placeID");
					return;
				}				
			}// End loop for (int i = 0; i < artifactAmount; i++)
		}
		
	}// End constructor Game(String input)

	
////////////////////////////////////////MEMBER FUNCTIONS ////////////////////////////////////////
//--------------------------------------------------------------------------------------------
	// Prints out the Game information. This is for debugging and testing purposes only.
	public void print()
	{
		int characterSize = characters.size();
		
		System.out.println("Game Name: " + gameName);
		System.out.println("Version " + gameVersion);
		
		System.out.println("=>Characters in " + gameName + "<=");
		for (int i = 0; i < characterSize; i++)
			characters.get(i).print();

		Place.printAll();
		
	}// End member function void print()

//--------------------------------------------------------------------------------------------
	// Play the game. Runs an infinite loop displaying the current Place and processing user input
	// commands until the user enters QUIT or EXIT as described above. (The user can also exit
	// the game by navigating to a Place with ID of 1, e.g. by traveling west from the Entrance
	// Hall in the example shown above. This should probably be implemented as an Exit Place, 
	// and checked for whenever the player moves from place to place.)
	public void play()
	{
		
		int charSize = characters.size();
		Character currentCharacter = null;
		
		while (true)
		{
			for (int i = 0; i < charSize; i++)
			{
				currentCharacter = characters.get(i);
				
				if (currentCharacter.incapacitated() == false)
				{
					System.out.println(CleanLineScanner.divider1);
					System.out.println("==> " + currentCharacter.name
					+ "'s turn (HP " + currentCharacter.healthToString() + ") <==\n");
					currentCharacter.makeMove();
				}
			}
		}
	}// End member function void play()
	
}// End public class Game
