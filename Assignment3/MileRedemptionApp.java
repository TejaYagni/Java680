/************************************************************
 *                                                          *
 *  CSCI 470/680-E         Assignment 3     Summer 2016     *                               
 *                                                          *
 *  Programmer: Pratik Patel                                *
 *		Saiteja Yagni                               *  
 *                                                          *
 *  Date Due:   06/16/2016                                  *                          
 *                                                          *
 *  Purpose:    To help an airline frequent flyer find good *
 *		ways of redeeming his/her accumulated 	    *
 *		mileage into airline tickets		    *
 ***********************************************************/

import java.util.Scanner; //To get input from the user
import java.io.File; //To use files
import java.io.IOException; //To handle input output exception

public class MileRedemptionApp 
{
    public static void main(String[] args) throws IOException
    {
	int numMiles, //Stores number of Miles enered ny the user
	    month; //Stores month of departure
	char cont; //Stores whether the user want to continue or exit
       	cont = 'y'; //intialized to y
	Scanner inputScan = new Scanner(System.in); //Scanner object created to take input from the console
	
	do // A do wile loop to prompt user for recommendtions multiple times
	    {
		try
		    {
			cont = 'n'; //value changed to n
			System.out.println("Enter the name of the file with exension"); // Prompts user to enter the filename
			String fileName = inputScan.next(); // Stores the input givn by user

			Scanner fileInput = new Scanner(new File(fileName)); //Scanner object for reading the contents of the file
			MileRedeemer redeemerObj = new MileRedeemer(); // Creates an object of MileRedeemer class

			redeemerObj.readDestinations(fileInput); // Method call to readDestinations function in MileRedeemer
			System.out.println("\n---------------------------------------------");
			System.out.println("List of destination cities you can travel to: ");
			for(String str: redeemerObj.getCityNames()) //Loop to print the city names
			    {
				System.out.println(str); //prints out each city name
			    }
			System.out.println("\n---------------------------------------------");
			System.out.print("\nPlease input your total accumulated miles: "); // Prompts user
			numMiles = inputScan.nextInt(); //Stores input

			System.out.print("\nPlease input your month of departure (1-12): "); // Prompts user
			month = inputScan.nextInt(); // Stores input

			System.out.println("");
			for(String str:redeemerObj.redeemMiles(numMiles, month)) //for each loop to print values returned by redeemMiles method
			    {
				System.out.println(str);
			    }
			System.out.println("\nYour remaining miles: "+redeemerObj.getRemainingMiles()); //Displays the remaining miles after recommendations are made
			fileInput.close(); //close fileInput object
		    }
		catch(Exception e)
		    {
			System.err.println("\nEncountered wrong input "+e.getMessage());
		    }
		finally
		    {
			System.out.print("\nDo you want to continue:(y/n)?"); //Prompts user to select whether he wants to continue or not
			String str = inputScan.next(); // Stores the user input
			cont = str.charAt(0); //converts string into char
			
		    }

	    }while(cont=='y'); // If user enters y then he will again be prompted
	System.out.println("\nEnd of appliation!"); //Displays it to user
	inputScan.close(); //closes the inputScan object
	
    }
}
