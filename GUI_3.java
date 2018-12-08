//////////////////////////////////////////////////////////////////////////////////////////////////
//										CS 342 FALL 2018										//
//////////////////////////////////////////////////////////////////////////////////////////////////
//		Author:			Cecilia Avila											     			//
//		Instructor:     Dr. John T. Bell														//
//		Lecture:		12:30 PM																//
//		Assignment:		HW5																		//
//		File Name:		GUI_3.java														        //
//////////////////////////////////////////////////////////////////////////////////////////////////
//		Description:																			//
//  This is GUI_3, it allows you to play the game in a simple window. It contains buttons 		//
//  that lets the user get see the players information stats, their inventory, and look.		//
//   The GUI also implements a scroll for the output information.								//
//////////////////////////////////////////////////////////////////////////////////////////////////

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class GUI_3 implements UserInterface {
	private JFrame frame;
	private JButton b1, b2, b3;
	private JTextField input;
	private JTextArea output;
	private JScrollPane scroll;
	private Character currChar;
	boolean isWaiting;
	String userinput;
	String display;

	
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
		frame.setVisible(true);
		
		while (isWaiting)
		{ 
			System.out.print("");
		}
		
		frame.setVisible(false);
		isWaiting = true;
		return userinput;
	}
	
	public GUI_3(Character c) {
		currChar = c;
		frame = new JFrame(currChar.name);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize (750, 450);
		frame.setLayout (new FlowLayout() );
		frame.getContentPane().setBackground(Color.gray);
	
		//-------------------------------------------
		// BUTTON FOR CHARACTER 
		b1 = new JButton ("PLAYER INFO");
		b1.setBackground(Color.PINK);
		b1.setOpaque(true);
		b1.addMouseListener (new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {	
				String s = currChar.summary();
				JOptionPane.showMessageDialog (null, s);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		}); 
		frame.add(b1);
		
		//-------------------------------------------
		// BUTTON FOR INVENTORY
		b2 = new JButton ("INVENTORY");
		b2.setBackground(Color.CYAN);
		b2.setOpaque(true);
		b2.addMouseListener (new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				String s = "";
				int size = currChar.collection.size();
				if (size == 0) {
					s = "Empty"; 
				}
				else {
					s = "You have the following: \n";
					for(int i = 0; i < size; i++) {
						s += currChar.collection.get(i).name() + "\n" ;
					}
				}
				JOptionPane.showMessageDialog (null, s);
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		frame.add(b2);
		
		//-------------------------------------------
		// BUTTON FOR LOOK
		b3 = new JButton ("LOOK");
		b3.setBackground(Color.MAGENTA);
		b3.setOpaque(true);
		b3.addMouseListener (new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				String s = "";
				s = currChar.here.look();
				JOptionPane.showMessageDialog (null, s);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

					
			}); 

			frame.add (b3);		
		
		//-------------------------------------------
		// INPUT TEXTBOX 
		input = new JTextField (40);
		input.setBorder(new LineBorder(Color.GREEN,2));
		input.setEditable(true);
		
		input.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				userinput = input.getText();
				display(userinput);
				input.setText("");
				isWaiting = false;
			}

		});		
		
		frame.add(input);
		
		//-------------------------------------------
		// OUTPUT TEXTBOX
		output = new JTextArea (20,40);
		output.setEditable(false);
		scroll = new JScrollPane (output);
	    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	          scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		frame.add(scroll);
		frame.setVisible( false );

	}
}
