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

/* Import statements for swing components*/
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.BorderFactory;
import javax.swing.SpinnerListModel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JOptionPane;


/* Import statements for awt components*/
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import java.util.Scanner; //import for scanner object
import java.io.File; //import for file accessing
import java.io.FileNotFoundException; //import for exception

/* subclass of JFrame*/
public class FrameTwo extends JFrame
{
	//variables to set width and height of frame
	private final int FRAME_WIDTH = 700;
	private final int FRAME_HEIGHT = 300;
	
	private static int miles; //to store miles eneter by user
	
	private String[] cityNames; //to store cityNames
	private String month; //to store month selected
	
	//private String fileName; //to store name of file
	
	private JList list; //to create a jList
	/* To create two panels one for destinations and other for redeem miles*/
	private JPanel panel;
	private JPanel panel2;
	
	/*JLabels for displaying labels to text fields, text area and spinner */
	private JLabel normalLabel;
	private JLabel superLabel;
	private JLabel upgradeLabel;
	private JLabel dateLabel;
	private JLabel milesLabel;
	private JLabel monthsLabel;
	private JLabel remainingMilesLabel;
	
	/* JTextFields for displaying values */
	private JTextField NormalField;
    private JTextField SuperSaverField;
    private JTextField UpgradeField;
    private JTextField DateField;
    
    private JTextField milesField; //To take input miles from user
	private JTextField remainingMilesField;
	
	/* JButton to redeem miles*/
    private JButton redeemButton;
    
    
    private SpinnerListModel monthModel; //to store the month model
    
    private JSpinner spinner; //to store JSpiiner
    
    private JTextArea outputText; //TO display the output in the text area
    
    private JScrollPane pane1;
    private JScrollPane pane2;
    
    
    MileRedeemer redeemerObj; //Object of type MileRedeemer
    
	FrameTwo() throws FileNotFoundException
	{
		setTitle("Mile Redemption App"); //To set the title of the frame
		/* Below are the functions for creating JLabels,JTextfield,JTexarea,JPanel,Spinner,JList */
		createScanner();
		
		createList();
		createLabel();
		createTextField();
		
		createSpinner();
		createButton();
		createTextArea();
		createPanel1();
		createPanel2();
		
		setSize(FRAME_WIDTH,FRAME_HEIGHT);//To set the size of the frame
	}
	
	/* function to create JList and display the contents*/
	private void createList()
	{
		list = new JList<String>(cityNames); //list stores Strings from string array cityNames
		//list.setFixedCellWidth(340); // To set cell width of list
		
		
		/*Mouse listener to display the miles in the respective text fields on selecting a string from JList*/
		MouseListener mouseListener = new MouseAdapter() 
		{
			public void mouseClicked(MouseEvent e) {
		        if ((e.getClickCount() == 2)||(e.getClickCount() == 1) ) //If mouse is clicked once or a double click then this loop is executed
		        {


		           String selectedItem = (String) list.getSelectedValue(); //stores the select item
		         
		           Destination d = redeemerObj.findDestination(selectedItem); //Object of type Destination is stored in d
		           String str = getMonthName(d.getStartMonth()); //stores the start month name into str
		           str = str+"-"+getMonthName(d.getEndMonth()); //str is appended with end month name
		            
		           /* text fields displays their respective miles */
		           NormalField.setText(Integer.toString(d.getNormalMiles())); 
		           SuperSaverField.setText(Integer.toString(d.getSuperSaverMiles()));
		           UpgradeField.setText(Integer.toString(d.getAdditionalMiles()));
		           DateField.setText(str);
		         }
		    }

		};
		list.addMouseListener(mouseListener);//Mouse listener is added to list
		
	}
	
