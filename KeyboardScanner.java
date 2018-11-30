//////////////////////////////////////////////////////////////////////////////////////////////////
//										CS 342 FALL 2018										//
//////////////////////////////////////////////////////////////////////////////////////////////////
//		Author:			Jeremy Robles															//
//		Instructor:     Dr. John T. Bell														//
//		Lecture:		12:30 PM																//
//		Assignment:		HW3																		//
//		File Name:		KeyboardScanner.java													//
//////////////////////////////////////////////////////////////////////////////////////////////////
//		Description:																			//
//	This singleton class is used for user inputs so that only one Scanner object is in use.		//
//////////////////////////////////////////////////////////////////////////////////////////////////

import java.util.Scanner;
public class KeyboardScanner
{
	private static KeyboardScanner keyboardScanner;
	private Scanner cin;
	
	private KeyboardScanner()
	{
		cin = new Scanner(System.in);
	}// End constructor private KeyboardScanner()
	
	public static Scanner getKeyboardScanner()
	{
		if (keyboardScanner == null)
			keyboardScanner = new KeyboardScanner();
		
		return keyboardScanner.cin;
	}// End public static KeyboardScanner getKeyboardScanner()
}// End public class KeyboardScanner
