package com.jackanalyzer;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

///< JackProcessor
/*!
 * This FA processes the file character by character.  See Parser_FA figure in the report
 * for visualization.
 */
class JackProcessor{

	
	@Getter private ArrayList<String> stringTokens;	///< String tokens to be passed to the automaton
	@Setter private File fin;						///< File to read tokens from
	private Set<Character> symbols;					///< Symbols moving this FA







	///< JackProcessor
	/*!
	 * Given an input file get the string tokens from it.
	 */
	public JackProcessor(File inJack) throws IOException{
		symbols = new HashSet<Character>();
		setSymbols();
		setFin(inJack);
		stringTokens = new ArrayList<String>();
		parseInput();
	}// end JackProcessor(File inJack)
	






	
	///< parseInput
	/*!
	 * This could be moved into a machine definition file and read into the FiniteAutomaton
	 * but I'm pressed for time and thus doing it explicitly.
	 */
	private void parseInput() throws IOException{
		
		StringBuilder sb = new StringBuilder();		///< Read file into SB
		String fileString;							// easer to read positionally from strings than SB
        BufferedReader bReader = new BufferedReader( Files.newBufferedReader(fin.toPath())); 
        
        // read all lines from the file
        String line = bReader.readLine(); 
        while(line != null){ 
	        	sb.append(line + "\n"); 
	        	line = bReader.readLine(); 
        }// end while(line != null)  
        
        fileString = sb.toString();
        		
    	int currentState = 0;
    	int finalState = 0;
    	char c;
    	String str = "";
    	
    	
    	for(int i = 0; i < fileString.length(); i++){
    		
    		c = fileString.charAt(i);
    		
    		if(currentState == 0){
    			if(c == '\n' || c == ' ')
    				finalState = 0;
    			else if(c == '/'){
    				finalState = 1;
    				str = str + c;
    			}// end else if(c == '/') 
    			else if(c == '"'){
    				str = str + c;
    				finalState = 10;
    			}// end else if(c == '"')
    			else if(symbols.contains(c) == true){
    				finalState = 0;
    				str = ""+ c;
        			stringTokens.add(str.trim());
        			str = "";
    			}// end else if(symbols.contains(c) == true)
    			else{
    				finalState = 9;
    				str = str + c;
    			}// end else
    		}// end if(currentState == 0)
    		

    		if(currentState == 1){
    			if(c == '/'){
    				finalState = 3;
    				str = str + c;
    			}//end if(c == '/')
    			else if(c == '*'){
    				finalState = 5;
    				str = str + c;
    			}// end else if(c == '*')
    			else if(c == ' ' || c == '\n')
    				finalState = 2;
    			else{
    				str = str + c;
    				finalState = 9;
    			}// end else
    		}//end if(currentState == 1)
    		
    		
    		// '/' Symbol
			if(currentState == 2){
    			stringTokens.add(str.trim());
    			str = "";
    			finalState = 0;
    		}// end if(currentState == 2)
    			
    		
    		if(currentState == 3){
    			if(c == '\n'){
    				finalState = 0;
    				str = "";
    			}// end if(c == '*') 
    			else{
    				finalState = 3;
    				str = str + c;
    			}// end else
    		}// end if(currentState == 3)
    			
    		
    		if(currentState == 5){
    			if(c == '*')
    				finalState = 6;
    			else
    				finalState = 5;
    			str = str + c;
    		}// end if(currentState == 5)
    		
    		
    		if(currentState == 6){
    			if(c == '/'){
    				str = "";
    				finalState = 0;
    			}// end if(c == '/')
    			else if(c == '*' ){
    				finalState = 6;
    				str = str + c;
    			}// end else if(c == '*' )
    			else{
    				finalState = 5;
    				str = str + c;
    			}// end else
    			
    		}// end if(currentState == 6)
    	
    		
    		// No state 7 moves
    		// No state 8 moves
    		
    		
    		// /**/ Comment
    		if(currentState == 9){
    			if(symbols.contains(c)){
    				finalState = 0;
    				str = str + c;
        			stringTokens.add(str.trim());
        			str = "";
    			}// end if(symbols.contains(c)) 
    			else if(c == ' ' || c == '\n'){
    				finalState = 0;
        			stringTokens.add(str);
        			str = "";
    			}// end else if(c == ' ' || c == '\n') 
    			else{
    				finalState = 9;
    				str = str + c;
    			}// end else
    		}// end if(currentState == 9)
    			
    			
    			
    		// String
    		if(currentState == 10){
    			if(symbols.contains(c)){
    				finalState = 0;
        			stringTokens.add(str.trim());
        			str = "";
    			}// end if(symbols.contains(c)) 
    			else{
    				finalState = 10;
    				str = str + c;
    			}// end else
    		}// end if(currentState == 10)
    		
    		
    		// No 11 moves
    			
    		currentState = finalState;
    		
        }// end for(int i = 0; i < fileString.length(); i++)
       

    }// end void parseInput()
	






	
	///< setSymbols
	/*!
	 * Symbols that aren't characters
	 */
	private void setSymbols(){
		symbols.add('(');
		symbols.add(')');
		symbols.add('[');
		symbols.add(']');
		symbols.add('{');
		symbols.add('}');
		symbols.add(',');
		symbols.add('.');
		symbols.add(';');
		symbols.add('=');
		symbols.add('-');
		symbols.add('~');
		symbols.add('+');
		symbols.add('*');
		symbols.add('/');
		symbols.add('&');
		symbols.add('|');
		symbols.add('<');
		symbols.add('>');
	}// end void setSymbols()
	
}// end class JackProcessor