	/* Below function  creates the labels */
	private void createLabel()
	{
		normalLabel = 	new JLabel("Normal Miles");
		superLabel = 	new JLabel("Supersaver Miles ");
		upgradeLabel = 	new JLabel("Upgrade Cost");
		dateLabel = 	new JLabel("Supersaver Dates");
		milesLabel = new JLabel("Enter your miles");
		monthsLabel = new JLabel("Select the month of departure");
		remainingMilesLabel = new JLabel("Your remaining miles");
	}
	
	/* Below function creates fields and are set to empty and not editable */
	private void createTextField()
	{
		final int FIELD_WIDTH = 18; //To set the width of text fields

		
	    NormalField = new JTextField(FIELD_WIDTH);
	    NormalField.setText("");
	    NormalField.setEditable(false);
	    
	    
	    SuperSaverField = new JTextField(FIELD_WIDTH);
	    SuperSaverField.setText("");
	    SuperSaverField.setEditable(false);
	    
	    UpgradeField = new JTextField(FIELD_WIDTH);
	    UpgradeField.setText("");
	    UpgradeField.setEditable(false);
	    
	    DateField = new JTextField(FIELD_WIDTH);
	    DateField.setText("");
	    DateField.setEditable(false);
	    
	    /*Miles field is editable to allow the user to enter the input*/
	    milesField = new JTextField(FIELD_WIDTH);
	    milesField.setText(""); 
	    
	    remainingMilesField = new JTextField(16);
	    remainingMilesField.setText("");
	    remainingMilesField.setEditable(false);
	    
	    
	}
	
	/* Function to create button */
	private void createButton()
	{
		
		redeemButton = new JButton("Redeem Miles"); //Button created
		
		Dimension d = redeemButton.getPreferredSize(); //to specify dimensions of button
		d.width = 340; //width specified
		redeemButton.setPreferredSize(d); //Width of button is set
		
		ActionListener listener = new AddButtonListener(); //listener object is created 
		redeemButton.addActionListener(listener); //object is set to button
		
		
	}
	
	/* Method to create spinner */
	private void createSpinner()
	{
		String[] monthStrings = getMonthStrings(); //get month names
		monthModel = new SpinnerListModel(monthStrings);  //model for spinner is created
		spinner = new JSpinner(monthModel); // sinner is created
		spinner.setEditor(new JSpinner.DefaultEditor(spinner)); //spinner can't be edited
		
		/* Width for spinner is set */
		Dimension d = spinner.getPreferredSize();
		d.width = 90;
		spinner.setPreferredSize(d);
		
	}
	
