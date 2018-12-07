import javax.swing.*;

import java.awt.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;


public class GUI_1 implements UserInterface
{
	private Character thisCharacter;
	
	private JFrame frame;
	
	private JTextArea tf1;//This Character
	private JComboBox<String> cb1;//All Characters List Options
	private JTextArea tf2; //All Characters List Output
	private JTextArea tf3; //Output
	private JTextArea tf4;//This Place
	private JComboBox<String> cb2;//All Places List Options
	private JTextArea tf5;//All Places List Output
	private JTextField tf6;//Input
	private JScrollPane scroll;
	
	private String[] characters;
	private String[] places;
	
	String userInput;
	
	boolean isWaiting;

	public String[] getCharacterNames()
	{
		
		Collection<Character> cCollection = Character.hmap.values();
		Vector<String> names = new Vector<String>();
		for(Character c : cCollection)
		{
			names.add(c.name());
		}
		String[] strings = names.toArray(new String[names.size()]);
		return strings;
	}

	public String[] getPlaceNames()
	{
		Collection<Place> pCollection = Place.placesMap.values();
		Vector<String> places = new Vector<String>();
		for(Place p : pCollection)
		{
			places.add(p.name());
		} 
		String[] strings = places.toArray(new String[places.size()]);
		return strings;
	}
	
	public String thisCharacterDisplay()
	{
		
		
		String name = "PLAYER: " + thisCharacter.name + "\n\n ";
		String description = thisCharacter.description + "\n ";
		String stats = 
				"Level: " + Integer.toString(thisCharacter.level()) + "\n " +
				"XP: " + Integer.toString(thisCharacter.exp()) + "\n " +
				"Health: " + thisCharacter.healthToString() + "\n " +
				"Strength: " + Integer.toString(thisCharacter.strength()) + "\n " +
				"Money: " + Integer.toString(thisCharacter.money()) + "\n " +
				"Inventory Space: " + Integer.toString(thisCharacter.inventorySpace()) + "\n\n ";
		String holding = "Items: ";
		
		int collectionSize = thisCharacter.collection.size();
		if (collectionSize == 0)
			holding = holding + "nothing";
		else
			for (int i = 0; i < collectionSize; i++)
				holding = holding + thisCharacter.name() + " - " + thisCharacter.collection.get(i).description;
		holding = holding + "\n\n ";
		
		
		String wearing = "Gear: ";
		if (thisCharacter.hasEquippedGear() == false)
			wearing = wearing + "nothing\n\n ";
		else
			 wearing = wearing + thisCharacter.equippedGearName() + "\n\n ";
		
		String weapon = "Weapon: ";
		if (thisCharacter.hasEquippedWeapon() == false)
			weapon = weapon + "nothing\n ";
		else
			weapon = weapon + thisCharacter.equippedGearName() + "\n ";		
		
		
		return name + description + stats + holding + wearing + weapon;
	}
	
	public String thisPlaceDisplay()
	{
		if (thisCharacter.here() == null)
		{
			System.out.println("EMPTY BRUH");
			return "OOPS? ";
		}
		
		Place p = thisCharacter.here();
		String fill = "----------\n ";
		String name = "PLACE: " + p.name() + "\n\n ";
		String description = "Description:\n" + p.description() + "\n ";
		String ppl = "Characters in Room:\n ";
		String items = "Items in Room:\n ";
		
		Character temp;
		int size = p.charVector.size();
		if (size == 0)
			ppl = ppl + "no one\n\n ";
		else
		{
			for (int i = 0; i < size; i++)
			{
				temp = p.charVector.get(i);
				if(temp != thisCharacter)
					ppl = ppl + temp.name() + "\n\n ";
			}
		}
		
		Artifact tp;
		size = p.artifactVector.size();
		if (size == 0)
			items = items + "none\n ";
		else
		{
			for (int i = 0; i < size; i++)
			{
				tp = p.artifactVector.elementAt(i);
				ppl = ppl + tp.name + "\n ";
			}
		}
		
		return fill + name + description + ppl + items;
	}
	
	public String selectedPlaceInfo(String pString)
	{
		Collection<Place> cPlaces = Place.placesMap.values();
		for(Place p : cPlaces)
		{
			if (p.name() == pString)
				return p.description();
		}
		return "No info for this place";
	}
	
	public String selectedCharacterInfo(String cString)
	{
		Collection<Character> cCharacters = Character.hmap.values();
		for(Character c : cCharacters)
		{
			if (c.name() == cString)
				return c.description();
		}
		return "No info for this Character";
	}
	
