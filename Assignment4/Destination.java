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
 *		mileage into airline tickets usng GUI       *
 ***********************************************************/
class Destination
{
    /*Variables to store values*/
    int normalMiles,
	supersaverMiles,
	additionalMiles,
	startMonth,
	endMonth;
    String destinationCity;
    /*Constructor to intialise the variables */
    Destination(int normalMiles,int supersaverMiles,int additionalMiles,int startMonth,int endMonth,String destinationCity)
    {
	this.normalMiles = normalMiles;
	this.supersaverMiles = supersaverMiles;
	this.additionalMiles = additionalMiles;
	this.startMonth = startMonth;
	this.endMonth = endMonth;
	this.destinationCity = destinationCity;
    }
    /*Used to set value of normal miles*/
    public void setNormalMiles(int normalMiles)
    {
	this.normalMiles = normalMiles;
    }
    /*method to get value of normal miles*/
    public int getNormalMiles()
    {
	return normalMiles;
    }
    /*method to set supersaver miles*/
    public void setSuperSaverMiles(int supersaverMiles)
    {
	this.supersaverMiles = supersaverMiles;
    }
    /*method to get value of supersaver miles*/
    public int getSuperSaverMiles()
    {
	return supersaverMiles;
    }
    /*method to set additional miles*/
    public void setAdditionalMiles(int additionalMiles)
    {
	this.additionalMiles = additionalMiles;
    }
    /*method to get additional miles*/
    public int getAdditionalMiles()
    {
	return additionalMiles;
    }
    /*method to set start month value*/
    public void setStartMonth(int startMonth)
    {
	this.startMonth = startMonth;
    }
    /*method to get start month value*/
    public int getStartMonth()
    {
	return startMonth;
    }
    /*method to set end month value*/
    public void setEndMonth(int endMonth)
    {
	this.endMonth = endMonth;
    }
    /*method to get end month value*/
    public int getEndMonth()
    {
	return endMonth;
    }
    /*method to set destination city*/
    public void setDestinationCity(String destinationCity)
    {
	this.destinationCity = destinationCity;
    }
    /*method to get destination city*/
    public String getDestinationCity()
    {
	return destinationCity;
    }

} 


