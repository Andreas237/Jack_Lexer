package com.finiteautomaton;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.lang3.StringUtils;







///< Finite Automaton (FiniteAutomaton)
/**
 * @author andreasslovacek
 * This class implements States, Alphabet, Transitions, and validity of a finite automaton
 */
@Data
class FiniteAutomaton {

	
	private Set<Byte> alphabet;								///< Sigma alphabet
    private Set <State> states;								///< Q states
    protected HashMap<Transition,Set<State>> transitions;	///< delta transition table QxSigma->Q
    private State startState;								///< q0 start state
    private Set <State> acceptStates;						///< F accept states
    private boolean acceptStatus;							///< Accept status of the FA given an input string
    private boolean isValid;								///< Is this machine valid?
    private String type;									///< Default FiniteAutomaton type is DFA
    private Integer finalState;								///< Final state of the machine							
	
	
	
	
	
	
	
	
	
	
	///< Constructor
    /*!
     * Initialize the Finite Automaton with empty sets and default values.
     */
    public FiniteAutomaton(){
        
    	this.acceptStates = new HashSet<State>();
        this.alphabet = new HashSet<Byte>();
        this.isValid = true;
        this.startState = new State(0);
        this.states = new HashSet<State>();
        this.transitions = new HashMap<Transition, Set<State>>();
        this.type = "DFA";
        
    }// end public FiniteAutomaton()
	
	
	
	
	
	
	
	
	
	
	///< acceptStatesToString
    public String acceptStatesToString(){
   
    	StringBuilder sb = new StringBuilder();
   
    	for(State s: acceptStates)
    		sb.append(s.state + " ");
    	
    	return sb.toString();
    }// end public String acceptStatesToString()  
	
	
	
	
	
	
	
	
	
	
	///< addAcceptState(Integer i)
    /*!
     * Given an integer add it as an accept state
     */
    public void addAcceptState(int i){	this.acceptStates.add( new State(i));}
	
	
	
	
	
	
	
	
	
	
	///< addTransition
    /*!
     * Create the transition table for the FiniteAutomaton.  If the transition
     * already exists add it with the the new final state
     */
    public void addTransition( State startState, State finalState, Byte symbol){
    	
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
	
	
	
	
	
	
	
	
	
	
	///< getAcceptStatus
	public boolean getAcceptStatus() { return this.acceptStatus;}
	
	
	
	
	
	
	
	
	
	
	///< getCountState
    protected int getCountState(){ return this.states.size();}
	
	
	
	
	
	
	
	
	
	
	///< printStringSelf
    public void printStringSelf(){
    	StringBuilder sb = new StringBuilder();
    	
    	// Append States
    	sb.append("states: " + statesToString() + "\n");
    	// Append Alphabet
    	sb.append("alphabet: " + alphabetToString() + "\n");
    	// Append Transitions
    	sb.append("transitions:\n" + transitionsToString());
    	// Append Start State
    	sb.append("start state: " + startState.state + "\n");
    	// Append Accept States
    	sb.append("accept states: " + acceptStatesToString() + "\n");
    	// Append Type
    	sb.append("type: " + this.getType() + "\n");
    	
    	System.out.println( sb.toString() );
    	
    }// end public void printStringSelf()
	
	
	
	
	
	
	
	
	
	
	///< setAcceptStates(Set<Integer> accepts)
    /*!
     * Given a set of integers add them to the accept states
     */
    public void setAcceptStates(Set<Integer> accepts){
    	for(Integer k: accepts)
    		this.acceptStates.add(new State(k));
    }// end void setAcceptStates(Set<Integer> accepts)
	
	
	
	
	
	
	
	
	
	
	///< setIsValid
    /*!
     * Set isValid based on input IF we are not in a trap state
     */
    public void setIsValid(boolean in/**<Validity of the FiniteAutomaton>*/){
    	if( this.isValid == true)
    		this.isValid = in;
    }// end public void setIsValid(boolean in) 
	
	
	
	
	
	
	
	
	
	
	///< setType
    /*!
     * Set the type of the FiniteAutomaton based on the transition table
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
    /*!
     * Build an output with all the entries in the transition table of the form:
     * initialState1,symbol,[finalState11,finalState12,...]
     * initialState2,symbol,[finalState21,finalState22,...]
     * @return The string containing all entries in the transition table
     */	
    private String transitionsToString(){
    	
    	StringBuilder sb = new StringBuilder();				// String to return
    	StringBuilder finalStates = new StringBuilder("[");	// Final States string, reset after every transition
    	int count = 0;										// indexer for final string
    	
    	// For every startState and symbol get an array of the transitions
    	for( Transition t : transitions.keySet()){
    		
    		finalStates.delete(1, finalStates.length()); // clear final states for every transition
    		byte [] byteArray = {t.symbol};
    		String symbol = new String(byteArray);
    		
    		// get the finalState for this transition
    		// format the string with commas and brackets as needed
    		count = 0;// reset count for each transition
    		for(State s: transitions.get(t)){
    			if(count > 0)
    				finalStates.append(" ");
    			finalStates.append(s.state);
    			count++;
    		}// end for(State s: transitions.get(t))
    		count = finalStates.lastIndexOf(" "); // reuse count to hold the place of the last comma
    		
    		
    		finalStates.append("]"); // 
    		
    		finalStates.delete(count, count);
    		
    		sb.append(t.currentState.state + "," + symbol + "," + finalStates + "\n" );
    		
    	}// end for( State s : states)
    	
    	
		return sb.toString();
    	
    }// end public String transitionsToString() 
    
}// end public class FiniteAutomaton 