	GUI_1(Character c){
		thisCharacter = c;
		
		frame = new JFrame(c.name()); //Create my window

	    characters = getCharacterNames(); //Get my array of 
	    places = getPlaceNames();
		

	    
		
	    tf1 = new JTextArea(); //This Character
	    
		cb1 = new JComboBox<String>(); //All Characters List Options
		DefaultComboBoxModel<String> model1 = new DefaultComboBoxModel<String>(characters);
		cb1.setModel(model1);
		
		tf2 = new JTextArea(); //All Characters List Output
		tf3 = new JTextArea(); //Output
		tf4 = new JTextArea(); //This Place
		
		cb2 = new JComboBox<String>(); //All Places List Options
		DefaultComboBoxModel<String> model2 = new DefaultComboBoxModel<String>(places);
		cb2.setModel(model2);
		
		tf5 = new JTextArea(); //All Places List Output
		tf6 = new JTextField(); //Input
		
		scroll = new JScrollPane (tf3);
	    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	          scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

	    
		
		
		tf1.setLineWrap(true);
		tf1.setWrapStyleWord(true);
		tf2.setLineWrap(true);
		tf2.setWrapStyleWord(true);
		tf3.setLineWrap(true);
		tf3.setWrapStyleWord(true);
		tf4.setLineWrap(true);
		tf4.setWrapStyleWord(true);
		tf5.setLineWrap(true);
		tf5.setWrapStyleWord(true);
		
		
		
		tf1.setBackground(new Color(255, 193, 224));
		tf4.setBackground(new Color(255, 193, 224));
		
		cb1.setBackground(new Color(254, 188, 255));
		tf2.setBackground(new Color(254, 188, 255));
		cb2.setBackground(new Color(254, 188, 255));
		tf5.setBackground(new Color(254, 188, 255));
		
		tf3.setBackground(new Color(191, 211, 255));
		tf6.setBackground(new Color(191, 211, 255));
		
		
		tf1.setEditable(false);
		tf2.setEditable(false);
		tf3.setEditable(false);
		tf4.setEditable(false);
		tf5.setEditable(false);
		tf6.setEditable(true);
		
		tf1.setText(thisCharacterDisplay());
		tf2.setText("\nInfo about selected character:\n\n" + selectedCharacterInfo(cb1.getSelectedItem().toString()) );
		tf3.setText("Output");
		tf4.setText(thisPlaceDisplay());
 	    tf5.setText("\nInfo about selected place:\n\n" + selectedPlaceInfo(cb2.getSelectedItem().toString()));
		tf6.setText("");
		
		cb1.addActionListener(new ActionListener()
        {
           public void actionPerformed(ActionEvent event)
           {
        	   tf2.setText("\nInfo about selected character:\n\n" + selectedCharacterInfo(cb1.getSelectedItem().toString()) );
           }
        });
		
		cb2.addActionListener(new ActionListener()
        {
           public void actionPerformed(ActionEvent event)
           {
        	   tf5.setText("\nInfo about selected place:\n\n" + selectedPlaceInfo(cb2.getSelectedItem().toString()));
           }
        });
		
		
		
		tf6.addActionListener(new ActionListener()
		{

            public void actionPerformed(ActionEvent e){
            		userInput = tf6.getText();
            		display(userInput);
        	    	tf6.setText("");
        	    	isWaiting = false;

                    characters = getCharacterNames();
                    DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(characters);
            		cb1.setModel(model);
            }
        });
	
		
		frame.add(tf1);
		frame.add(cb1);
		frame.add(tf2);
		
		//frame.add(tf3);
		frame.add(scroll);
		
		
		frame.add(tf4);
		frame.add(cb2);
		frame.add(tf5);
		frame.add(tf6);
		
		
		
		
		frame.setLayout(new GridLayout(0,4));
		frame.setSize(1000,700);
		frame.setVisible(false);
	}

	public void display(String s) 
	{
		String text = tf3.getText();
		text += "\n" + s;
		tf3.setText(text);
		
	}

	public String getLine()
	{
		tf1.setText(thisCharacterDisplay());
		tf4.setText(thisPlaceDisplay());
		
		
		
		isWaiting = true;
		
		
		characters = getCharacterNames();
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(characters);
		cb1.setModel(model);
		
		
		
		frame.setVisible(true);
		while (isWaiting)
		{ 
			System.out.print("");
		}
		
		frame.setVisible(false);
		isWaiting = true;
		return userInput;
	}

	
	
	
	public void refresh()
	{
		
		
		
		characters = getCharacterNames();
	    places = getPlaceNames();
	    
	}
	
	public void update()
	{
		tf1.setText(thisCharacterDisplay());		
		tf4.setText(thisPlaceDisplay());
 	    
	}
	
	
	
}
