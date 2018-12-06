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
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class GUI_2 implements UserInterface
{
	private JFrame window;
	private JPanel column1, column2, column3;
	private JPanel charPanel, invePanel, placePanel, placeInfoPanel;
	private JLabel charDescLabel, charStatsLabel, placeDescLabel;
	private JLabel outfitLabel, weaponLabel;
	private Vector<JLabel> placeCharLabel, placeItemLabel, inveLabel;
	private JTextArea output;
	private JScrollPane outputScroll;
	private JTextField input;
	private TitledBorder charBorder, inveBorder, placeBorder, placeInfoBorder;
	private Character character;
	private Place thisPlace;
	private JButton actionButton;
	private ActionListener buttonListener;
	boolean isWaiting;
	String userInput;
	
	public GUI_2(Character c)
	{
		userInput = "";
		character = c;
		thisPlace = character.here;
		window = new JFrame(character.name());
		window.setLayout(new GridLayout(1,3));
		charPanel = new JPanel(new GridLayout(4,1));
		invePanel = new JPanel(new FlowLayout());
		placePanel = new JPanel(new GridLayout(1,1));
		placeInfoPanel = new JPanel();
		((FlowLayout)placeInfoPanel.getLayout()).setHgap(30);
		output = new JTextArea();
		input = new JTextField();
		column1 = new JPanel();
		column1.setLayout(new BoxLayout(column1, BoxLayout.PAGE_AXIS));
		column2 = new JPanel();
		column2.setLayout(new BoxLayout(column2, BoxLayout.PAGE_AXIS));
		column3 = new JPanel();
		column3.setLayout(new GridLayout(3,1));
		actionButton = new JButton("Done");
		outputScroll = new JScrollPane(output,  JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		buttonListener = new ActionListener()
	    {
		  @Override
	      public void actionPerformed(ActionEvent e)
	      {
	    	  userInput = input.getText();
	    	  display(userInput);
	    	  input.setText("");
	    	  isWaiting = false;
	      }
	    };
			actionButton.addActionListener(buttonListener);
		
		String charDesc = "<html>";
		charDesc+=character.description();
		charDesc = charDesc.replaceAll("\n", "<br/>");
		charDesc+= "</html>";
		
		String charSummary = "<html>";
		charSummary+=character.summary();
		charSummary = charSummary.replaceAll("\n", "<br/>");
		charSummary+= "</html>";
		
		charDescLabel = new JLabel(charDesc);
		charStatsLabel = new JLabel(charSummary);
		outfitLabel = new JLabel("Equipped Outfit: " + "NONE");
		weaponLabel = new JLabel("Equipped Weapon: " + "NONE");
		placeDescLabel = new JLabel("Place description here\n");
		placeCharLabel = new Vector<JLabel>();//new JLabel("List of Characters here\n");
		placeItemLabel = new Vector<JLabel>();//new JLabel("Items here\n");
		inveLabel = new Vector<JLabel>();//new JLabel("Inventory items here");

		
		charBorder = BorderFactory.createTitledBorder("Character Description");
		inveBorder = BorderFactory.createTitledBorder("Inventory " + character.inventoryString());
		placeBorder = BorderFactory.createTitledBorder("Place Name");
		placeInfoBorder = BorderFactory.createTitledBorder("What's in here:");
		
		charPanel.setBorder(charBorder);
		charPanel.add(charDescLabel);
		charPanel.add(charStatsLabel);
		charPanel.add(outfitLabel);
		charPanel.add(weaponLabel);
		invePanel.setBorder(inveBorder);

		placePanel.setBorder(placeBorder);
		placePanel.add(placeDescLabel);
		placeInfoPanel.setBorder(placeInfoBorder);
		//placeInfoPanel.add(placeCharLabel);
		//placeInfoPanel.add(placeItemLabel);
		//invePanel.add(inveLabel);

		column1.add(charPanel, BorderLayout.NORTH);
		column1.add(invePanel, BorderLayout.CENTER);
		column2.add(placePanel, BorderLayout.NORTH);
		column2.add(placeInfoPanel, BorderLayout.CENTER);
		column3.add(outputScroll, BorderLayout.NORTH);
		column3.add(input, BorderLayout.CENTER);
		column3.add(actionButton, BorderLayout.SOUTH);
		
		window.add(column1);
		window.add(column2);
		window.add(column3);
		window.setPreferredSize(new Dimension (1300, 700));
		window.pack();
		window.setVisible(false);
	}
	
	@Override
	public void display(String s)
	{
		String text = output.getText();
		text += "\n" + s;
		output.setText(text);
	}

	@Override
	public String getLine() {
		
		isWaiting = true;

		window.setVisible(true);
		while (isWaiting)
		{ 
			System.out.print("");
		}
		
		window.setVisible(false);
		isWaiting = true;
		return userInput;
	}
	
	public void refresh()
	{
		thisPlace = character.here();
		int itemInveAmount = character.currentInventorySpace();
		int itemPlaceAmount;
		int charPlaceAmount;
		
		charPanel.removeAll();
		String mainCharDesc = "<html>";
		mainCharDesc+=character.description();
		mainCharDesc = mainCharDesc.replaceAll("\n", "<br/>");
		mainCharDesc+= "</html>";
		
		String charSummary = "<html>";
		charSummary+=character.summary();
		charSummary = charSummary.replaceAll("\n", "<br/>");
		charSummary+= "</html>";
		
		charDescLabel = new JLabel(mainCharDesc);
		charStatsLabel = new JLabel(charSummary);
		charPanel.setBorder(charBorder);
		charPanel.add(charDescLabel);
		charPanel.add(charStatsLabel);
		charPanel.add(outfitLabel);
		charPanel.add(weaponLabel);
		
		//invePanel.repaint();
		
		String placeDesc = "<html>";
		placeDesc+= thisPlace.description();
		placeDesc = placeDesc.replaceAll("\n", "<br/>");
		placeDesc+= "</html>";
		
		placeBorder = BorderFactory.createTitledBorder(thisPlace.name());
		placePanel.removeAll();
		placePanel.setBorder(placeBorder);
		placeDescLabel = new JLabel(placeDesc);
		placePanel.add(placeDescLabel);
		
		placeInfoPanel.removeAll();
		placeCharLabel.removeAllElements();
		
		for (int i = 0; i < thisPlace.charVectorSize(); i++)
		{
			if (thisPlace.charVectorAt(i) != null)
			{
				String charDesc = "<html>";
				charDesc+= thisPlace.charVectorAt(i).description() + "\n"+ thisPlace.charVectorAt(i).summary();
				charDesc = charDesc.replaceAll("\n", "<br/>");
				charDesc+= "</html>";
				placeCharLabel.addElement(new JLabel("[" + thisPlace.charVectorAt(i).name() + "]"));
				placeCharLabel.get(i).setToolTipText(charDesc);
				placeInfoPanel.add(placeCharLabel.get(i));
			}
				
		}
		
		placeItemLabel.removeAllElements();
		for (int i = 0; i < thisPlace.artifactVectorSize(); i++)
		{
			if (thisPlace.artifactVectorAt(i) != null)
			{
				String artifactDesc = "<html>";
				artifactDesc+= thisPlace.artifactVectorAt(i).summary();
				artifactDesc = artifactDesc.replaceAll("\n", "<br/>");
				artifactDesc+= "</html>";
				placeItemLabel.addElement(new JLabel("[" + thisPlace.artifactVectorAt(i).name() + "]"));
				placeItemLabel.get(i).setToolTipText(artifactDesc);
				placeInfoPanel.add(placeItemLabel.get(i));
			}
				
		}
		

		invePanel.removeAll();
		
		
		inveBorder = BorderFactory.createTitledBorder("Inventory " + character.inventoryString());
		invePanel.setBorder(inveBorder);
		placeItemLabel.removeAllElements();
		
		for (int i = 0; i < itemInveAmount; i++)
		{
			if (character.collectionAt(i) != null)
			{
			placeItemLabel.addElement(new JLabel("[" + character.collectionAt(i).name() + "]"));
			String itemDesc = "<html>";
			itemDesc+= character.collectionAt(i).summary();
			itemDesc = itemDesc.replaceAll("\n", "<br/>");
			itemDesc+= "</html>";
			placeItemLabel.get(i).setToolTipText(itemDesc);
			}
			else
			{
				placeItemLabel.addElement(new JLabel("EMPTY SPACE"));
			}
			
			invePanel.add(placeItemLabel.get(i));
		}
		if (character.hasEquippedGear())
		{
			String itemDesc = "<html>";
			itemDesc+= character.equippedGearDescription();
			itemDesc = itemDesc.replaceAll("\n", "<br/>");
			itemDesc+= "</html>";
			
			outfitLabel.setText("Equipped Weapon: " + character.equippedGearName());
			outfitLabel.setToolTipText(itemDesc);
			
		}
		else
		{
			outfitLabel.setText("Equipped Outfit: NONE");
			outfitLabel.setToolTipText("You are wearing casual Friday clothes.");
		}
		
		if (character.hasEquippedWeapon())
		{
			String itemDesc = "<html>";
			itemDesc+= character.equippedGearDescription();
			itemDesc = itemDesc.replaceAll("\n", "<br/>");
			itemDesc+= "</html>";
			weaponLabel.setText("Equipped Weapon: " + character.equippedWeaponName());
			weaponLabel.setToolTipText(itemDesc);
		}
		else
		{
			weaponLabel.setText("Equipped Weapon: NONE");
			weaponLabel.setToolTipText("You brandish nothing but your fists.");
		}
		
		//window.repaint();
		window.revalidate();
	}
	

}
