
//////////////////////////////////////////////////////////////////////////////////////////////////
//										CS 342 FALL 2018										//
//////////////////////////////////////////////////////////////////////////////////////////////////
//		Author:			Cecilia Avila															//
//		Instructor:     Dr. John T. Bell													    //
//		Lecture:		12:30 PM																//
//		Assignment:		HW4																		//
//		File Name:		GameTester.java															//
//////////////////////////////////////////////////////////////////////////////////////////////////
//		Description:																			//
//	The test driver for the other classes and contains main(). The GameTester takes a command-	//
//	line argument of the name of a file, containing data in GDF 4.0 format. This file is		//
//	opened and connected to a Scanner which then is passed to the Game constructor. If the file	//
//	cannot be opened, then the program asks the user for an alternate file name until a file	//
//	can be opened or the user enters "quit" as the file name.									//
//////////////////////////////////////////////////////////////////////////////////////////////////

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class GameTester
{
//--------------------------------------------------------------------------------------------
	// Prints assignment information
	static void printGroupInfo()
	{
		System.out.println("Group 11: ");
		System.out.println("Jeremy Robles");
		System.out.println("netID: jroble29");
		System.out.println();
		System.out.println("Cecilia Avila");
		System.out.println("netID: cavila21");
		System.out.println();
		System.out.println("Erick Vaquero");
		System.out.println("netID: evaque2");
		System.out.println();
	}// End void printGroupInfo()

//--------------------------------------------------------------------------------------------
	// Main function
	public static void main(String[] args) throws IOException,FileNotFoundException
	{
		// Display Group info
		printGroupInfo();
		
		// File name stored as a string
		String fileName = args[0];
		
		// Input stream used when user enters an incorrect file name
		Scanner cin = new Scanner(System.in);
		
		// Get file name from command line. The file will not be altered
		File readOnly = new File(fileName);
		
		// Request file name again if file could not be found
		while (!readOnly.exists())
		{
			System.out.println(CleanLineScanner.divider1);
			System.out.println("Could not open file \"" + fileName + "\".");
			
			// Get file name
			fileName = getFileName(cin);
			
			// User wishes to quit
			if (fileName.equalsIgnoreCase("QUIT"))
				return;
			
			// Set file path
			readOnly = new File(fileName);
		}// End loop while (!readOnly.exists())

		// Display message saying file was found
		System.out.println(CleanLineScanner.divider1);
		System.out.println("Opened file \"" + fileName + "\".");

		// Input stream will be from the file
		Scanner stream = new Scanner(readOnly);
		
		// Create new game using the input file stream
		Game game = new Game(stream);
		
		// Begin gameplay
		game.play();
		
		// Close stream
		cin.close();
	}// End public static void main(String[] args)
	
//--------------------------------------------------------------------------------------------
	// Gets a string input from the user for a file name
	public static String getFileName(Scanner cin)
	{
		String input;
		
		System.out.println("Please a GDF file name:");
		System.out.print("=>");
		input = cin.nextLine();
		
		return input;
	}// End method public static String getFileName(Scanner cin)

}// End public class GameTester
