package com.finiteautomaton;


import lombok.Data;


/*!
 * @author andreasslovacek
 * This class implements the transition table of an Finite Automaton
 */
@Data
public class Transition {

	
	public State currentState; 	///< The state before the transition 
    public Byte symbol;			///< The alphabet symbol causing the transition
	
	
	
	
	
	
	
	
	
	
	///< Constructor
    /*!
     * Transition constructed from a start state and symbol.
     */
    public Transition(State startState, Byte symbol){
        this.currentState = startState;
        this.symbol = symbol;
    }// end public Transition(State startState, Byte symbol)
	
	
	
	
	
	
	
	
	
	
	///< Equality of Transitions
    // Note: Lombok recommends implementing all-or-non equals(), hashCode()
    @Override
    public boolean equals(Object obj){
        Transition trans = (Transition) obj;
        if( this.currentState == trans.currentState && this.symbol == trans.symbol)
            return true;
        else
            return false;
    }// end public boolean equals(Object obj)
	
	
	
	
	
	
	
	
	
	
	///< HashCode of Transition
    // Note: Lombok recommends implementing all-or-non equals(), hashCode()
    @Override
    public int hashCode(){	return this.toString().hashCode();}
	
	
	
	
	
	
	
	
	
	
}// end public class Transition
