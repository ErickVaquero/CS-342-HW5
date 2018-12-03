
//////////////////////////////////////////////////////////////////////////////////////////////////
//										CS 342 FALL 2018										//
//////////////////////////////////////////////////////////////////////////////////////////////////
//		Author:			Jeremy Robles															//
//		Instructor:     Dr. John T. Bell														//
//		Lecture:		12:30 PM																//
//		Assignment:		HW4																		//
//		File Name:		Character.java															//
//////////////////////////////////////////////////////////////////////////////////////////////////
//		Description:																			//
//	These characters are the game "pieces" in which they are allowed to interact with the map	//
//	and objects within the map. They can either be player characters or NPCs.					//
//////////////////////////////////////////////////////////////////////////////////////////////////

import java.util.Scanner;
import java.util.Vector;
import java.util.HashMap;
import java.util.Map; 

public abstract class Character
{
/////////////////////////////////////////// ATTRIBUTES ///////////////////////////////////////////
	protected int ID;
	protected String name;
	protected String description;

	protected Place here;					  // Stores the current place
	protected int placeID;					  // ID of current place
	protected Vector<Artifact> collection;	  // Stores the items in character's possession
	protected int money;					  // AMount of money the character is holding
	protected int maxHealth;				  // Max health of character. Can be adjusted by equipped gear
	protected int currentHealth;		      // Amount of health character currently has
	protected int strength;					  // Used as attack multiplier and ability to pick up items
	protected int exp;				          // Current experience the character has. Increases by defeating
	protected int level;			          // Level of character. Increasing level increases stats
	protected int inventorySpace;	          // This must be >= size of collection vector
	protected Gear equippedGear;	          // Currently equipped gear
	protected Weapon equippedWeapon;	      // Currently equipped weapon
	protected boolean incapacitated;	      // Whether the character can still move or not.
	protected IO io;
	
	protected static HashMap<Integer, Character>	// Mapping of all Place instances sorted by ID
	 hmap = new HashMap<Integer, Character>();
	
