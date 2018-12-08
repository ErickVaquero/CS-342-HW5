//////////////////////////////////////////////////////////////////////////////////////////////////
//										CS 342 FALL 2018										//
//////////////////////////////////////////////////////////////////////////////////////////////////
//		Author:			Jeremy Robles															//
//		Instructor:     Dr. John T. Bell														//
//		Lecture:		12:30 PM																//
//		Assignment:		HW4																		//
//		File Name:		UI.java																	//
//////////////////////////////////////////////////////////////////////////////////////////////////
//		Description:																			//
//	Implements the DecisionMaker interface. The user decides what moves a player character		//
//	will make. They can use any command as of GDF 4.0.											//
//////////////////////////////////////////////////////////////////////////////////////////////////

import java.util.Scanner;


public class UI implements DecisionMaker
{
	
////////////////////////////////////////MEMBER FUNCTIONS ////////////////////////////////////////
//--------------------------------------------------------------------------------------------
	@Override
	public Move getMove(Character ch, Place p)
	{
		
		String input;		// User's raw input is stored here
		String uCaseInput;	// User's raw input is converted to uppercase and stored here
		//Scanner scanner = KeyboardScanner.getKeyboardScanner();

		p.display();
		ch.io.display(p.getDisplayString());
		
		//System.out.print("Enter input: ");
		ch.io.display("\nEnter input: ");
		//input = scanner.nextLine();
		input = ch.io.getLine();
		uCaseInput = input.toUpperCase();
		
		// Examine the input and execute the proper actions
		if (uCaseInput.matches("QUIT") || uCaseInput.matches("EXIT"))
			return new MoveQuit(ch, p);
		else if (uCaseInput.matches("LOOK"))
			return new MoveLook(ch, p);
		else if (uCaseInput.contains("GET "))
		{
			uCaseInput = uCaseInput.replace("GET ", "");
			return new MoveGet(ch, p, uCaseInput);
		}
		else if (uCaseInput.contains("DROP "))
		{
			uCaseInput = uCaseInput.replace("DROP ", "");
			return new MoveDrop(ch, p, uCaseInput);
		}
		else if (uCaseInput.contains("USE "))
		{
			uCaseInput = uCaseInput.replace("USE ", "");
			return new MoveUse(ch, p, uCaseInput);
		}
		else if (uCaseInput.matches("INVENTORY") || input.equalsIgnoreCase("INVE"))
		{
			return new MoveInve(ch,p);
		}
		else if (uCaseInput.contains("GO "))
		{
			uCaseInput = uCaseInput.replace("GO ", "");
			return new MoveGo(ch, p, uCaseInput);
		}
		else if (uCaseInput.contains("CONSUME "))
		{
			uCaseInput = uCaseInput.replace("CONSUME ", "");
			return new MoveConsume(ch, p, uCaseInput);
		}
		else if (uCaseInput.contains("ATTACK "))
		{
			uCaseInput = uCaseInput.replace("ATTACK ", "");
			return new MoveAttack(ch, p, uCaseInput);
		}
		else if (uCaseInput.contains("UNEQUIP "))
		{
			uCaseInput = uCaseInput.replace("UNEQUIP ", "");
			return new MoveUnequip(ch, p, uCaseInput);
		}

		else if (uCaseInput.contains("EQUIP "))
		{
			uCaseInput = uCaseInput.replace("EQUIP ", "");
			return new MoveEquip(ch, p, uCaseInput);
		}
		
		else if (uCaseInput.contains("BUY "))
		{
			uCaseInput = uCaseInput.replace("BUY ", "");
			return new MoveBuy(ch, p, uCaseInput);
		}
		else if (uCaseInput.contains("SELL "))
		{
			uCaseInput = uCaseInput.replace("SELL ", "");
			return new MoveSell(ch, p, uCaseInput);
		}
		else if (uCaseInput.contains("TEXT"))
		{
			ch.io.selectInterface(0);
			return new MoveTwiddle(ch);
		}
		else if (uCaseInput.contains("GUI"))
		{
			uCaseInput = uCaseInput.replace("GUI ", "");
			int pick = Integer.parseInt(uCaseInput);
			ch.io.selectInterface(pick);
			return new MoveTwiddle(ch);
		}

		// Could not understand user input
		else
			return new MoveTwiddle(ch);
	}// End public Move getMove(Character ch, Place p)

	
	
}// End public class UI implements DecisionMaker
