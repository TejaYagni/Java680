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

/* Imports to use Swing components*/
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.Color;
/* Imports to use awt tools*/
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/* To generate random number*/
import java.util.Random;

public class sortPanel extends JPanel
{
	
	/* Components are declared here*/
	private JButton populateButton;
	private JButton sortButton;
	
	private JComboBox<String> box;
	private JComboBox<String> speedBox;
	
	private JPanel sap; //JPanel object 
	
	
	/* Panel width and height are specified here*/
	private final int PANEL_WIDTH = 320;
	private final int PANEL_HEIGHT = 320;
	
	private int[] intList; //Declare an array of integers
	
	/* String array which stores algorithms */
	private String[] algorithms = {"Bubble","Selection","Insertion","Quick Sort"};
	private String[] speed = {"Fast","Medium","Slow"};
	
	GridBagLayout layout1; //GridBagLayout declared here
	
	GridBagConstraints gbc; //GridBagConstraints declared here
	
	JLabel lab = new JLabel("  "); //new JLabel created here
	
	
	/* Constructor for sortPanel */
	sortPanel()
	{
		super();
		layout1 = new GridBagLayout(); //layout defined 
		
		this.setLayout(layout1); //layout set for the this panel
			
		sap = new SortAnimationPanel(); //object for sortAnimation panel created
		
		createButton(); //function call to create button
		createComboBox(); //function call to create Combo box
		addControls(); //function to add buttons and combo to frame
		setSize(PANEL_WIDTH,PANEL_HEIGHT); // size of panel taht is height and width are set
	}
	
	
	/* Method for adding controls */
	public void addControls()
	{
		/*GridBagCOnstraints to set them in a proper way*/
		gbc = new GridBagConstraints();
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 6; // width set to 6
		gbc.gridheight = 6; // height set to 6
		
		
		
		add(sap,gbc); // panel added to panel
		
		
		gbc.gridx=0;
		gbc.gridy=6;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		
		add(lab,gbc); //label added to panel
		
		
		gbc.gridx=1;
		gbc.gridy=6;
		
		add(populateButton,gbc); //button added to panel
		
		
		gbc.gridx = 2;
		gbc.gridy = 6;
		
		add(box,gbc); //Combo box added to panel
		
		gbc.gridx = 3;
		gbc.gridy = 6;
		
		add(speedBox,gbc); //Sort button added to panel
		
		gbc.gridx = 4;
		gbc.gridy = 6;
		

		add(sortButton,gbc); //Sort button added to panel
		
		gbc.gridx = 5;
		gbc.gridy = 6;
		
		
		
	}
	
	
	/* Method to create buttons*/
	
	public void createButton()
	{
		populateButton = new JButton("Populate"); //Button by name Populate is created
		sortButton = new JButton("Sort"); //Button by name Sort is created
		
		 ActionListener pbl = new PopulateButtonListener(); //listener object created 
		 populateButton.addActionListener(pbl); //listener added to button
		 
		 sortButton.setEnabled(false);
		 
		 ActionListener sbl = new SortButtonListener();  //listener object created 
		 sortButton.addActionListener(sbl); //listener added to button
	}
	
	/* Method to create comboBox */
	
	public void createComboBox()
	{
		box = new JComboBox<String>(algorithms); // box created which contains array of string
		speedBox = new JComboBox<String>(speed); //box created for dispalying 
	}
	
	
	/* Class for creating panel, this class implements Runnable and extends JPanel */
	class SortAnimationPanel extends JPanel implements Runnable 
	{
		
		private Thread t; //thread declared
		
		private int speedSelected;
		
		private String selectedAlgo; // string variable to hold the selected value 
		private String selectedSpeed; //string variable to hold the selected speed
		
		private static final long serialVersionUID = 1L;

		/* Constructor for SortAnimationPanel */
		SortAnimationPanel()
		{
			super(); //calls the super class constructor
			
			intList = new int[320]; //creates the integer list
			
			setPreferredSize(new Dimension(320,220)); //size  set for Panel
		}
		
		
		/* Method run which is implicitly called upon creating a thread*/
		
		@Override
		public void run() 
		{
				
			if(selectedSpeed == speed[0])
				speedSelected = 1;
			else if(selectedSpeed == speed[1])
				speedSelected = 5;
			else if(selectedSpeed == speed[2])
				speedSelected = 10;
			
			
			if(selectedAlgo == "Selection") //if algorithm selected is Selection
			 try 
				{
					doSelectionSort(intList);//method call for selection sort
				} 
			catch (InterruptedException e) //if there is any exception occured
				{
					e.printStackTrace();
				}
			else if(selectedAlgo == algorithms[2]) //if algorithm selected is Insertion
				try {
						doInsertionSort(intList); //method call for insertion sort
					}
			catch (InterruptedException e) 
				{
					
					e.printStackTrace();
				}
			else if(selectedAlgo == algorithms[0]) //if algorithm selected is bubble sort
				try 
				{
					bubble_srt(intList); //method call for bubble sort
				} 
			catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
			else if(selectedAlgo == algorithms[3]) //if algorithm selected is quick sort
				try 
				{
					doQuickSort(0,319); //method call for quick sort
				} 
			catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
			
				populateButton.setEnabled(true); //populate button is enabled
				sortButton.setEnabled(false); //sort button is disabled
		}
		
		
		/*Method paintComponent is used to fill the panel with lengths depending upon variables in intList array*/
		@Override
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g); //calls super method from base class
			
