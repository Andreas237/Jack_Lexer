package com.finiteautomaton;


import lombok.Data;


/**
 * @author andreasslovacek
 * This class implements the functions needed for state operations
 * such as checking if trap/start, comparison and equality.
 * Implements the Comparable interface for sorting later.  
 */
@Data
class State implements Comparable<State> {

	
	public int state;
	
	
	
	
	
	
	
	
	
	
	///< Constructor
	public State(int state){
        this.state = state;
    }// end public State(int state)
	
	
	
	
	
	
	
	
	
	
	///< Compare integer values of this state and a given state
	public int compareTo(State o){
		Integer t = this.state;
		Integer n = o.state;
		return t.compareTo(n);
	}// end public int compareTo(State o)
	
	
	
	
	
	
	
	
	
	
	///< Equality of states
	// Note: Lombok recommends implementing all-or-non equals(), hashCode()
    @Override
    public boolean equals(Object o){
        State inState = (State) o;
        if(inState.state == this.state)
            return true;
        else
            return false;
    }// end public boolean equals(Object o)
	
	
	
	
	
	
	
	
	
	
	///< Return the hash code of the state
    // Note: Lombok recommends implementing all-or-non equals(), hashCode()
    @Override
    public int hashCode(){	return state;}
	
	
	
	
	
	
	
	
	
	
	///< Return true if this is the start or trap state
    public boolean isValid(){
        if(this.state < 0 || this.state > 255)
            return false;
        else
            return true;
    }// end public boolean isValid()
	
	
	
	
	
	
	
	
	
	
	///< Return comparison of this state with trap state
    public boolean isTrapState(){	return state == 255;}
	
	
	
	
	
	
	
	
	
	
	///< Return comparison of this state with start state
    public boolean isStartState(){	return state == 0;}
	
	
	
	
	
	
	
	
	
	
	
}//end class State implements Comparable<State>