	protected DecisionMaker nextMove; 
	

//////////////////////////////////////////CONSTRUCTORS //////////////////////////////////////////
//--------------------------------------------------------------------------------------------
	// Creates a character from data in a data file.
	Character(Scanner stream, double version)
	{
		collection = new Vector<Artifact>();
		equippedGear = null;
		equippedWeapon = null;
		io = new IO();
		
		placeID = stream.nextInt();

		here = Place.getPlaceByID(placeID);

		ID = stream.nextInt();

		name = CleanLineScanner.getCleanLine(stream);
		level = stream.nextInt();
		exp =stream.nextInt();
		maxHealth = stream.nextInt();
		currentHealth = maxHealth;
		strength = stream.nextInt();
		money = stream.nextInt();
		inventorySpace = stream.nextInt();
		
		description = "";
		

		int ndesc = stream.nextInt(); // Number of description lines to follow, an integer > 0
		
		for (int i = 0; i < ndesc; i++)
			description += "*" + CleanLineScanner.getCleanLine(stream) + "\n";
		
		incapacitated = false;
		//Insert this instance into the hash map
		hmap.put(ID,this);
	}//end constructor Character(Scanner stream, int version)
	
//--------------------------------------------------------------------------------------------
	// Creates a character from the passed-in data. It is used when no player character(s)
	// have been created from data in a data file.
	Character(int id, String n, String desc)
	{
		ID = id;
		name = n;
		description = desc;
		incapacitated = false;
		
		level = 1;
		exp = 100;
		maxHealth = 100;
		currentHealth = 100;
		strength = 1;
		money = 100;
		inventorySpace = 5;
		
		//Insert this instance into the hashmap
		hmap.put(ID,this);
	}//end constructor Character(int id, String n, String desc)

	
////////////////////////////////////////MEMBER FUNCTIONS ////////////////////////////////////////	
//--------------------------------------------------------------------------------------------
	// The way a character makes its move is dependent on what type of character it is
	abstract public void makeMove();
	
//--------------------------------------------------------------------------------------------
	// Returns a Character instance by using their IDs
	public static Character getCharacterByID(int searchVal)
	{
		return hmap.get(searchVal);
	}//end public static Character getCharacterByID(int searchVal)
	
//--------------------------------------------------------------------------------------------
	// Checks to see if the character has an item in their possession
	public boolean hasArtifact(Artifact item)
	{
		int collectionSize = collection.size();
		
		for (int i = 0; i < collectionSize; i++)
			if (item.equals(collection.get(i)))
				return true;
		
		return false;
	}//end 	public boolean hasArtifact(Artifact item)

//--------------------------------------------------------------------------------------------
	// Prints out all of the Character information. This is for debugging and testing purposes.
	public void print()
	{
		//Print out type of character and description
		io.display("Character: " + name + "\n" + description);
		io.display("Current location: " + here.name() + "(" + placeID + ")");
		io.display(name + "'s Inventory:");
		//Print out inventory
		showInventory();
	}//end member method public void print()
	
//--------------------------------------------------------------------------------------------
	// Displays the character's simplified info
	public void display()
	{
		io.display(name + "\n" + description);
	}//end member method public void display()
	
	
//--------------------------------------------------------------------------------------------
	// Returns the ID of the place this character is currently in
	public int placeID()
	{
		return placeID;
	}
	
//--------------------------------------------------------------------------------------------
	// Add an artifact to this character's possession
	public void addArtifact(Artifact item)
	{
		collection.addElement(item);
	}
	
//--------------------------------------------------------------------------------------------
	// Removes the artifact from this character's possessions
	public void removeArtifact(Artifact item)
	{
		Artifact temp = item;
		here.addArtifact(temp);
		
		
		collection.remove(item);
	}
	
//--------------------------------------------------------------------------------------------
	// Character changes its current place to the destination place
	public void goToPlace(Place destination)
	{
		here = destination;
		placeID = here.getID();
	}
	
//--------------------------------------------------------------------------------------------
	// Character changes its current place by searching a Place ID
	public void goToPlaceByID(int id)
	{
		Place temp = Place.getPlaceByID(id);
		if (temp == null)
			return;
		here = Place.getPlaceByID(id);
		placeID = id;
		
	}
	
//--------------------------------------------------------------------------------------------
	// Shows the character's inventory
	public void showInventory()
	{
		/*System.out.println("Stats: ");
		System.out.println("Level: " + level + ", EXP: " + exp + ", " + money + "G");
		System.out.println("HP: " + currentHealth + "/" + maxHealth + ", Strength: " + strength);
		System.out.println("Capacity: " + currentInventorySpace() + "/" + inventorySpace);
		
		System.out.println("Items: ");
		int collectionSize = collection.size();
		if (collectionSize == 0)
			System.out.println("Nothing\n");
		else
			for (int i = 0; i < collectionSize; i++)
				collection.get(i).printDescription();*/
		
		io.display("Stats: ");
		io.display("Level: " + level + ", EXP: " + exp + ", " + money + "G");
		io.display("HP: " + currentHealth + "/" + maxHealth + ", Strength: " + strength);
		io.display("Capacity: " + currentInventorySpace() + "/" + inventorySpace);
		
		io.display("Items: ");
		int collectionSize = collection.size();
		if (collectionSize == 0)
			io.display("Nothing\n");
		else
			for (int i = 0; i < collectionSize; i++)
				collection.get(i).printDescription();
	}
	
//--------------------------------------------------------------------------------------------
	// Returns an instance of a character's artifact if it matches their inventory
	public Artifact getArtifactByName(String string)
	{
		int collectionSize = collection.size();
		for (int i = 0; i < collectionSize; i++)
		{
			if (collection.get(i).match(string))
			{
				return collection.get(i);
			}
		}
		
		return null;
	}
	
//--------------------------------------------------------------------------------------------
	// Returns the name of the character
	public String name()
	{
		return name;
	}
	
//--------------------------------------------------------------------------------------------
	// Returns the name of the first artifact in inventory. This is used in AI.java
	public String firstItemToString()
	{
		String text = "";
		
		if (collection.size() == 0)
			return text;
		
		return collection.get(0).name();
	}
	
