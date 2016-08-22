/************************************************************
 *                                                          *
 *  CSCI 470/680-E         Assignment 5     Summer 2016     *                    
 *                                                          *
 *  Programmer: Pratik Patel                                *
 *				Saiteja Yagni                               *
 *				Mohammed Jaffer Ali                         *  
 *                                                          *
 *  Date Due:   08/04/2016                                  *                          
 *                                                          *
 *  Purpose:    To implement Java Concurrency and Animation *
 ***********************************************************/

/* Imports from abstract window tool kit*/
import java.awt.BorderLayout;

/*Imports from swing class*/
import javax.swing.JFrame;
import javax.swing.JPanel;

public class mainFrame extends JFrame
{
	/*Frame height and width are described here*/
	private final int FRAME_WIDTH = 750;
	private final int FRAME_HEIGHT = 255;
	
	/*Two panels are declared*/
	JPanel panel1;
	JPanel panel2;
	

	
	mainFrame()
	{
			
		/*super class constructor is called*/
		super("Sorting Animation");
		
		/* Two panels are initialized */
		panel1 = new sortPanel();
		panel2 = new sortPanel();
		
		this.add(panel1,BorderLayout.WEST);//Panel one is added
	
		
		this.add(panel2,BorderLayout.EAST); //Panel two is added
		setSize(FRAME_WIDTH,FRAME_HEIGHT); //size of the frame is set
	}
	
	/*Main function where execution starts */
	public static void main(String[] args)
	{
		JFrame mf = new mainFrame(); //frame object is created
		mf.setLocationRelativeTo(null); //Location is set to null
		mf.setDefaultCloseOperation(EXIT_ON_CLOSE); //close operation is set to false
		mf.setVisible(true); //frame is made visible
		mf.setResizable(false); //frame is not resizable
	}
}