			g.setColor(Color.blue);
			
			if(intList.length != 0) //if the length of the array is not equal to 0 then the following loop is executed
			
			for(int i=0,j=0;i<320;++i,++j) 
			{
				g.drawLine(i, 219-intList[i], i,320 ); //draws lines on to the panel
				
				//g.setColor(Color.blue);
			}
			
		}
		
		/* Method which is implicitly called upon creation of thread*/
		public void start(String abc,String Speed)
		{
			selectedAlgo = abc; //passed parameter is stored into selectedAlgo string variable
			selectedSpeed = Speed; //passed parameter is stored into selectedSpeed string variable
			
			t = new Thread(this,"Thread1"); //threat t is created with name Thread1
			t.start(); //Thread starts here
			
		}
		
		/* Method which makes the selection sort happen, it takes an int array as argument*/
		public  int[] doSelectionSort(int[] arr) throws InterruptedException
		{
	         
	        for (int i = 0; i < arr.length - 1; i++) //logic for the sort starts here
	        {
	            int index = i; //value of i is stored
	            for (int j = i + 1; j < arr.length; j++) 
	                if (arr[j] < arr[index])  //if the second element is greater than first
	                    index = j; //then first is set to second array 
	      
	            int smallerNumber = arr[index];   //stores the value of second array element as index is set to second
	            arr[index] = arr[i]; //element swapping takes place here
	            arr[i] = smallerNumber;
	            repaint(); //panel is repainted
	            Thread.sleep(speedSelected); //thread is put to sleep 
	            
	        }
	        return arr;
	    }
		
		
		/* Method which makes the insertion sort happen, it takes an int array as argument*/
		public int[] doInsertionSort(int[] input) throws InterruptedException{
	         
	        int temp;
	        for (int i = 1; i < input.length; i++)  //logic for the sort starts here
	        {
	            for(int j = i ; j > 0 ; j--)
	            {
	                if(input[j] < input[j-1])
	                {
	                	//element swapping takes place here
	                    temp = input[j];
	                    input[j] = input[j-1];
	                    input[j-1] = temp;
	                }
	                repaint(); //panel is repainted
		            Thread.sleep(speedSelected); //thread is put to sleep 
	            }
	        }
	        return input;
	    }
		
		/* Method which makes the bubble sort happen, it takes an int array as argument*/
		 public void bubble_srt(int array[]) throws InterruptedException 
		 {
		        int n = array.length;
		        int k;
		        for (int m = n; m >= 0; m--) //logic for the sort starts here
		        {
		            for (int i = 0; i < n - 1; i++) 
		            {
		                k = i + 1;
		                if (array[i] > array[k]) 
		                {
		                	int temp; //element swapping takes place here
		                    temp = array[i];
		                    array[i] = array[k];
		                    array[k] = temp;
		                    repaint(); //panel is repainted
		                    Thread.sleep(speedSelected); //thread is put to sleep
		                }
		            }
		        }
		 }
		 
		 private void doQuickSort(int lowerIndex, int higherIndex) throws InterruptedException {
	         
		        int i = lowerIndex;
		        int j = higherIndex;
		        // calculate pivot number, I am taking pivot as middle index number
		        int pivot = intList[lowerIndex+(higherIndex-lowerIndex)/2];
		        // Divide into two arrays
		        while (i <= j) {
		            /**
		             * In each iteration, we will identify a number from left side which 
		             * is greater then the pivot value, and also we will identify a number 
		             * from right side which is less then the pivot value. Once the search 
		             * is done, then we exchange both numbers.
		             */
		            while (intList[i] < pivot) 
		            {
		                i++;
		            }
		            while (intList[j] > pivot) 
		            {
		                j--;
		            }
		            if (i <= j) 
		            {
		            	int temp = intList[i];
		                intList[i] = intList[j];
		                intList[j] = temp;
		                //move index to next position on both sides
		                i++;
		                j--;
		                repaint(); //panel is repainted
		                Thread.sleep(speedSelected); //thread is put to sleep
		            }
		        }
		        // call quickSort() method recursively
		        if (lowerIndex < j)
		            doQuickSort(lowerIndex, j);
		        if (i < higherIndex)
		            doQuickSort(i, higherIndex);
		    }
		 
		 
	}
	
	/* method to fill the panel with integer array values*/
	void fillArray()
	{
		int min = 0; //max and min length of the line drawn on panel are defined
		int max = 219;
		Random rand = new Random(System.currentTimeMillis()); //new random object is created
		
		for(int i=0;i<320;++i)
		{
			/* List is populated with random integers*/
			intList[i] = rand.nextInt((max-min)+1)+min; 
			
		}
		sortButton.setEnabled(true); //sort button is enabled
	}
	
	/* Button Listener class to set what to do when a button is clicked */
	class PopulateButtonListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			fillArray(); //integer array is filled
			
			sap.repaint(); //panel is filled with arrays
			 
		}
		
	}
	
	/* Button Listener class to set what to do when a button is clicked */
	class SortButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			/*Buttons are disabled as sorting will take place*/
			populateButton.setEnabled(false); 
			sortButton.setEnabled(false);
			/* gets the user selection */
			String selectedValue = (box.getSelectedItem()).toString();
			String selectedSpeed = (speedBox.getSelectedItem().toString());
			/*creates and starts a new thread */
			((SortAnimationPanel) sap).start(selectedValue,selectedSpeed);
			
		}
	}
}
