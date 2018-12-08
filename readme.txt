CS 342 Homework 5 - (Group Project)

Group Members: Cecilia Avila (cavila21), Jeremy Robles (jroble29), Erick Vaquero (evaque2)

Course: CS 342 - Fall 2018
Course Instructor: John T. Bell
Assingment Summary: This assignment is a role-playing game written in Java
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
DIVISION OF WORK:

General Overview - 

	Everyone contributed to the inital base code of the assignment. Cecilia contributed her 
	GameTester, Place, and Direction Classes. Jeremy contributed his Character, Player, NPC,
	UI, AI, Descionmaker, Gamer, CleanLine Scanner and Move Classes. Erick contributed his 
	Artifact Class and the KeyboardScanner Class. Eveyone came together to meet for about
	three hours to have the code work properly together. We later came together again to
	try to put the final extended code together with the GUI components.
	
	Everyone contributed to the extension of the code and the specifics of each individual's 
	contribution is outlined below.

	Everyone contributed to the proccess of creating the UML. Jeremy and Erick met to outline
	the UML and Cecilia created the finalized digital version of the UML for submission.
	

Cecilia Avilia - Extended the Place and Direction Class. (GUI_3)

	Cecilia was responsible for extending the Place Class and Direction Class. Extension to the 
	Place Class includes the creation of the DarkPlace, HealthPlace, and Shop Classes. The DarkPlace
	Class is used for creating a dark room in the game in which the player cannot navigate without
	obtaining some sort of lighting artifact to see. The result is that the player only has the option
	to return to the room they came from. Only when they use a light source will they be able to see
	the artifacts and paths in the room. The HealthPlace Class is used for creating rooms which can 
	affect the health of players in the game. Depending on the choice of the game creator, a room will
	either negativly impact the player's health (acting as hazardous, poisonous, radioactive, etc.)
	or positivly impact the player's health (acting as some sort a hospital, magical room, etc.).
	Finally, the Shop Class in a room in the game where the player can buy or sell artifacts. Inside
	of a Shop Class room, the player is not allowed to pick up or drop items. The shop sells items
	which are finite (e.g. rare superweapon) as well as items which are infinite (e.g. a sandwhich). 
	Changes in the Direction class are to support the Place Class extentions. Cecilia also did the 
	commenting in the project

	GUI_1 works as follows: Each player character gets their own window (JFrame). To view the
	player's stats, they need to click he pink "Player Info" button. To view the inventory,
	the cyan "inventory" button needs to be pressed. To view the current place, the 
	magenta "look" button needs to be pressed. Pressing either of these three buttons
	results in a small window to pop up to see the description.Below the three buttons are the
	input/output components. The user enters their input in the middle textbox presses the return key
	to complete an action. The output is displayed in the bottom text area.

	
Jeremy Robles - Extended the Character and Move Class, (GUI_2)
	
	Jeremy was responsible for extending the Character Class and also worked on extending the Move class.
	The Character Class was extending by giving characters additional "stats" which effected how characters
	played the game. These stats include current health, maximum health, strength, carrying capacity of
	items (has a relationship with strength), current experiance (XP), current level (based on XP), equipped
	gear (clothes), equipped weapon, money. These attributes allow the player to attack, die, buy, sell, 
	and level up and increase their stats. The added Move classes implement the use of the new attributes.
	The UI/AI was adjusted accordingly to allow the user to use these actions. Jeremy helped with the Place Class
	and created the TextInterface class.

	GUI_2 works as follows: The left pane contains the character information such as its descriptions, stats,
	and inventory. If you hover over some of the JLabels, it will give you a description of the items and
	their values. The middle pane describes the current place. The top boxlists the current place and its
	description. The lower box details all the characters and the items in the place. If you hover over the
	JLabels, it will give you a description of the items and their values, or if its a character, it will
	give you a description of that character and a brief summary.  The rightmost panel is the input/output
	panel. The user enters their input in the middle textbox and presses the lower "Done" button to complete
	an action. The output is displayed in the upper text area.

Erick Vaquero - Extended the Artifact Class (GUI_1)

	Erick was responsible for extending the Artifact Class as well as some additional features. The Artifact
	class was extended though the creation of four child classes: Consumable, Gear, Weapon, and Lamp. 
	The Consumable Class allows for the creation of items such as food, healing potions, or poison which effect 
	a characters stats (health, strength, etc.). A Consumable Class item can only be used once, and then it is
	gone forever (e.g. A sandwhich you eat). The Gear Class represents clothing/gear which a characters can wear
	to effect their stats while they wear it. For simplification, the character can only wear one item at a time
	such as a mad scientists outfit which increases maxumum health, or a warrior outfit which increases strength.
	The Weapon Class is an character usable weapon which allows a user to increase the damage they deal when
	attacking. The Lamp Class artifact is simply an item which can be used to light a Place of type DarkRoom. Erick
	helped with the Place Class. Erick did the Readme, makefile. Erick implemented the ability to run GDF format 
	3.1 and higher.

	GUI_1 works as follows: The left pane contains the character information such as its descriptions, stats,
	and inventory. In addition, it also includes information about the current place. It gives the description
	and the other characters in the room. The middle pink panels have dropdown menus to allow the user to see
	which characters are in the game and what places there are. Selecting an item in those JCombo boxes gives
	the description of the character or place. The rightmost panel is the input/output panel. The user enters their
	input in the textbox and presses enter to complete an action. The output is displayed in the upper text
	area.

~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
HOW TO RUN THE PROGRAM:

We included our sample game file called "t.gdf" to use

1. $make
2. $java GameTester t.gdf

Commands (case not sensitive):
go (direction)
get (item name current place that is not shop)
use (item name from inventory)
consume (consumable name from inventory)
equip (item name from inventory)
unequip (item name that is equipped)
drop (item name from inventory)
buy (item name from shop)
sell (item name from inventory)
attack (character in same place)
look
inve
inventory
text
GUI
quit