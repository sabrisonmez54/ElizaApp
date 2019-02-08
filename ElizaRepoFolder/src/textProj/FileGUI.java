package textProj;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import javax.swing.JFrame;

public class FileGUI extends QuestionBank implements ActionListener {
	
	public QuestionBank qb;
	public  JLabel question;
	private JPanel jp2;
	private JPanel jpMain;
	private JFrame	frame;
	public int clicked;
	private JTextField response;
	private JButton nextBtn;
	private JButton startBtn;
	private JLabel startScreen; 
	private int QbIndex;
	public String responseLong;
	
	FileGUI(){
		
		//JPANEL
		jpMain = new JPanel();
		jpMain.setBackground(Color.LIGHT_GRAY);
		jpMain.setLayout(new GridLayout(6,2));
		
		qb = new QuestionBank();
		
		question = new JLabel("");
		 
		jp2 = new JPanel();
		startScreen = new JLabel("Welcome to Eliza. Press start to begin.");
		jp2.add(startScreen);
		startBtn = new JButton("Start Session");
		jp2.add(startBtn);
		startBtn.addActionListener(new ActionListener() {
			 
		        public void actionPerformed(ActionEvent e) {
		        	
		            jp2.setVisible(false);
		            jpMain.setVisible(true);
		            String nquestion = qb.getNextQuestion2();
		            question.setText(nquestion);
		            frame.add(jpMain);
		            jpMain.add(question);
		            frame.repaint();
		            
		        }
		        
	    } );

		 response = new JTextField(" ");
		 jpMain.add(response);
		 
		 nextBtn = new JButton("Next Question");
		 nextBtn.setActionCommand(qb.usResponse);
		 jpMain.add(nextBtn);
		 nextBtn.addActionListener(this);
	        	
	//JFRAME
	frame = new JFrame("ELIZA");
    frame.setSize(700, 700);
	frame.add(jp2);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setVisible(true);
	frame.setLocationRelativeTo(null);

	}
	
	public void actionPerformed(ActionEvent e) {
		
		qb.WriteResponse(response.getText());
		
		clicked++;
	
		question.setText(qb.getNextQuestion2());
		
		response.setText("");
	    	if(clicked>9) {
	    		
			nextBtn.setVisible(false);
			question.setVisible(false);
			response.setVisible(false);
			
			JButton finishBtn = new JButton("Finish session and View all Q & A");
			jpMain.add(finishBtn);
			
			JTextArea allWords = new JTextArea();
			JScrollPane jScroll = new JScrollPane(allWords);
			
			allWords.setWrapStyleWord(true);
			allWords.setLineWrap(true);
			allWords.setOpaque(false);
			allWords.setEditable(false);
			allWords.setFocusable(false);
			allWords.setBackground(Color.orange);
			allWords.setFont(UIManager.getFont("Label.font"));
			allWords.setBorder(UIManager.getBorder("Label.border"));
			
			jpMain.add(jScroll);
			jScroll.setVisible(false);
						
			JButton longWords = new JButton("View Longest Words");
			jpMain.add(longWords);
			longWords.setVisible(false);
			
			JButton alphabeticWords = new JButton("View Words Alphabetically");
			jpMain.add(alphabeticWords);
			alphabeticWords.setVisible(false);;
			
		finishBtn.addActionListener(new ActionListener() {
		       public void actionPerformed(ActionEvent e) {
		       jpMain.remove(question);
		       jpMain.remove(response);
		       finishBtn.setVisible(false);
		       jScroll.setVisible(true);
		       longWords.setVisible(true);
		       alphabeticWords.setVisible(false);
		      
		       String allOfthem = "";
		       String longAnalysis ="";
		       String finalAnalysis ="";
		       
		       int num = 0;
		       
		    	   for (QuestionClass item: qb.QuestionsAndAnswers) {
		    		   
		    		   num++;
		    		   allOfthem = allOfthem + " Q" + num + ": " + item.question + " answer: " + item.usResponse.trim() + System.lineSeparator(); 
		    		   longAnalysis = longAnalysis.trim() + item.usResponse.trim();
		    		   
		       }
		    	   
		    	   finalAnalysis ="wow " + Arrays.stream(longAnalysis.trim().split(" ")).max(Comparator.comparingInt(String::length)).orElse(null) + " and " + 
		    	   Arrays.stream(longAnalysis.trim().split(" ")).min(Comparator.comparingInt(String::length)).orElse(null) + " seem very important to you.";
		 		  
		    	   allWords.setText(allOfthem + System.lineSeparator() + " Session Analysis : " + finalAnalysis);  	   
	    
		        }
			});
		
					longWords.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
			
						longWords.setVisible(false);
							
						String longResponse= "";
						
						 for (QuestionClass item: qb.QuestionsAndAnswers) {
				    		
				    		   longResponse = longResponse + item.usResponse;
		
						 }
						 
						 String[] parts = longResponse.split("\\s+");   
						 Arrays.sort(parts, (a, b)->Integer.compare(b.length(), a.length()));
						 StringBuilder sb = new StringBuilder();  
						 for(String s:parts){
						    sb.append(s);  
						    sb.append(" ");  
						 }  

						 String sorted = sb.toString().trim();  
						 allWords.setText(sorted);
						 
						 alphabeticWords.setVisible(true);
							
						}
					
					});
					
				    alphabeticWords.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {

							longWords.setVisible(false);
													 
							String alphaResponse= allWords.getText();
							
							String[] parts2 = alphaResponse.toString().split("\\s+");   
							 Arrays.sort(parts2);  
							 StringBuilder sb2 = new StringBuilder();  
							 for(String s:parts2){
								sb2.append(s);  
							    sb2.append(" ");  
							 }  

							 String sorted2 = sb2.toString().trim();  
							 allWords.setText(sorted2);
						}
					});
			}		        
	    	}		
	}