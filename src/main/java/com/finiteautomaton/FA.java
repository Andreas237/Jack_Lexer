package com.finiteautomaton;


import lombok.Data;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.HashSet;

///< Finite Automaton (FA)
/**
 * @author andreasslovacek
 * This class implements States, Alphabet, Transitions, and validity of a finite automaton
 */
@Data
public class FA {
	
    private Set<Byte> alphabet;							///< Sigma alphabet
    private Set <State> states;							///< Q states
    private HashMap<Transition,Set<State>> transitions;	///< delta transition table QxSigma->Q
    private State startState;							///< q0 start state
    private Set <State> acceptStates;					///< F accept states
    private Gson GSON;							///< JSON representation of the class
    private boolean isValid;							///< Is this machine valid?
    private String type;								///< Default FA type is DFA
	
	
	
	
	
	
	
	
	
	
	///< Constructor
    /*!
     * Initialize the Finite Automaton with empty sets and default values.
     */
    public FA(){
        
    	this.acceptStates = new HashSet<State>();
        this.alphabet = new HashSet<Byte>();
        this.GSON = new GsonBuilder().setPrettyPrinting().create();
        this.isValid = true;
        this.startState = new State(0);
        this.states = new HashSet<State>();
        this.transitions = new HashMap<Transition, Set<State>>();
        this.type = "DFA";
        
    }// end public FA()  
	
	
	
	
	
	
	
	
	
	
	///< addTransition
    /*!
     * Create the transition table for the FA.  If the transition
     * already exists add it with the the new final state
     */
    private void addTransition( State startState, State finalState, Byte symbol){
    	
    	// Build the Transition table
    	Transition transition = new Transition(startState,symbol);
    	// if the tranisition is in the table add a new final state with it
        if( this.transitions.containsKey(transition) )
        	this.transitions.get(transition).add(finalState);
        else{	// add the tranisition and final state
        	this.transitions.put(transition, new HashSet<State>());
        	this.transitions.get(transition).add(finalState);
        }// end else
        	
    }//end private void addTransition( State startState, State finalState, Byte symbol)
	
	
	
	
	
	
	
	
	
	
	///< alphabetToString
    /*!
     * Add every symbol of the alphabet to the output string. Do not
     * include epsilon == `.
     * StringBuilder isn't implicity called on concatenation within a loop,
     * therefore build that and return the string equivalent.
     * \return The alphabet
     */
    public String alphabetToString(){
    	
    	StringBuilder outStr = new StringBuilder();
    	
    	
    	// Add everything in the alphabet except epsilon to the output string
    	for(Byte symbol : alphabet){
    		if( symbol != 96 ){
    			byte [] temp = {symbol};
    			outStr.append( new String(temp) );
    		}// end if( symbol != 96 ) 
    	}// end for(Byte symbol : alphabet) 
    	
    	return outStr.toString();
    	
    }// end public String alphabetToString()
	
	
	
	
	
	
	
	
	
	
	///< getCountState
    protected int getCountState(){ return this.states.size();}
	
	
	
	
	
	
	
	
	
	
	///< printJsonSelf
    public void printJsonSelf(){	System.out.print( this.GSON.toJson(this) );}
	
	
	
	
	
	
	
	
	
	
	///< printStringSelf
    public void printStringSelf(){
    	StringBuilder sb = new StringBuilder();
    	
    	// Append States
    	sb.append("states:" + statesToString() + "\n");
    	// Append Alphabet
    	sb.append("alphabet:" + alphabetToString() + "\n");
    	// Append Transitions
    	// Print something like :{start state, symbol, [final states]
    	// Append Start State
    	// Append Accept States
    	// Append Type
    	
    }// end public void printStringSelf()
	
	
	
	
	
	
	
	
	
	
	///< setIsValid
    /*!
     * Set isValid based on input IF we are not in a trap state
     */
    public void setIsValid(boolean in/**<Validity of the FA>*/){
    	if( this.isValid == true)
    		this.isValid = in;
    }// end public void setIsValid(boolean in) 
	
	
	
	
	
	
	
	
	
	
	///< setType
    /*!
     * Set the type of the FA based on the transition table
     */
    public void setType(){
    	
    	// Iterate through the transitions
    	for (Transition trans : getTransitions().keySet()){
    		if(transitions.get(trans).size() > 1){
    			type = "NFA";
    			return;
    		}// end if(transitions.get(transition).size() > 1)
    	}// end for (Transition trans : getTransitions().keySet())
      
    	
    	// Check for ` in alphabet
    	for(Byte symbol : alphabet){
    		if(symbol == 96){
				type = "NFA";
	  			return;
			}// end if(symbol == 96)
	    }// end for(Byte symbol : alphabet)
    	
    	
    	// If the boolean type is invalid then set the printable type
		if(isValid == false)
			type = "INVALID";
		
		
    }// end public void setType() 
	
	
	
	
	
	
	
	
	
	
	///< statesToString
    public String statesToString(){
    	
    	StringBuilder sb = new StringBuilder();
    	
    	for( State s : states){
    		sb.append( s.state + " ");
    	}// end for( State s : states)
    	
    	
		return sb.toString();
    	
    }// end public String statesToString() 
	
	
	
	
	
	
	
	
	
	
	///< transitionsToString
    public String transitionsToString(){
    	
    	StringBuilder sb = new StringBuilder();
    	StringBuilder finalStates = new StringBuilder();
    	
    	// For every startState and symbol get an array of the transitions
    	for( Transition t : transitions.keySet()){
    		
    		finalStates.delete(1, finalStates.length()); // clear final states for every transition
    		byte [] byteArray = {t.symbol};
    		String symbol = new String(byteArray);
    		
    		// get the finalState for this transition
    		for(State s: transitions.get(t)) 
    			finalStates.append(s.state+",");
    		//TODO: finish building output string that will be: start,symbol,[final states]
    		
    	}// end for( State s : states)
    	
    	
		return sb.toString();
    	
    }// end public String transitionsToString() 
    
}// end public class FA 
