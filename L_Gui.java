import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.*;

import java.util.*;

class L_Gui extends JFrame {
	// Variable declarations

	//labels of the textfields
    private JLabel		jLabelGen;
    private JLabel		jLabelAx;
    private JLabel		jLabelRule;
    private JLabel		jLabelAng;
    private JLabel		jLabelScale;
    private JLabel		jLabelX;
    private JLabel		jLabelY;
    private JLabel		jLabelDir;
    private JLabel		jSpace1;
    private JLabel		jSpace2;
    private JLabel		jSpace3;
    
    //textfields for parameters
    private JTextField	jTextFieldGen;
    private JTextField	jTextFieldAx;
    private JTextField	jTextFieldRule;
    private JTextField	jTextFieldAng;
    private JTextField	jTextFieldScale;
    private JTextField	jTextFieldX;
    private JTextField	jTextFieldY;
    private JTextField	jTextFieldDir;
    
    //buttons
    private JButton		drawButton;
    private JButton		loadButton;  
    private JButton		chooseButton;

    //Drawing surfae & panel of parameters
    private DrawBot		drawingSurface;
    private JPanel		controlPanel;

	//default constructor  
    public L_Gui() {
        initComponents();
    }
    
    //all the initializations in one method
    private void initComponents() {
    	//create all textfield labels
        jLabelGen	= new JLabel("generation");
        jLabelAx	= new JLabel("axiom");
        jLabelRule	= new JLabel("rule");
        jLabelAng	= new JLabel("angle");
        jLabelScale	= new JLabel("scale");
        jLabelX		= new JLabel("x-coor");
        jLabelY		= new JLabel("y-coor");
        jLabelDir	= new JLabel("direction");
        jSpace1		= new JLabel("");
        jSpace2		= new JLabel("");
        jSpace3		= new JLabel("");
        
        //create all textfields
        jTextFieldGen	= new JTextField();
        jTextFieldAx	= new JTextField();
        jTextFieldRule	= new JTextField();
        jTextFieldAng	= new JTextField();
        jTextFieldScale	= new JTextField();
        jTextFieldX		= new JTextField();
        jTextFieldY		= new JTextField();
        jTextFieldDir	= new JTextField();
        
        //set the generation to 0
        jTextFieldGen.setText("0");
        
        //create the buttons
        drawButton	= new JButton("DRAW");
        loadButton = new JButton("LOAD");
        chooseButton	= new JButton("CHOOSE");
        
        //create a drawing surface
        drawingSurface = new DrawBot();
        drawingSurface.setBackground(new Color(255, 255, 255));
        
        //create the panel for the buttons
        controlPanel = new JPanel();
        controlPanel.setPreferredSize( new Dimension(100,600));
        controlPanel.setLayout(new FlowLayout());
        controlPanel.setBackground(new Color(225, 102, 102));
        //set the Layout to FlowLayout
        getContentPane().setLayout(new FlowLayout());

        //set text field alignments
        jTextFieldGen.setPreferredSize( new Dimension(80,25));
        jTextFieldAx.setPreferredSize( new Dimension(80,25));
        jTextFieldRule.setPreferredSize( new Dimension(80,25));
        jTextFieldAng.setPreferredSize( new Dimension(80,25));
        jTextFieldScale.setPreferredSize( new Dimension(80,25));
        jTextFieldX.setPreferredSize( new Dimension(80,25));
        jTextFieldY.setPreferredSize( new Dimension(80,25));
        jTextFieldDir.setPreferredSize( new Dimension(80,25));
        
        jTextFieldGen.setHorizontalAlignment(JTextField.CENTER);
        jTextFieldAx.setHorizontalAlignment(JTextField.CENTER);
        jTextFieldRule.setHorizontalAlignment(JTextField.CENTER);
        jTextFieldAng.setHorizontalAlignment(JTextField.CENTER);
        jTextFieldScale.setHorizontalAlignment(JTextField.CENTER);
        jTextFieldX.setHorizontalAlignment(JTextField.CENTER);
        jTextFieldY.setHorizontalAlignment(JTextField.CENTER);
        jTextFieldDir.setHorizontalAlignment(JTextField.CENTER);
        
        jSpace1.setPreferredSize( new Dimension(80,20));
        jSpace2.setPreferredSize( new Dimension(80,20));
        jSpace3.setPreferredSize( new Dimension(80,20));
        
        //add a WindowListener to terminate the program when 
        //window is closed
        addWindowListener(new WindowAdapter() {
          public void windowClosing(WindowEvent evt) {
              System.exit(1);
          }
        });
        
        //Set up drawButton & add action listener
        drawButton.setPreferredSize( new Dimension(80,25));
        drawButton.setBackground(new Color(235, 235, 235));
        drawButton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent evt) {
        	  //each time draw button is clicked, it reads the next iteration 
        	  //and draws the drawing
        	  drawingSurface.readIns();
        	  
          }
        });//---end of Draw Button Action Listener
        
        //Fix up chooseButton -- allows you to choose a file to load
        chooseButton.setPreferredSize( new Dimension(80,25));
        chooseButton.setBackground(new Color(235, 235, 235));
        chooseButton.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent evt){
        	 //opens up a file choose
          	 JFileChooser c = new JFileChooser();
               //Demonstrate "Open" dialog:
               int rVal = c.showOpenDialog(L_Gui.this);
               if (rVal == JFileChooser.APPROVE_OPTION) {
            	   
            	   File file = c.getSelectedFile();
            	
            	   try {
            		   //scans the file, and sends it to loadData for data to be read & loaded
            		   Scanner sc = new Scanner(file);
            		   loadData(sc);
            	   } catch (FileNotFoundException e) {
						e.printStackTrace();
            	   }
               }//---end of file chooser
           	}
        	});//----end of choose button
        
        //Fix up loadButton -- allows you to load default file
        loadButton.setPreferredSize( new Dimension(80,25));
        loadButton.setBackground(new Color(235, 235, 235));
        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
				try {
					//scans the default file & loads the data
					Scanner sc = new Scanner(new File("l-system1.txt"));
					loadData(sc);
				} catch (FileNotFoundException e){
					System.out.println("NO File found");
					System.exit(0);
				}
            }
         });
        
        //Add the components to the control panel
        controlPanel.add(jSpace1);
        controlPanel.add(jTextFieldAx);        
        controlPanel.add(jLabelAx); 
        controlPanel.add(jTextFieldRule);        
        controlPanel.add(jLabelRule); 
        controlPanel.add(jTextFieldAng);
        controlPanel.add(jLabelAng);       
        controlPanel.add(jTextFieldScale);
        controlPanel.add(jLabelScale); 
        controlPanel.add(jTextFieldX);
        controlPanel.add(jLabelX); 
        controlPanel.add(jTextFieldY);
        controlPanel.add(jLabelY); 
        controlPanel.add(jTextFieldDir);
        controlPanel.add(jLabelDir); 
        controlPanel.add(jSpace2);
        controlPanel.add(loadButton);
        controlPanel.add(chooseButton);
        controlPanel.add(drawButton);
        controlPanel.add(jSpace3);
        controlPanel.add(jTextFieldGen);        
        controlPanel.add(jLabelGen);
        
        // add the panels to the L_GUI
        getContentPane().add(controlPanel);
        getContentPane().add(drawingSurface);
        
        pack();
        setVisible(true);
        
    }//---end of InitComponents
    
    //loads the data into to the text fields
    private void loadData (Scanner sc) {
	    	jTextFieldAx.setText(sc.nextLine());
			jTextFieldRule.setText(sc.nextLine());
			jTextFieldAng.setText(sc.nextLine());
			jTextFieldScale.setText(sc.nextLine());
			jTextFieldX.setText(sc.nextLine());
			jTextFieldY.setText(sc.nextLine());
			jTextFieldDir.setText(sc.nextLine());
    }
    
    //inner class - DrawBot
    private class DrawBot extends JPanel{
    	
   	    private String axiom;
 	    private String rule;
 	    private double angle;	
 	    private int scale;
 	    //initial x-coordinate position
 	    private int Xcoor;	
 	    //initial y-coordinate position
 	    private int Ycoor;	
 	    private double direction;
 	    private int generation;
 	    //maximum # of generations
 	    private int MAX_Gen = 50;	
 	    //S is the derived instruction after the new generation 
 	    private String S;
 	    //array list of lines to draw
 	    private ArrayList<Line> lines;
 	    //Array of charcters for axiom
 	    char [] ax;
 	    //stack - for branches
 	    Stack<Integer> branches;

 	    //constructor
        public DrawBot(){
 		    setBackground(Color.white);
 		    setPreferredSize( new Dimension( 600,600));
 		    
 		    lines = new ArrayList<Line>();
 		    branches = new Stack<Integer>();
 		   
        }
        
        //get the data in the textfields 
        public void getData(){
      	  axiom = jTextFieldAx.getText();
      	  rule = jTextFieldRule.getText();
      	  angle = Double.parseDouble(jTextFieldAng.getText());
      	  scale = Integer.parseInt(jTextFieldScale.getText());
      	  Xcoor = Integer.parseInt(jTextFieldX.getText());
      	  Ycoor = Integer.parseInt(jTextFieldY.getText());
      	  direction = Double.parseDouble(jTextFieldDir.getText());
      	  generation = Integer.parseInt(jTextFieldGen.getText());
        }
        
        //reads the instruction - deciphers and adds lines into array to draw
        void readIns() {
        	//clear the array of lines form previous generation
        	lines.clear();
        	//gets the data from the textfield 
        	getData();
        	
        	if (generation > MAX_Gen) {
        		System.out.println("Max Generation Reached");
				System.exit(0);
        	}
        	else {
	        	//if generation is 0, set the derived String to the axiom
	        	if (generation == 0) {
	        		S = axiom;
	        		//convert into character array
	        		ax = axiom.toCharArray();
	        	}
	        	//if generation is greater than 0, then replace all Fs with the rule, 
	        	//to create new derived instruction
	        	else {
		        	S = S.replace("F", rule);
		        	//convert into caharacter array
		        	ax = S.toCharArray();
	        	}
	        	
	        	//iterate through the derivation
	        	for(int i=0; i<ax.length; i++) {
	        		//if character is F, then we need to draw a line (at the angle of current direction
	        		if (ax[i] == 'F') {
	        			int newX =  Xcoor + (int)(scale*Math.cos(Math.toRadians(direction)));
	        			int newY =  Ycoor + (int)(scale*Math.sin(Math.toRadians(direction)));
	        			
	        			Line l = new Line(Xcoor, Ycoor, newX, newY);
	        			lines.add(l);
	        			
	        			//
	        			Xcoor = newX;
	        			Ycoor = newY;
	
	        		}
	        		//if character is a +, we increase direction by defined angle
	        		if (ax[i] == '+') {
	        			direction = (direction + angle)%360;        			
	
	        		}
	        		//if character is a -, we decrease the direction by defined angle
	        		if (ax[i] == '-') {
	        			direction = (360 + direction - angle)%360;
	        		}
	        		//if we encounter brackets we are going branches
	        		//we use a stacl and put these coordinates in a stack 
	        		//when opening bracket encountered [, we push the current X and Y coords onto stack
	        		if (ax[i] == '[') {
	        			branches.push(Xcoor);
	        			branches.push(Ycoor);
	        		}
	        		//when closing bracket encountered ], we pop the X Y coords on top of the stack, and 
	        		//set them to the current X and Y coord
	        		if (ax[i] == ']') {
	        			Ycoor = branches.pop();
	        			Xcoor = branches.pop();
	        		}

	        	}//---end of for loop
	        	
	        	//increment generation and update textfield
	        	generation++;     	
	      	  	jTextFieldGen.setText(Integer.toString(generation));
	        	
	      	  	//repaint the drawing surface (calls paintComponents)
	        	repaint();
	        	
        	}//---end of else
        	
        }//---end of readIns
        
        //paints the drawing
        public void paintComponent(Graphics g){
        	
            super.paintComponent(g);
        	
            //while nothing is drawn, displays the instructions of the GUI
            //ast the start of the program 
            if (generation == 0) {
            	g.drawString("To Load the default file, click 'Load'", 150, 100);
            	g.drawString("To Choose a file, click 'Choose'", 150, 115);
            	g.drawString("To begin drawing the L-system, click Draw", 150, 150);
            	g.drawString("Your file must be of the following format:", 150, 200);
            	g.drawString("Line 1: Initial Axiom (Use 'F')", 150, 215);
            	g.drawString("Line 2: Rule", 150, 230);
            	g.drawString("Line 3: Default Angle of turns by turtle", 150, 245);
            	g.drawString("Line 4: Scale (number of units moved", 150, 260);
            	g.drawString("Line 5: Initial X coordinate", 150, 275);
            	g.drawString("Line 6: Initial Y coordinate", 150, 290);
            	g.drawString("Line 7: Initial direction of angle", 150, 305);
            }
            
            //draw the lines which were saved in the array
            for (Line l : lines) {
        		g.drawLine(l.x1, l.y1, l.x2, l.y2);
        	}
            
        }//---end of painComponent
    	
    }//---end of DrawBot
	
}//---end of class