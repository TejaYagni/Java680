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
 *		ways of redeeming his/her accumulated 	    *
 *		mileage into airline tickets using GUI      *
 ***********************************************************/

import javax.swing.JFrame; //To create frame


public class Viewer 
{
	public static void main(String[] args) 
	{
		try
		{
			JFrame frame = new FrameOne(); //Object for FrameOne created
			frame.setLocationRelativeTo(null);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //To show close option
			frame.setVisible(true); //So that it appears on screen
		}
		catch(Exception e)
		{
			System.out.print("Error"); //If there is any error then this is displayed on console
		}
	}
}

