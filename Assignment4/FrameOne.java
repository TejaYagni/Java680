/************************************************************
 *                                                          *
 *  CSCI 470/680-E         Assignment 4     Summer 2016     *                    
 *                                                          *
 *  Programmer: Pratik Patel                                *
 *		Saiteja Yagni                               *  
 *                                                          *
 *  Date Due:   07/25/2016                                  *                          
 *                                                          *
 *  Purpose:    To help an airline frequent flyer find good *
 *		ways of redeeming his/her accumulated       *
 *		mileage into airline tickets using GUI      *
 ***********************************************************/


import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;

public class FrameOne extends JFrame
{
	/*To specify the width of frame*/
	private static final int FRAME_WIDTH = 450;
	private static final int FRAME_HEIGHT = 100;
	
	private static String fileName=""; //to store filename
	
	private JTextField textField; //text field to take input
	private JButton button; //button to submit the file name
	private JPanel panel; //Panel to put the field and button into a frame
	
	FrameOne()
	{
		setTitle("File Name"); //title of frame
		/*Functions to create text field,button and panel*/
		createTextField();
		createButton();
		createPanel();
		setSize(FRAME_WIDTH,FRAME_HEIGHT); //TO specify the size of frame
	}
	/*Function to return the file name*/
	public static String getFile()
	{
		return fileName;
	}
	
	public void createTextField()
	{
	    
	   textField = new JTextField(20); //text field created    
	}
	
	
	/* Inner class to specify the functionality of button it implements Action listener interface*/
	class AddInterestListener implements ActionListener
	  {
		@Override
	    public void actionPerformed(ActionEvent event)
	    {
	      
	      fileName = (textField.getText()); //stores the name of file given as input to user
	      
	      try 
	      {
			city(); //method call
		  } 
	      catch (FileNotFoundException e) 
	      {
			//e.printStackTrace(); //If file is not found then it is printed
	    	  JOptionPane.showMessageDialog(null, "File not found");
	    	  System.exit(1);
	      }
	      
	      
	    }

		
	  }
	
	/* Function that creates  Frame to for FrameTwo class */
	public void city() throws FileNotFoundException
	{
		JFrame frame = new FrameTwo();
	    
	    frame.setLocationRelativeTo(null);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setVisible(true);
	}
	
	/* Function to create button*/
	private void createButton()
	  {
	    
	    button = new JButton("Submit");
	    
	    ActionListener listener = new AddInterestListener();
	    button.addActionListener(listener); //ActionListner set to button
	    
	  }
	
	/* Panel created  to set it with text field and button*/
	 private void createPanel()
	  {
	    
	    panel = new JPanel();
	    
	    panel.add(textField); //textfield added to panel
	    
	    panel.add(button); //button added to panel
	    add(panel); //panel added to frame
	  
	  }
}