	public int money() { return money;}
	public int maxHealth() { return maxHealth;}
	public int currentHealth() { return currentHealth;} 
	public int strength() { return strength;}
	public int exp() { return exp; }
	public int level() { return level; }
	public int inventorySpace() { return inventorySpace; }
	
//--------------------------------------------------------------------------------------------
	public void attack(Character target)
	{
		int damage = strength;
		
		if (equippedWeapon != null)
			damage*= equippedWeapon.baseAttack();
		
		io.display(name + " attacks " + target.name() +
		" for " + damage + " damage!");
		//Attack target and receive experience points
		addEXP(target.receiveAttack(-1*damage));


	}
	
//--------------------------------------------------------------------------------------------
	//Drop all items when incapacitated
	private void dropAllItems()
	{
		Artifact temp = null;
		
		int collectionSize = collection.size();
		
		for (int i = 0; i < collectionSize; i++)
		{
			temp = collection.get(i);
			here.addArtifact(temp);
		}
		
		//Remove equipped items if necessary
		if (equippedGear != null)
		{
			here.addArtifact(equippedGear);
			equippedGear = null;
		}
		
		if (equippedWeapon != null)
		{
			here.addArtifact(equippedWeapon);
			equippedGear = null;
		}
		
		//Remove all artifacts from collection.
		collection.removeAllElements();
	}
//--------------------------------------------------------------------------------------------	
	// Update the characters health after being attacked
	public int receiveAttack(int damage)
	{
		dealHealth(damage);
		
		if (damage > 0)
		{
			if (currentHealth > maxHealth)
				currentHealth = maxHealth;
			io.display(name + " healed by " + damage + "!");
			io.display("Health: " + currentHealth + "/" + maxHealth);
			
			return damage/15;
		}
		
		System.out.println(name + " is at " + healthToString() + " HP!");
		
		// Character becomes incapacitated if they lose all their health
		if (currentHealth <= 0)
		{
			// Experience bonus is higher if they are incapacitated
			return exp / 100 + level;
		}
		
		return exp / 100;
	}
	
//--------------------------------------------------------------------------------------------	
	// Update characters inventory when they sell an item to the shop
	public void giveItem(Character target, Artifact item)
	{
		if (item == null)
		{
			io.display("Could not give the item.");
			return;
		}
		
		// Character cannot give item if they don't have it in the first place
		if (hasArtifact(item) == false)
		{
			io.display("You don't even have the " + item.name() + " to give!");
			return;
		}
		
		// Complete transaction
		Artifact tempArtifact = item;
		this.removeArtifact(item);
		target.addArtifact(tempArtifact);
		io.display(name + " gives the " + tempArtifact.name() +
				" to " + target.name());
	}// End public void giveItem(Character target, Artifact item)
	
//--------------------------------------------------------------------------------------------
	// Characters can obtain outfits 
	public void equipGear(Gear outfit)
	{
		// Don't equip if outfit doesn't exist, or character is already wearing something
		if (outfit == null || equippedGear != null)
		{
			io.display("Could not equip the item.");
			return;
		}
		
		// Character cannot equip item if they don't have it in the first place
		if (hasArtifact(outfit) == false)
		{
			io.display("You don't even have the " + outfit.name() + " to equip!");
			return;
		}
		
		// Store item into equippedGear slot and remove it from inventory
		equippedGear = outfit;
		collection.removeElement(outfit);
		
		// Apply bonuses
		maxHealth += equippedGear.maxHealthBoost();
		strength += equippedGear.strengthBoost();
		io.display(name + " is now wearing " + outfit.name() + ".");

	}
	
//--------------------------------------------------------------------------------------------	
	public void unequipGear()
	{
		if (equippedGear == null)
		{
			io.display("It's dangerous to go ahead naked, so you keep your clothes on.");
			return;
		}
		
		// Subtract bonuses
		maxHealth -= equippedGear.maxHealthBoost();
		strength -= equippedGear.strengthBoost();
		
		// Take off gear from equippedGear slot and put it back into inventory
		Artifact tempArtifact = equippedGear;
		equippedGear = null;
		this.addArtifact(tempArtifact);
		io.display(name + " takes off the " + tempArtifact.name() + ".");
	}
	
//--------------------------------------------------------------------------------------------	
	public void equipWeapon(Weapon weapon)
	{
		if (weapon == null)
		{
			io.display("Could not equip the item.");
			return;
		}
		
		// Character cannot equip item if they don't have it in the first place
		if (hasArtifact(weapon) == false)
		{
			io.display("You don't even have the " + weapon.name() + " to equip!");
			return;
		}
		
		// Store item into equippedWeapon slot and remove it from inventory
		equippedWeapon = weapon;
		
		collection.removeElement(weapon);
		io.display(name + " is now holding a " + equippedWeapon.name() + ".");
	}
	
//--------------------------------------------------------------------------------------------
	public void unequipWeapon()
	{
		if (equippedWeapon == null)
		{
			io.display("You have nothing to unequip.");
			return;
		}
		
		// Take off weapon from equippedWeapon slot and put it back into inventory
		Artifact tempArtifact = equippedWeapon;
		this.addArtifact(tempArtifact);
		equippedWeapon = null;
		io.display(name + " takes off the " + tempArtifact.name() + ".");
	}
//--------------------------------------------------------------------------------------------
	// Returns the character corresponding to input
	public static Character stringToCharacter(String input)
	{
		
		// Iterate through characters and search for correct one
		for (Map.Entry<Integer, Character> entry : hmap.entrySet())
			if (entry.getValue().name().equalsIgnoreCase(input))
				return entry.getValue();
		
		return null;
	}
	
//--------------------------------------------------------------------------------------------
	public String equippedGearName()
	{
		return equippedGear.name();
	}
	
//--------------------------------------------------------------------------------------------
	public String equippedWeaponName()
	{
		return equippedWeapon.name();
	}
	
//--------------------------------------------------------------------------------------------
	//  Update the character and their attributes based on the consumable they obtain
	public void consumeItem(Consumable item)
	{
		money += item.moneyBoost();
		dealHealth(item.healthBoost());
		strength += item.strengthBoost();
		addEXP(item.expBoost());
		inventorySpace += item.inventoryBoost();
		collection.removeElement(item);
	}
	
//--------------------------------------------------------------------------------------------
	private void addEXP(int points)
	{
		exp += points;
		money += points/10;
		
		//Increase level and apply bonuses if needed
		while (exp/100 > level)
		{

			level++;
			money += (level*150);
			currentHealth += 100;
			maxHealth += 100;
			strength += 1;
			//System.out.println(name + " leveled up to lvl " + level + "!");
			io.display(name + " leveled up to lvl " + level + "!");
		}
	}
	
//--------------------------------------------------------------------------------------------
	public boolean incapacitated()
	{
		return incapacitated;
	}
	
//--------------------------------------------------------------------------------------------
	public int currentInventorySpace()
	{
		return collection.size();
	}
	
//--------------------------------------------------------------------------------------------
	public void dealMoney(int m)
	{
		money += m;
	}
	
//--------------------------------------------------------------------------------------------
	// Checks the health of the character and keeps it bounded 
	public void dealHealth(int h)
	{
		currentHealth += h;
		
		if (currentHealth > maxHealth)	// Make sure character does not exceed maxHealth of 100
			currentHealth = maxHealth;
		if (currentHealth <= 0)			// Incapitated
		{
			currentHealth = 0;
			io.display(name + " is incapacitated!");
			incapacitated = true;
			dropAllItems();
		}
	}
	
//--------------------------------------------------------------------------------------------
	public String healthToString()
	{
		return currentHealth + "/" + maxHealth;
	}
	
}// End public class Character
