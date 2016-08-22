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
import java.util.Arrays; //To use arrays
import java.util.Collections; //To use sort method 
import java.util.Comparator; //To implement Comparator interface
import java.util.Scanner; //To read streams from conole as well as file
import java.util.ArrayList; //To use ArrayList

class MileRedeemer
{
	
    int remainingMiles=0; //remaining miles set to 0
    String record; // To store each line from file 
    ArrayList<Destination>destinationList = new ArrayList<Destination>(); // ArrayList of type Destination is created
    
    /*This function is used to read lines from files*/
    public void readDestinations(Scanner fileScanner)
    {
	try
	    {
		/*If the file has a line then the loop is executed else no*/
		while(fileScanner.hasNext()) 
		    {
			int normalMiles, //stores normal miles
			    supersaverMiles, //stores super saver miles
			    additionalMiles, //stores addtional miles
			    startMonth, //stores start of month
			    endMonth; //stores end of month
			String destinationCity; //stores destination city 
			record = fileScanner.nextLine(); //stores the next line into record
			
			String[] values = record.split(";"); //splits record on encountering semi-colon
			String[] values2 = values[4].split("-"); //splits the record on encountering hipen
			
			/*values that are obtained after splitting the record are then stored into different variables*/
			destinationCity = values[0]; 
			/*strings are converted into integers using Integer.parse int and stored in variables*/
			normalMiles = Integer.parseInt(values[1]);
			supersaverMiles = Integer.parseInt(values[2]);
			additionalMiles = Integer.parseInt(values[3]);
			startMonth = Integer.parseInt(values2[0]);
			endMonth = Integer.parseInt(values2[1]);
			
			/*Object of type Destination is created and populated with above intialized variables*/
			Destination obj = new Destination(normalMiles,supersaverMiles,additionalMiles,startMonth,endMonth,destinationCity);
			
			/*This object is stored in array list*/
			destinationList.add(obj);
			
		    }
	    }
	catch(Exception e)
	    {
		System.err.println("File input format wrong: "+e.getMessage());
		System.exit(0);
	    }
	
	
    }
    
    /*getCityNames is a function which returns a String array containing Cities*/
    public String[] getCityNames()
    {
	int i = 0; 
	String[] cityNames = new String[destinationList.size()]; //String array cityNmaes is created store cityNames
	/*cityNames is populated with city names using for each loop*/    
	for(Destination d: destinationList)
	    {
		cityNames[i] = d.getDestinationCity();
		i++;
	    }
	Arrays.sort(cityNames); // cityNames is sorted in Ascending order
	return cityNames; //String array is returned
    }    
    
    public String[] redeemMiles(int miles,int month)
    {
    	int tempMiles = miles; //stores miles into another variable
	
        ArrayList<Destination> abc = new ArrayList<>(); //ArrayList of type destination is created
        ArrayList<String> def = new ArrayList<>(); //ArrayList of type String is created
    	
        Collections.sort(destinationList,new MileageComparator()); // destinationList is sorted in the descending order of Normal miles
	/*For each to prse through destination list*/    	
	for(Destination d: destinationList) 
	    {
    		if(month < d.getStartMonth() || month > d.getEndMonth()) //If the month is less that start month or greater than endmonth then if is executed
		    {
			if(tempMiles > d.getNormalMiles()) 
			    {
				/*if miles is greater than Normal miles then this object is added into abc arrayList*/
				abc.add(d);
				tempMiles-= d.getNormalMiles(); //miles is decreased by normal miles of the current instance
			    }
			
		    }
    		else if(month >= d.getStartMonth() && month <= d.getEndMonth()) //If month is in super saver miles then this block is executed
		    {
    			if(tempMiles > d.getSuperSaverMiles()) 
			    {
				/* If miles is greter than super saver then the miles is decresed by super saver ad the current instance of Destiantion is added into array list abc */
    				abc.add(d); 
    				tempMiles -= d.getSuperSaverMiles();
			    }
		    }
		
	    }
	
    	for(Destination d: abc)
	    {
		/* If the miles is greater than additional miles then ticket is redeemed to first class and miles is decreased by additional miles */
		if(tempMiles > d.getAdditionalMiles()) 
		    {
			def.add("* A trip to "+d.getDestinationCity()+" first class"); //def is populated with strings of recommendations
			tempMiles -= d.getAdditionalMiles();
		    }
		else // If miles is less than additional miles then economy class ticket is recommended
		    {
			def.add("* A trip to "+d.getDestinationCity()+", economy class"); //Displays economy class with destination city
		    }
	    }
    	remainingMiles = tempMiles; //remainingMiles is filled with mile left
        String[] details = new String[def.size()]; //String array is created
        details = def.toArray(details); //populated with arraylist of type string
    	return details; //returns the details
    }
    
    
    public int getRemainingMiles()
    {
    	return remainingMiles; // Returns the remaining miles
    }
    
}

class MileageComparator implements Comparator<Destination> 
{
    @Override
    public int compare(Destination d1, Destination d2) //Compares attributes of two instances of Destinations 
    {
	if(d1.getNormalMiles() > d2.getNormalMiles()) //compares normal miles between two intstances
	    {
		return -1;
	    }
	else if(d1.getNormalMiles() < d2.getNormalMiles()) //if normal miles of second instance is greater than first
	    {
		return 1;
	    }
	else return 0;
    }
}
    
