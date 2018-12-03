//////////////////////////////////////////////////////////////////////////////////////////////////
//										CS 342 FALL 2018										//
//////////////////////////////////////////////////////////////////////////////////////////////////
//		Author:			Jeremy Robles															//
//		Instructor:     Dr. John T. Bell														//
//		Lecture:		12:30 PM																//
//		Assignment:		HW5																		//
//		File Name:		GUI_2.java														//
//////////////////////////////////////////////////////////////////////////////////////////////////
//		Description:																			//
//											//
//////////////////////////////////////////////////////////////////////////////////////////////////



import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class GUI_2 implements UserInterface
{
	private JFrame window;
	private JPanel column1, column2, column3;
	private JPanel charPanel, invePanel, placePanel, placeInfoPanel;
	private JTextArea output;
	private JTextField input;
	private TitledBorder charBorder, inveBorder, placeBorder, placeInfoBorder;
	
	public GUI_2()
	{
		
		window = new JFrame();
		window.setLayout(new BorderLayout());
		charPanel = new JPanel();
		invePanel = new JPanel();
		placePanel = new JPanel();
		placeInfoPanel = new JPanel();
		output = new JTextArea();
		input = new JTextField();
		column1 = new JPanel();
		column1.setLayout(new BorderLayout());
		column2 = new JPanel();
		column2.setLayout(new BorderLayout());
		column3 = new JPanel();
		column3.setLayout(new BorderLayout());
		
		charBorder = BorderFactory.createTitledBorder("Character Description");
		inveBorder = BorderFactory.createTitledBorder("Inventory");
		placeBorder = BorderFactory.createTitledBorder("Place Name");
		placeInfoBorder = BorderFactory.createTitledBorder("What's in here:");
		
		charPanel.setBorder(charBorder);
		invePanel.setBorder(inveBorder);
		placePanel.setBorder(placeBorder);
		placeInfoPanel.setBorder(placeInfoBorder);

		column1.add(charPanel, BorderLayout.NORTH);
		column1.add(invePanel, BorderLayout.CENTER);
		column2.add(placePanel, BorderLayout.NORTH);
		column2.add(placeInfoPanel, BorderLayout.CENTER);
		column3.add(output);
		column3.add(input);
		
		window.add(column1, BorderLayout.WEST);

		window.add(column3, BorderLayout.EAST);
		window.add(column2, BorderLayout.CENTER);
		window.setPreferredSize(new Dimension (1000, 800));
		window.pack();
		//window.setVisible(true);
	}
	
	@Override
	public void display(String s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getLine() {
		// TODO Auto-generated method stub
		return null;
	}

}