	/* Function to create text area*/
	private void createTextArea()
	{
		outputText = new JTextArea(10,30); //text area created 
		pane2 = new JScrollPane( outputText ); //scroll pane for output text is created
		outputText.setEditable(false); //text area can't be edited
		//pane2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	}
	
	
	/* function to create scanner */
	private void createScanner() throws FileNotFoundException
	{
		Scanner fileInput = new Scanner(new File(FrameOne.getFile())); //Scanner created
		  
	    redeemerObj = new MileRedeemer(); //MileRedeemer object created
	    redeemerObj.readDestinations(fileInput); //reads destination objects
	    cityNames = redeemerObj.getCityNames(); //stores cityNames of all objects of type destination
	}
	
	
	/* function to create panel and add components into it */
	private void createPanel1()
	{
		panel = new JPanel(); //panel created
		JScrollPane scrollPane = new JScrollPane(list); //scroll pane created
		scrollPane.add(list); //scroll pane added to list
		panel.setPreferredSize(new Dimension(350,350)); //panel size is set
	
		/*Title for the panel is set*/
		TitledBorder title; 
		/* Title name Destinations is set to title*/
	    title = BorderFactory.createTitledBorder("Destinations"); 
	    title.setTitleJustification(TitledBorder.CENTER); //Title is justified to right
		panel.setBorder(title); //title applied to panel
		
		/*Layout for panel is set*/
		panel.setLayout(new GridBagLayout()); 
		GridBagConstraints gbc = new GridBagConstraints();//constraints are declared
		
		/*Width and height of cell are determined*/
		gbc.gridwidth = 2;
		gbc.gridheight = 2;
		
		
		
		gbc.fill = GridBagConstraints.BOTH;
		
		gbc.weightx = 2;
		gbc.weighty = 1;
		
		pane1 = new JScrollPane(list);
				
		panel.add(pane1,gbc); //list is added to panel
		
		/*Width and height of cell are determined*/
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		
		/*Panel is created for JLabels and JTextFields */
		JPanel panel_a = new JPanel();
		GridBagLayout layout2 = new GridBagLayout(); 
		panel_a.setLayout(layout2); //layout set for panel
		
		/*Constraints for a row are described below*/
		gbc.fill = GridBagConstraints.HORIZONTAL;
	    
		/*Describes the position of the grid*/
		gbc.gridx = 0;
	    gbc.gridy = 0;
	      
	    panel_a.add(normalLabel,gbc);  //label added to panel
	     
	     /*Describes the position of the grid*/
	     gbc.gridx = 1;
	     gbc.gridy = 0;
	      
	     panel_a.add(NormalField,gbc); //field added to panel
	     
	     /*Constraints for a row are described below*/
	     gbc.fill = GridBagConstraints.HORIZONTAL;
	     
	     /*Describes the position of the grid*/
	     gbc.gridx = 0;
	     gbc.gridy = 1;
	     panel_a.add(superLabel,gbc); //label added to panel
	     
	     /*Describes the position of the grid*/
	     gbc.gridx = 1;
	     gbc.gridy = 1;
	     panel_a.add(SuperSaverField,gbc); //text field added to panel
	     
	     /*Constraints for a row are described below*/
	     gbc.fill = GridBagConstraints.HORIZONTAL;
	     
	     /*Describes the position of the grid*/
	     gbc.gridx = 0;
	     gbc.gridy = 2;
	     panel_a.add(upgradeLabel,gbc); //label added to panel
	     	
	     /*Describes the position of the grid*/
	     gbc.gridx = 1;
	     gbc.gridy = 2;
	     panel_a.add(UpgradeField,gbc); //field added to panel
	     
	     /*Constraints for a row are described below*/
	     gbc.fill = GridBagConstraints.HORIZONTAL;
	     
	     /*Describes the position of the grid*/
	     gbc.gridx = 0;
	     gbc.gridy = 3;
	     panel_a.add(dateLabel,gbc); //label added to panel
	     
	     /*Describes the position of the grid*/
	     gbc.gridx = 1;
	     gbc.gridy = 3;
	     panel_a.add(DateField,gbc); //field added to panel
	     
	     panel_a.setBackground(new Color(255,255,77));	
	     panel.add(panel_a,gbc); //panel added to another panel
		
	    /*panel color is set*/
		panel.setBackground(new Color(255,255,77));
		add(panel); //panel added to frame
		getContentPane().add(panel,BorderLayout.WEST); //panel is adjusted to the west(right) side of the frame
		
		
		
	}
	
	/*function to create panel for the redeem miles and assign to the left of the frame*/
	private void createPanel2()
	{
		panel2 = new JPanel(); //panel is created 
		panel2.setPreferredSize(new Dimension(350,350)); //panel size is set
		
		/*Title for the panel is set*/
		TitledBorder title; 
		/* Title name Destinations is set to title*/
	    title = BorderFactory.createTitledBorder("Redeem Miles");//title name is specified
	    title.setTitleJustification(TitledBorder.CENTER); //title justified to center
		panel2.setBorder(title); //title is set
		
		/* JLabels,JTextfields,spinner,textarea*/
		panel2.add(milesLabel);
		panel2.add(milesField);
		panel2.add(monthsLabel);
		panel2.add(spinner);
		panel2.add(redeemButton);
		panel2.add(pane2);
		panel2.add(remainingMilesLabel);
		panel2.add(remainingMilesField);
		
		panel2.setBackground(new Color(100,100,77)); //panel color is set
		add(panel2); //panel is added to frame
		getContentPane().add(panel2,BorderLayout.EAST); //panel is adjusted to the east or left side 
	}
	/* static function to return the miles*/
	public static int getMiles()
	{
		return miles;
	}
	
	/* function takes string month and returns the integer corresponding to it*/
	private int getMonth(String month)
	{
		int integerMonth = 0;
		if(month.equals("January"))
			integerMonth = 1;
		else if(month.equals("February"))
			integerMonth = 2;
		else if(month.equals("March"))
			integerMonth = 3;
		else if(month.equals("April"))
			integerMonth = 4;
		else if(month.equals("May"))
			integerMonth = 5;
		else if(month.equals("June"))
			integerMonth = 6;
		else if(month.equals("July"))
			integerMonth = 7;
		else if(month.equals("August"))
			integerMonth = 8;
		else if(month.equals("September"))
			integerMonth = 9;
		else if(month.equals("October"))
			integerMonth = 10;
		else if(month.equals("November"))
			integerMonth = 11;
		else 
			integerMonth = 12;
		
		return integerMonth;
	}
	

	/*Inner class that implements ActionListner interface to perform action when button is clicked*/
	class AddButtonListener implements ActionListener
	{
	
		/*Abstract method that needs to be set to be instantiated*/
	    public void actionPerformed(ActionEvent event)
	    {
	    	//try catch block to catch exceptions if there are any
	    	try 
	    	{
		    	miles = Integer.parseInt(milesField.getText()); //stores the input given by user
		    	month = (String) spinner.getValue(); //gets the month selected by user
		    	
		    	outputText.setText(""); //If there is any text in textarea then it is cleared off
		    	
		    	int integerMonth = getMonth(month); //integer of month is obtained
		    	
		    	/* String array that stores the output that needs to be displayed */
		    	String[] values = redeemerObj.redeemMiles(miles,integerMonth); 
		    	
		    	if(values.length != 0)
			    	for(String str:redeemerObj.redeemMiles(miles, integerMonth))
			    	{
			    		outputText.append(str+"\n"); //Each string is displayed one after other in text area
			    	}
		    	//if the string array is empty then this is displayed in text area
		    	else outputText.setText("Not enough miles "); 
		   
		    	/*Stores the remaining miles in an int*/
		    	int remainingMiles = redeemerObj.getRemainingMiles(); 
		    	String strMiles = Integer.toString(remainingMiles); //converted to string
		    	remainingMilesField.setText(strMiles); //displayed in the textfield
		    	
	    	}
	    	catch(Exception e)
	    	{
	    		/*display error on the screen if user inputs anything other than integer*/
	    		JOptionPane.showMessageDialog(null, "Input error");
	    		remainingMilesField.setText("");
	    		milesField.setText("");
	    		outputText.setText("");
	    	}
	    	
	    }
	}
	
	
	/*Function to populate JSpinner*/
	protected String[] getMonthStrings() 
	{
	  String[] months = new java.text.DateFormatSymbols().getMonths();
	  
	  int lastIndex = months.length - 1;
	  
	  if (months[lastIndex] == null || months[lastIndex].length() <= 0)  
	  { 
	    String[] monthStrings = new String[lastIndex];
	    System.arraycopy(months, 0, monthStrings, 0, lastIndex);
	    return monthStrings;
	  }
	  else 
	  { 
	    return months;
	  }
	}
	
	/*Function that takes integer input and returns corresponding month name */
	private String getMonthName(int num)
	{
		String month;
		String[] str = {"January",      
				   "February",
				   "March",        
				   "April",        
				   "May",          
				   "June",         
				   "July",         
				   "August",       
				   "September",    
				   "October",      
				   "November",     
				   "December"};//String array that stores month
		if(num<=str.length)
			month = str[num-1];
		else month = "";
		return month;
		
	}
	

	
}

