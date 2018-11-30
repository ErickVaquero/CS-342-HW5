import java.util.Scanner;

//////////////////////////////////////////////////////////////////////////////////////////////////
//										CS 342 FALL 2018										//
//////////////////////////////////////////////////////////////////////////////////////////////////
//		Author:			Jeremy Robles															//
//		Instructor:     Dr. John T. Bell													    //
//		Lecture:		12:30 PM																//
//		Assignment:		HW4																		//
//		File Name:		CleanLineScanner.java													//
//////////////////////////////////////////////////////////////////////////////////////////////////
//		Description:																			//
// This class is used to get a clean line from a file stream or used for its dividers.			//
//////////////////////////////////////////////////////////////////////////////////////////////////


public class CleanLineScanner
{

	public static final String divider1 =	//Used as a divider when outputting to console
			"________________________________________________________________________________";
	//--------------------------------------------------------------------------------------------
	// Returns a clean line free of comments and unnecessary whitespaces from a file stream.
	public static String getCleanLine(Scanner stream)
	{
		//Extracted line from stream is stored here
		String cleanLine = "";
		
		//Use new delimiter
		String delimiter ="(//.*)|(\n)";
		stream.useDelimiter(delimiter);
		
		//There are no lines to read, so return empty line
		if (stream.hasNext() == false)
			return cleanLine;
		
		//Get next token and trim it
		cleanLine = stream.next();
		cleanLine = cleanLine.trim();
		
		//Recursive sequence to get next non-empty line
		if (cleanLine.isEmpty())
			cleanLine = getCleanLine(stream);
		
		//Set delimiter back to default
		stream.useDelimiter("\\s");
		
		//Replace tab/space sequences as a single space
		cleanLine = cleanLine.replaceAll("\\p{Blank}+", " ");
		
		return cleanLine;
	}//end member method public static String getCleanLine(Scanner stream)
}//end public class CleanLineScanner
