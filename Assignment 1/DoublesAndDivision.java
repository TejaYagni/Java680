/************************************************************
 *                                                          *
 *  CSCI 470/680-E        Assignment 1         Summer 2016  *                   
 *                                                          *
 *  Programmer: Saiteja Yagni                               *
 *              Pratik Patel                                *  
 *                                                          *
 *  Date Due:   06/23/2016                                  *                   
 *                                                          *
 *  Purpose:    To understand the effects of integer        *
 *              division versus floating point division     *
 ***********************************************************/
public class DoublesAndDivision
{
 public static void main(String[] args)
    {
	int num1 = 30, //num1 is intialized to 30
	    num2 = 20, //num2 is intialized to 20
	    num3; //unintialized num3
	
	double num4; //unintialized variable num4 of type double

	System.out.println("**** CSCI 470/680-E Assignment 1 Output ****");

	num3 = num1 / num2; //divide num1 by num2 and store the value in num3
	/* Displays the result on the console */
	System.out.printf("The result of integer %d divided by integer %d and stored in an integer is %d\n",num1,num2,num3); 

	num4 = num1 / num2; //divide num1 by num2 and store the value in num4
	/* Display the result on the console */
	System.out.printf("The result of integer %d divided by integer %d and stored in a double is %f\n",num1,num2,num4);

	num4 = (double) num1 / num2; //divide num1 which is casted into a double by num2 and store the value in num4
	/* Display the result on the console */
	System.out.printf("The result of integer %d (cast as double) divided by integer %d and stored in a double is %f\n",num1,num2,num4);

	System.out.println("**** CSCI 470/680-E Output Complete ****");
    }
}

